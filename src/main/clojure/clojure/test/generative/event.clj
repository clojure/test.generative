;   Copyright (c) Rich Hickey, Stuart Halloway, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test.generative.event
  (:require [clojure.test.generative.config :as config]
            [clojure.test.generative.io :as io]))

(set! *warn-on-reflection* true)

(defprotocol FQName
  (fqname [_]))

(extend-protocol FQName
  String
  (fqname [s] s)

  clojure.lang.Symbol
  (fqname [s] (str s))

  clojure.lang.Keyword
  (fqname [k] (subs (str k) 1))

  clojure.lang.Var
  (fqname [v] (if-let [ns (.ns v)]
                (symbol (str ns "/" (.sym v)))
                (.sym v))))

(defn level-enabled?
  "Is the event-level enabled?"
  [event-level enable-level]
  (case enable-level
        :error (case event-level (:error) true false)
        :warn (case event-level (:error :warn) true false)
        :info (case event-level (:error :warn :info) true false)
        :debug true))

(def ^long pid
  "Process id"
  (read-string (.getName (java.lang.management.ManagementFactory/getRuntimeMXBean))))

(defn assocnn
  "Assoc but drop nils"
  ([m k v] (if (nil? v) m (assoc m k v)))
  ([m k v & kvs] (let [ret (assocnn m k v)]
                   (if kvs
                     (recur ret (first kvs) (second kvs) (nnext kvs))
                     ret))))

(defn create
  [& args]
  (let [t (Thread/currentThread)
        event (apply assocnn
                     {:tstamp (System/currentTimeMillis)
                      :thread (.getId t)
                      :thread/name (.getName t)
                      :pid pid
                      :level :info}
                     args)]
    (assert (keyword? (:type event)) event)
    event))

(def ^:private handlers
  (atom []))

(defn add-handler
  "Add a handler. Idempotent"
  [f]
  (swap!
   handlers
   (fn [v f]
     (if (some #{f} v)
       v
       (conj v f)))
   f))

(defn remove-handler
  "Remove a handler. Idempotent"
  [f]
  (swap!
   handlers
   (fn [v f]
     (into (empty v) (remove #{f} v)))
   f))

(defn load-var-val
  "Load and return the value of a var"
  [fqname]
  (when-let [ns (namespace fqname)]
    (require (symbol ns)))
  (if-let [v (resolve fqname)]
    @v
    (throw (IllegalArgumentException. (str "No var named " fqname)))))

(defmacro with-handler
  "Run with handler temporarily installed."
  [handler & body]
  `(let [h# ~handler]
     (add-handler h#)
     (try
      ~@body
      (finally
       (remove-handler h#)))))

(defn install-default-handlers
  "Installs handler functions, a comma-delimited list of fn names, from
   clojure.test.generative.event.handlers. If none are specified, install
   c.t.g.io/console-reporter"
  []
  (reset! handlers [])
  (doseq [handler (:handlers (config/config))]
    (add-handler (load-var-val (symbol handler)))))

(defn report-fn
  "Call the installed handlers for an event, or io/pprint if no handlers
   installed."
  [event]
  (if-let [hs (seq @handlers)]
    (doseq [h hs]
      (h event))
    (io/pprint event)))

(defmacro report
  [type & args]
  (assert (even? (count args)) args)
  `(report-fn (create ~@args :type ~type)))

(defn local-bindings
  "Produces a map of the names of local bindings to their values."
  [env]
  (let [symbols (map key env)]
    (zipmap (map (fn [sym] `(quote ~sym)) symbols) symbols)))

(defmacro report-context
  "Report event with contextual ns, file, line, bindings."
  [type & args]
  (assert (even? (count args)) args)
  `(report-fn
    (create :locals ~(local-bindings &env)
            :file ~*file*
            :line ~(:line (meta &form))
            ~@args
            :type ~type)))



