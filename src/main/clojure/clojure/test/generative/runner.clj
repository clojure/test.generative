;   Copyright (c) Rich Hickey, Stuart Halloway, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test.generative.runner
  (:require
   [clojure.tools.namespace :as ns]
   [clojure.test.generative.config :as config]
   [clojure.test.generative.event :as event]
   [clojure.test.generative.generators :as gen]
   [clojure.test.generative.io :as io]
   [clojure.test.generative.clojure-test-adapter :as cta]
   [clojure.test :as ctest]))

(set! *warn-on-reflection* true)

(defprotocol Test
  (test-name [_])
  (test-fn [_])
  (test-input [_]))

(extend-protocol Test
  clojure.lang.Var
  (test-name
   [v]
   (-> (when-let [ns (.ns v)]
         (str ns "/" (.sym v))
         (.sym v))
       symbol))
  (test-fn
   [this]
   @this)
  (test-input
   [v]
   (map #(%) (:clojure.test.generative/inputs (meta v)))))

;; non-nil binding means running inside the framework
(def ^:dynamic *failed* nil)

(defn run-iter
  "Run a single test iteration"
  [test]
  (let [name (test-name test)
        f (test-fn test)
        input (test-input test)]
    (event/report :test/iter :name name :args input :tags #{:begin})
    (try
     (let [result (apply f input)]
       (when-not (realized? *failed*)
         (event/report :test/iter :name name :return result :tags #{:end})))
     (catch Throwable t
       (deliver *failed* :error)
       (event/report :error :name name :exception t)))))

(defn run-for
  "Run f (presumably for side effects) repeatedly on n threads,
   until msec has passed or somebody signals *failed*"
  [test nthreads msec]
  (let [start (System/currentTimeMillis)
        futs (doall
              (map
               #(future
                 (try
                  (event/report :test/seed :test/seed (+ % 42))
                  (binding [gen/*seed* (+ % 42)
                            gen/*rnd* (java.util.Random. gen/*seed*)
                            *failed* (promise)]
                    (event/report :test/test :tags #{:begin})
                    (loop [iter 0]
                      (let [result (run-iter test)
                            now (System/currentTimeMillis)
                            failed? (realized? *failed*)]
                        (if (and (< now (+ start msec))
                                   (not failed?))
                          (recur (inc iter))
                          (event/report :test/test
                                        :msec (- now start)
                                        :count (inc iter)
                                        :tags #{:end}
                                        :test/result (if failed? :test/fail :test/pass)
                                        :level (if failed? :warn :info)
                                        :name (test-name test))))))
                  (catch Throwable t
                    (event/report :error :level :error :exception t :name (test-name test)))))
               (range nthreads)))]
    (doseq [f futs] @f)))

(defn run-batch
  "Run a batch of fs on nthreads each. Call each f repeatedly
   for up to test-msec"
  [tests nthreads test-msec]
  (when (seq tests)
    (doseq [test tests]
      (run-for test nthreads test-msec))))

(defn failed!
  "Tell the runner that a test failed"
  []
  (when *failed*
    (deliver *failed* :failed)))

#_(defn set-seed
  [n]
  (set! gen/*rnd* (java.util.Random. n)))

(defn gentest?
  [v]
  (boolean (:clojure.test.generative/inputs (meta v))))

(defn find-vars-in-namespaces
  [& nses]
  (when nses
    (reduce (fn [v ns] (into v (vals (ns-interns ns)))) [] nses)))

(defn find-vars-in-dirs
  [& dirs]
  (let [nses (mapcat #(ns/find-namespaces-in-dir (java.io.File. ^String %)) dirs)]
    (doseq [ns nses] (require ns))
    (apply find-vars-in-namespaces nses)))

(defn find-gentests-in-vars
  [& vars]
  (filter gentest? vars))

(defn run-generative-tests
  "Run generative tests."
  [nses nthreads msec]
  (let [c (count (->> (apply find-vars-in-namespaces nses)
                      (filter gentest?)))]
    (when-not (zero? c)
      (let [test-msec (quot msec c)]
        (doseq [ns nses]
          (when-let [fs (->> (find-vars-in-namespaces ns)
                             (filter gentest?)
                             seq)]
            (event/report :test/group
                          :name ns
                          :tags #{:begin}
                          :test/threads nthreads
                          :test/count (count fs))
            (try
             (run-batch
              fs
              nthreads
              test-msec)
             (finally
              (event/report :test/group :tags #{:end} :test/threads nthreads :test/count (count fs))))))))))

(defn has-clojure-test-tests?
  [ns]
  (or (contains? (ns-interns ns) 'test-ns-hook)
      (some (comp :test meta) (vals (ns-interns ns)))))

(defn run-all-tests
  "Run generative tests and clojure.test tests"
  [nses threads msec]
  (let [run-with-counts
        (fn [lib f]
          (let [event-counts (atom {})
                event-counter #(when-not (contains? (:tags %) :begin)
                                 (when-let [type (:type %)]
                                   (swap! event-counts update-in [type] (fnil inc 0))))]
            (event/report :test/library :name lib)
            (event/with-handler event-counter (f))
            @event-counts))
        ct-results (run-with-counts 'clojure.test
                     #(binding [ctest/report cta/report-adapter]
                        (when-let [ctnses (seq (filter has-clojure-test-tests? nses))]
                          (apply ctest/run-tests ctnses))))
        ctg-results (run-with-counts 'clojure.test.generative
                      #(run-generative-tests nses threads msec))]
    (io/await)
    {'clojure.test ct-results
     'clojure.test.generative ctg-results}))

(defn failed?
  [result]
  (or (:assert/fail result)
      (:test/fail result)
      (:error result)))

(defn -main
  "Command line entry point, runs all tests in dirs using clojure.test and
   test.generative. Calls System.exit!"
  [& dirs]
  (if (seq dirs)
    (let [nses (mapcat #(ns/find-namespaces-in-dir (java.io.File. ^String %)) dirs)
          conf (config/config)]
      (doseq [ns nses] (require ns))
      (event/install-default-handlers)
      (try
       (let [results (run-all-tests nses (:threads conf) (:msec conf))]
         (doseq [[k v] results]
           (println (str "\nFramework " k))
           (println v))
         (System/exit (if (some failed? (vals results)) 1 0)))
       (catch Throwable t
         (.printStackTrace t)
         (System/exit -1))
       (finally
        (shutdown-agents))))
    (do
      (println "Specify at least one directory with tests")
      (System/exit -1))))



