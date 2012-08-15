;   Copyright (c) Rich Hickey, Stuart Halloway, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test.generative.logback
  (:require [clojure.test.generative.event :as event])
  (:import [ch.qos.logback.classic.spi ILoggingEvent]
           [ch.qos.logback.classic Level]
           [org.slf4j Logger LoggerFactory]))

(set! *warn-on-reflection* true)

(defn level->logback
  [level]
  (case level
        :info Level/INFO
        :warn Level/WARN
        :error Level/ERROR
        :debug Level/DEBUG))           

(defn event->logback
  [event ^ch.qos.logback.classic.Logger logger]
  (let [msg (delay (binding [*print-length* 50]
                     (pr-str (dissoc event :level :thread :tstamp :name))))
        level (level->logback (:level event))]
    (reify ILoggingEvent
           (getThreadName [_] (str (:thread-name event)))
           (getLevel [_] level)
           (getMessage [_] @msg)
           (getArgumentArray [_])
           (getFormattedMessage [_] @msg)
           (getLoggerName [_] (.getName logger))
           (getLoggerContextVO [_] (.. logger getLoggerContext getLoggerContextRemoteView))
           (getThrowableProxy [_])
           (getCallerData [_])
           (hasCallerData [_])
           (getMarker [_])
           (getMdc [_])
           (getTimeStamp [_] (:tstamp event))
           (prepareForDeferredProcessing [_]))))

(defn handler
  [event]
  (let [name (str (:name event))
        logger ^ch.qos.logback.classic.Logger (LoggerFactory/getLogger name)]
    (.callAppenders logger (event->logback event logger))))


(comment
  (require :reload '[clojure.test.generative.event :as event])
  (require :reload '[clojure.test.generative.event.logback :as la])
  (in-ns 'clojure.test.generative.event.logback-adapter)
  (set! *warn-on-reflection* true)
  (def l (LoggerFactory/getLogger "stu"))
  (.info l "hi")
  (report-to-logback (event/create {:level :warn :message "Drat"}))

  )
