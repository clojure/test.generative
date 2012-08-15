;   Copyright (c) Rich Hickey, Stuart Halloway, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test.java-test
  (:use clojure.test.generative))

(set! *warn-on-reflection* true)

(defspec entry-set-keys-always-have-values
  #(java.util.concurrent.ConcurrentHashMap. ^java.util.Map %)
  [^{:tag (hash-map #(uniform 0 1e7) long #(uniform 1 20000))} m]
  (let [ks (keys m)]
    (dotimes [_ 5]
      (future
       (doseq [k (shuffle ks)]
         (.remove ^java.util.Map % k)
         (Thread/sleep 1)))))
  (loop [it (-> ^java.util.Map % (.entrySet) (.iterator))]
    (Thread/sleep 1)
    (when (.hasNext it)
      (let [[k v] (.next it)]
        (when (nil? v) (throw (RuntimeException. "Nil value for " k)))
        (recur it)))))
