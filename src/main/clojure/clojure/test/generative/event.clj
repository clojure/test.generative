;   Copyright (c) Rich Hickey, Stuart Halloway, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test.generative.event
  (:refer-clojure :exclude [pr-str])
  (:require [clojure.pprint :as pprint]
            [clojure.string :as str]))

(set! *warn-on-reflection* true)

(def ^long pid
  "Process id"
  (read-string (.getName (java.lang.management.ManagementFactory/getRuntimeMXBean))))

(defn create
  [& args]
  (let [t (Thread/currentThread)]
    (apply assoc
           {:tstamp (System/currentTimeMillis)
            :thread (.getId t)
            :thread-name (.getName t)
            :pid pid
            :level :info}
           args)))

(def ^:private serializer (agent nil))

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

(defn pprint
  "Print with event print settings"
  [s]
  (binding [*print-length* event-print-length
            *print-level* event-print-level]
    (pprint/pprint s)
    (flush)))

(def ^:private report-fns
  (atom []))

(defn add-handler
  "Add a handler. Idempotent"
  [f]
  (swap!
   report-fns
   (fn [v f]
     (if (some #{f} v)
       v
       (conj v f)))
   f))

(defn remove-handler
  "Remove a handler. Idempotent"
  [f]
  (swap!
   report-fns
   (fn [v f]
     (into (empty v) (remove #{f} v)))
   f))

(defmacro with-handler
  "Run with handler temporarily installed."
  [handler & body]
  `(let [h# ~handler]
     (add-handler h#)
     (try
      ~@body
      (finally
       (remove-handler h#)))))

(defn load-var-val
  "Load and return the value of a var"
  [fqname]
  (when-let [ns (namespace fqname)]
    (require (symbol ns)))
  @(resolve fqname))

(defn load-default-handlers
  []
  (reset! report-fns [])
  (doseq [handler (let [s (System/getProperty "clojure.test.generative.event.handlers")]
              (when (seq s) (str/split s #",")))]
    (add-handler (load-var-val (symbol handler)))))

(defn report-fn
  [event]
  (doseq [f @report-fns]
    (f event)))

(defmacro report
  [& args]
  `(report-fn (create ~@args)))

(defn local-bindings
  "Produces a map of the names of local bindings to their values."
  [env]
  (let [symbols (map key env)]
    (zipmap (map (fn [sym] `(quote ~sym)) symbols) symbols)))

(defmacro report-context
  "Report event with contextual ns, file, line, bindings."
  [& args]
  `(report-fn
    (create :bindings ~(local-bindings &env)
            :file ~*file*
            :line ~(:line (meta &form))
            ~@args)))

(def last-dot (atom 0))

(defn dot-progress
  "Prints a dot per event, throttled to ten dots/sec."
  [{:keys [tstamp]}]
  (when (< 100 (- tstamp @last-dot))
    (reset! last-dot tstamp)
    (send-off serializer
              (fn [_] 
                (print ".")
                (flush)
                nil))))

