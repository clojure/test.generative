(ns clojure.test.generative.logback-test
  (:use [clojure.test :only (deftest)]
        clojure.test.generative)
  (:require [clojure.test.generative.logback :as logback]
            [clojure.test.generative.event :as event])
  (:import [ch.qos.logback.classic.spi ILoggingEvent]
           [ch.qos.logback.classic Level]))

(set! *warn-on-reflection* true)

(deftest create-logback-event
  (let [{:keys [^ILoggingEvent event]}
        (logback/event->logback (event/create :type :example/event))]
    (is (= Level/INFO (.getLevel event)))
    (is (= "example/event" (.getLoggerName event)))))
