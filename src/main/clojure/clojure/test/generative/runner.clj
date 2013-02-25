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
   [clojure.java.io :as jio]
   [clojure.pprint :as pprint]
   [clojure.stacktrace :as stacktrace]
   [clojure.tools.namespace :as ns]
   [clojure.test.generative.config :as config]
   [clojure.test.generative.event :as event]
   [clojure.data.generators :as gen]
   [clojure.test.generative.io :as io]
   [clojure.test :as ctest]))

(set! *warn-on-reflection* true)

;; non-nil binding means running inside the framework
(def ^:dynamic *failed* nil)

(defn failed!
  "Tell the runner that a test failed"
  []
  (when *failed*
    (deliver *failed* :failed)))

(defmulti ctevent->event
  "Convert a clojure.test reporting event to an event."
  :type)

(defmethod ctevent->event :default
  [e]
  (event/create :clojure.test/unknown e))

(defmethod ctevent->event :pass
  [e]
  (event/create :type :assert/pass))

(defmethod ctevent->event :fail
  [e]
  (failed!)
  (event/create :type :assert/fail
                :level :warn
                :message (:message e)
                :test/actual (:actual e)
                :test/expected (:expected e)
                :file (:file e)
                :line (:line e)
                ::ctest/contexts (seq ctest/*testing-contexts*)
                ::ctest/vars (reverse (map #(:name (meta %)) ctest/*testing-vars*))))

(defmethod ctevent->event :error
  [e]
  (event/create :level :error
                :type :error
                ::ctest/contexts (seq ctest/*testing-contexts*)
                :message (:message e)
                :test/expected (:expected e)
                :exception (:actual e)
                :file (:file e)
                :line (:line e)
                ::ctest/vars (reverse (map #(:name (meta %)) ctest/*testing-vars*))))

(defmethod ctevent->event :summary
  [e]
  nil)

(defmethod ctevent->event :begin-test-ns
  [e]
  (event/create :type :test/group
                :tags #{:begin}
                :name (ns-name (:ns e))))

(defmethod ctevent->event :end-test-ns
  [e]
  (event/create :type :test/group
                :tags #{:end}
                :name (ns-name (:ns e))))

(defmethod ctevent->event :begin-test-var
  [e]
  (event/create :type :test/test
                :tags #{:begin}
                :name (event/fqname (:var e))))

(defmethod ctevent->event :end-test-var
  [e]
  (event/create :type :test/test
                :tags #{:end}
                :name (event/fqname (:var e))))

(defn ct-adapter
  "Adapt clojure.test event model to fire c.t.g events."
  [m]
  (when-let [e (ctevent->event m)]
    (event/report-fn e)))

(defn run-iter
  "Run a single test iteration"
  [name f input]
  (event/report :test/iter :level :debug :name name :args input :tags #{:begin})
  (try
   (let [result (apply f input)]
     (when-not (realized? *failed*)
       (event/report :test/iter :level :debug :name name :return result :tags #{:end})))
   (catch Throwable t
     (deliver *failed* :error)
     (event/report :error :name name :exception t))))

(defn run-for
  "Run f (presumably for side effects) repeatedly on n threads,
   until msec has passed or somebody signals *failed*"
  [test nthreads msec]
  (let [start (System/currentTimeMillis)
        futs (doall
              (map
               #(future
                 (try
                  (let [seed (+ % 42)
                        name (:name test)
                        f (:fn test)]
                    (binding [gen/*rnd* (java.util.Random. seed)
                              *failed* (promise)]
                      (event/report :test/test :tags #{:begin} :test/seed seed :name name)
                      (loop [iter 0
                             [input & more] ((:inputs test))]
                        (let [now (System/currentTimeMillis)
                              failed? (realized? *failed*)]
                          (if input
                            (let [result (run-iter name f input)]
                              (if (and (< now (+ start msec))
                                       (not failed?))
                                (recur (inc iter) more)
                                (event/report :test/test
                                              :msec (- now start)
                                              :count (inc iter)
                                              :tags #{:end}
                                              :test/result (if failed? :test/fail :test/pass)
                                              :level (if failed? :warn :info)
                                              :name name)))
                            (event/report :test/test
                                          :msec (- now start)
                                          :count iter
                                          :tags #{:end :test/inputs-exhausted}
                                          :test/result (if failed? :test/fail :test/pass)
                                          :level (if failed? :warn :info)
                                          :name name))))))
                  (catch Throwable t
                    (event/report :error :level :error :exception t :name name))))
               (range nthreads)))]
    (doseq [f futs] @f)))

(defn run-batch
  "Run a batch of fs on nthreads each. Call each f repeatedly
   for up to test-msec"
  [tests nthreads test-msec]
  (when (seq tests)
    (doseq [test tests]
      (run-for test nthreads test-msec))))

(defmulti var-tests
  "TestContainer.tests support for vars. To create custom test
   types, define vars that have :c.t.g/type metadata, and then add
   a matching var-tests method that returns a collection of tests."
  (fn [v] (:clojure.test.generative/type (meta v))))

(defmethod var-tests :defspec [^clojure.lang.Var v]
  [{:name  (-> (when-let [ns (.ns v)]
                 (str ns "/" (.sym v))
                 (.sym v))
               symbol)
    :fn @v
    :inputs (fn []
              (repeatedly
               (fn []
                 (into [] (map #(%) (:clojure.test.generative/arg-fns (meta v)))))))}])

(defmethod var-tests nil [v] nil)

(defprotocol TestContainer
  (tests
   [_]
   "Returns a collection of generative tests, where a test is a map with
      :name     ns-qualified symbol
      :fn       fn to test
      :inputs   fn returning a (possibly infinite!) sequence of inputs

   All input generation should use and gen/*rnd*
   if a source of pseudo-randomness is needed."))

(extend-protocol TestContainer
  clojure.lang.Var
  (tests [v] (var-tests v)))

(defn find-vars-in-namespaces
  [& nses]
  (when nses
    (reduce (fn [v ns] (into v (vals (ns-interns ns)))) [] nses)))

(defn find-vars-in-dirs
  [& dirs]
  (let [nses (mapcat #(ns/find-namespaces-in-dir (java.io.File. ^String %)) dirs)]
    (doseq [ns nses] (require ns))
    (apply find-vars-in-namespaces nses)))

(defn run-generative-tests
  "Run generative tests."
  [nses nthreads msec]
  (let [c (count (->> (apply find-vars-in-namespaces nses)
                      (mapcat tests)))]
    (when-not (zero? c)
      (let [test-msec (quot msec c)]
        (doseq [ns nses]
          (when-let [fs (->> (find-vars-in-namespaces ns)
                             (mapcat tests)
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
  (binding [ctest/report ct-adapter]
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
                       #(when-let [ctnses (seq (filter has-clojure-test-tests? nses))]
                          (apply ctest/run-tests ctnses)))
          ctg-results (run-with-counts 'clojure.test.generative
                        #(run-generative-tests nses threads msec))]
      (io/await)
      {'clojure.test ct-results
       'clojure.test.generative ctg-results})))

(defn failed?
  [result]
  (or (:assert/fail result)
      (:test/fail result)
      (:error result)))

(def process-id
  (delay
   (java.util.UUID/randomUUID)))

(def storage-writer
  (delay
   (let [f (str ".tg/" @process-id)]
     (jio/make-parents f)
     (jio/writer f :append true))))

(def store-agent (agent nil))

(def store
  "store data in .tg/{process-id}"
  (io/serialized
   (fn [e]
     (binding [*print-length* nil
               *print-level* nil
               *out* @storage-writer]
       (println e)))
   store-agent))

(defn save
  "Save results at info level or higher, using store."
  [e]
  (when (event/level-enabled? (:level e) :info)
    (store e)))

(defn test-dirs
  "Runs tests in dirs, returning a map of test lib keyword
   to summary data"
  [& dirs]
  (let [nses (mapcat #(ns/find-namespaces-in-dir (java.io.File. ^String %)) dirs)
        conf (config/config)]
    (doseq [ns nses] (require ns))
    (event/install-default-handlers)
    (run-all-tests nses (:threads conf) (:msec conf))))

(defn -main
  "Command line entry point, runs all tests in dirs using clojure.test and
   test.generative. Calls System.exit!"
  [& dirs]
  (if (seq dirs)
    (try
     (let [results (apply test-dirs dirs)
           failed? (boolean (some failed? (vals results)))]
       (doseq [[k v] results]
         (println (str "\nFramework " k))
         (println v))
       (when failed?
         (binding [*out* *err*]
           (println "\n*** Some tests failed ***\n")))
       (System/exit (if failed? 1 0)))
     (catch Throwable t
       (stacktrace/print-cause-trace t)
       (System/exit -1))
     (finally
      (shutdown-agents)))
    (do
      (println "Specify at least one directory with tests")
      (System/exit -1))))



