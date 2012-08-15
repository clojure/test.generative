;   Copyright (c) Rich Hickey, Stuart Halloway, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test.generative.clojure-test
  (:require [clojure.test.generative.event :as event]
            [clojure.test.generative.config :as config]
            [clojure.test.generative.runner :as runner]
            [clojure.tools.namespace :as ns]
            [clojure.test :as ctest]))

(defmulti event->ctevent
  "Returns an event for a Clojure test reporter, or nil"
  :type)

(defmethod event->ctevent :default [_] nil)
(defmethod event->ctevent :clojure.test.generative/pass [_] {:type :pass})
(defmethod event->ctevent :clojure.test.generative/iter [e] e)
(defmethod event->ctevent :clojure.test.generative/fail [e] e)
(defmethod event->ctevent :clojure.test.generative/error [e] e)

(defmethod ctest/report :clojure.test.generative/iter
  [e]
  (when (contains? (:tags e) :begin)
    (ctest/inc-report-counter :test)))

(defmethod ctest/report :clojure.test.generative/fail
  [e]
  (ctest/inc-report-counter :fail)
  (println "\nFAIL\n")
  (event/pprint e))

(defmethod ctest/report :clojure.test.generative/error
  [e]
  (ctest/inc-report-counter :error)
  (println "\nERROR\n")
  (event/pprint e))

(defn handler
  "Event handler that delegates c.t.g events to clojure.test/report"
  [e]
  (event/dot-progress e)
  (when-let [cte (event->ctevent e)]
    (clojure.test/report cte)))

(defmacro run-generative-tests
  "Add this macro somewhere in your clojure.test suite to create
   a clojure.test entry point that will run your generative tests"
  []
  (event/add-handler handler)
  `(ctest/deftest ~'generative-test-adapter
     []
     (runner/run-all)))

(defn -main
  "Command line entry point, runs all tests in dirs using clojure.test"
  [& dirs]
  (if (seq dirs)
    (let [nses (mapcat #(ns/find-namespaces-in-dir (java.io.File. ^String %)) dirs)]
      (doseq [ns nses] (require ns))
      (let [{:keys [threads msec]} (config/config)]
        (try
         (println "Testing on" threads "threads for" msec "msec.")
         (apply ctest/run-tests nses)
         (catch Throwable t
           (.printStackTrace t)
           (System/exit -1))
         (finally
          (shutdown-agents)))))
    (do
      (println "Specify at least one directory with tests")
      (System/exit -1))))

