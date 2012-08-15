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
   [clojure.test.generative.generators :as gen]))

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
    (event/report :name name :args input :type :clojure.test.generative/iter :tags #{:begin})
    (try
     (let [result (apply f input)]
       (when-not (realized? *failed*)
         (event/report :name name :return result :type :clojure.test.generative/iter :tags #{:end})))
     (catch Throwable t
       (deliver *failed* :error)
       (event/report :name name :exception t :type :clojure.test.generative/error)))))

(defn run-for
  "Run f (presumably for side effects) repeatedly on n threads,
   until msec has passed or somebody signals *failed*"
  [test nthreads msec]
  (let [start (System/currentTimeMillis)
        futs (doall
              (map
               #(future
                 (try
                  (binding [gen/*seed* (+ % 42)
                            gen/*rnd* (java.util.Random. gen/*seed*)
                            *failed* (promise)]
                    (loop [iter 0]
                      (let [result (run-iter test)
                            now (System/currentTimeMillis)]
                        (if (and (< now (+ start msec))
                                   (not (realized? *failed*)))
                          (recur (inc iter))
                          (event/report :msec (- now start)
                                        :count iter
                                        :type :clojure.test.generative/run-completed)))))
                  (catch Throwable t
                    (event/report :level :error :exception t :type :clojure.test.generative/run-error))))
               (range nthreads)))]
    (doseq [f futs] @f)))

(defn run-batch
  "Run a batch of fs on nthreads each. Try to divide msec
   among the fs. Args like run-for."
  [tests nthreads msec]
  (when (seq tests)
    (let [msec-per-f (quot msec (count tests))]
      (doseq [test tests]
        (run-for test nthreads msec-per-f)))))

(defn failed!
  "Tell the runner that a test failed"
  []
  (when *failed*
    (deliver *failed* :failed)))

(defn set-seed
  [n]
  (event/report :seed n)
  (set! gen/*rnd* (java.util.Random. n)))

(defn run-suite
  [fs nthreads msec]
  (event/report :tags #{:begin} :type :clojure.test.generative/suite :nthreads nthreads :ntests (count fs))
  (try
   (run-batch
    fs
    nthreads
    msec)
   (finally
    (event/report :tags #{:end} :type :clojure.test.generative/suite :nthreads nthreads :ntests (count fs)))))

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

(defn run-all
  []
  (let [conf (config/config)
        tests (->> (apply find-vars-in-namespaces (all-ns))
                   (filter gentest?))]
    (run-suite tests (:threads conf) (:msec conf))))



