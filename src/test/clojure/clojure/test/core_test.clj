;   Copyright (c) Rich Hickey, Stuart Halloway, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test.core-test
  (:use clojure.test.generative clojure.pprint clojure.data clojure.walk)
  (:require [clojure.test.generative.generators :as gen]))

(def ^:private failed-diff-ref (atom nil))

(defmacro
  assert=
  "Specialization of assert for equality. Uses clojure.data/diff to
   report detailed difference in unequal things. You can get the
   (last-failed-diff)."
  ([a b] `(assert= ~a ~b nil))
  ([a b context]
     (when *assert*
       `(let [[d1# d2# s#] (clojure.data/diff ~a ~b)
              context# (if ~context (str "[" ~context "] ") "")]
          (when (or d1# d2#)
            (reset! failed-diff-ref [d1# d2# s#])
            (throw (new AssertionError (str context# "Items different: " ~a ", " ~b))))))))

(defn last-failed-diff
  []
  @failed-diff-ref)

(defn map->case-src
  "Returns source for a case statement that works like map m,
   with default for missing elements."
  [m default]
  `(fn [input#]
     (case input#
           ~@(mapcat identity m)
           ~default)))

(defn map->case-fn
  "Create a case statement that works the same as map m,
   with default for missing items."
  [m default]
  (eval (map->case-src m default)))

(defn case-test-constant
  "Generate a test constant that might appear in a case expression."
  []
  (gen/one-of gen/long
              gen/char
              gen/scalar
              gen/keyword))

(defspec case-spec
  map->case-fn
  [^{:tag (hash-map `case-test-constant long)} case-map
   ^keyword default]
  (doseq [k (keys case-map)]
    (assert= (% k) (case-map k) k))
  (doseq [k (range 100)]
    (when-not (contains? case-map k)
      (assert= (% k) default k))))

(defn encountered-in-walk
  "Returns a frequency map of items encountered while walking
   obj, using a walk-fn from e.g. clojure.walk."
  [walk-fn obj]
  (let [encountered (atom [])]
    (walk-fn
     (fn [obj] (swap! encountered conj obj) obj)
     obj)
    (frequencies @encountered)))

(defspec pre-and-post-walk-hit-same-things
  identity
  [^anything _]
  (assert= (encountered-in-walk prewalk %)
           (encountered-in-walk postwalk %)))
