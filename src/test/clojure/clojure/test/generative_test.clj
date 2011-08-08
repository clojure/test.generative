;   Copyright (c) Rich Hickey, Stuart Halloway, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test.generative-test
  (:use clojure.test.generative)
  (:require [clojure.test.generative.generators :as gen]))

(defspec test-anything-goes
  identity
  [^anything s])

(defn little-number
  []
  (gen/uniform 0 3))

(defspec test-exhausted-generation-2
  (constantly nil)
  [^{:spec `little-number} arg1
   ^{:spec `little-number} arg2])

(def digits [0 1 2 3 4 5 6 7 8 9])

(defspec test-collection-based-generator
  identity
  [^{:spec `digits} d]
  (assert (<= 0 d 9)))

(defspec test-vec
  identity
  [^{:spec (vec long)} _]
  (assert (vector? %)))

(defspec test-weighted-generation
  identity
  [^{:spec (vec #(weighted {bool 8 long 1}))} _]
  (let [[longs bools] (split-with number? %)]
    (when (< 10 (count %))
      (assert (< (count longs) (count bools))))
    (assert (= (+ (count longs) (count bools))
               (count %)))))

(defspec integers-closed-over-addition
  (fn [a b] (+' a b))
  [^long a ^long b]
  (integer? %))

(defmacro long=
  "Returns true if all forms are equal, or if all forms
   throw an ArithmeticException"
  [& forms]
  `(=
    ~@(map
       (fn [form]
         `(try
           ~form
           (catch ArithmeticException e# :ArithmeticException)))
       forms)))


