;   Copyright (c) Rich Hickey, Stuart Halloway, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test.generative.io
  (:refer-clojure :exclude [pr-str println await])
  (:require [clojure.pprint :as pprint]))

(def ^:private serializer (agent nil))

(defn await
  []
  "Wait for everything sent to the serializer"
  (send-off serializer (fn [_]))
  (clojure.core/await serializer))

(defn serialized
  "Returns a function that calls f for side effects, async,
   serialized by an agent"
  [f]
  (fn [& args]
    (send-off serializer
              (fn [_]
                (apply f args)
                nil))))

;; TODO set from Java property?
(def ^:private event-print-length 100)
(def ^:private event-print-level 10)

(defn pr-str
  "Print with event print settings"
  [s]
  (binding [*print-length* event-print-length
            *print-level* event-print-level]
    (clojure.core/pr-str s)))

(def println
  "Print with event print settings"
  (serialized clojure.core/println))

(def pprint
  "Print with event print settings"
  (serialized
   (fn [s]
     (binding [*print-length* event-print-length
               *print-level* event-print-level]
       (pprint/pprint s)
       (flush)))))

(def last-dot (atom 0))

#_(defn dot-progress
  "Prints a dot per event, throttled to ten dots/sec."
  [{:keys [tstamp]}]
  (when (< 100 (- tstamp @last-dot))
    (reset! last-dot tstamp)
    (send-off serializer
              (fn [_] 
                (print ".")
                (flush)
                nil))))

(def report-hierarchy
  (reduce
   #(apply derive %1 %2)
   (make-hierarchy)
   [[:test/iter :ignore]
    [:test/seed :ignore]
    [:test/pass :ignore]
    [:assert/pass :ignore]
    [:assert/summary :ignore]]))

(defmulti console-reporter :type :hierarchy #'report-hierarchy)

#_(defmethod console-reporter :progress [m] (dot-progress m))
(defmethod console-reporter :ignore [_])
(defmethod console-reporter :test/test
  [{:keys [tags msec] :as m}]
  (when (and (tags :end))
      (println (select-keys m [:msec :test/result :name :count]))))
(defmethod console-reporter :test/group
  [{:keys [name tags]}]
  (when-not (tags :end)
      (println (str "\n" name"\n"))))
(defmethod console-reporter :test/library
  [{:keys [name]}]
  (println (str "\n"
            (apply str (repeat 60 "="))
            "\nRunning " name " tests\n")))
(defmethod console-reporter :default [m] (pprint m))


