;   Copyright (c) Rich Hickey, Stuart Halloway, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test.hash-test
  (:use clojure.test.generative)
  (:require [clojure.data.generators :as gen]))

(defn hash-obj
  [h]
  (reify Object (hashCode [_] h)))

(defn poorly-keyed-map
  [vals distinct]
  (zipmap
   (map #(hash-obj (mod (hash %) distinct)) vals)
   vals))

(defn verify-key-removal
  [m kset]
  (loop [c (count kset)
         [k & more] (seq kset)
         tmap (transient m)]
    (if k
      (do
        (assert (= (count tmap) c))
        (recur (dec c)
               more
               (dissoc! tmap k)))
      (do
        (assert (zero? (count tmap)))
        (assert (zero? (count (persistent! tmap))))))))

(defspec transient-maps-with-key-collision
  poorly-keyed-map
  [^{:tag (vec long (uniform 0 1000))} vals
   ^{:tag (uniform 1 100)} distinct]
  (verify-key-removal % (into #{} (keys %))))
