;   Copyright (c) Rich Hickey, Stuart Halloway, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test.math-test
  (:use clojure.test.generative)
  (:require [clojure.test.generative.generators :as gen]))

(defn longable?
  [n]
  (try
   (long n)
   true
   (catch Exception _)))

(defspec integer-commutative-laws
  (partial map identity)
  [^long a ^long b]
  (if (longable? (+' a b))
    (assert (= (+ a b) (+ b a) (+' a b) (+' b a)))
    (assert (= (+' a b) (+' b a))))
  (if (longable? (*' a b))
    (assert (= (* a b) (* b a) (*' a b) (*' b a)))
    (assert (= (*' a b) (*' b a)))))

(defspec integer-associative-laws
  (partial map identity)
  [^long a ^long b ^long c]
  (if (every? longable? [(+' a b) (+' b c) (+' a b c)])
    (assert (= (+ (+ a b) c) (+ a (+ b c))
               (+' (+' a b) c) (+' a (+' b c))))
    (assert (= (+' (+' a b) c) (+' a (+' b c)))))
  (if (every? longable? [(*' a b) (*' b c) (*' a b c)])
    (assert (= (* (* a b) c) (* a (* b c))
               (*' (*' a b) c) (*' a (*' b c))))
    (assert (= (*' (*' a b) c) (*' a (*' b c))))))

(defspec integer-distributive-laws
  (partial map identity)
  [^long a ^long b ^long c]
  (if (every? longable? [(*' a (+' b c)) (+' (*' a b) (*' a c))])
    (assert (= (* a (+ b c)) (+ (* a b) (* a c))
               (*' a (+' b c)) (+' (*' a b) (*' a c))))
    (assert (= (*' a (+' b c)) (+' (*' a b) (*' a c))))))

(defspec addition-undoes-subtraction
  (partial map identity)
  [^long a ^long b]
  (if (longable? (-' a b))
    (assert (= a (-> a (- b) (+ b)))))
  (assert (= a (-> a (-' b) (+' b)))))

(defspec quotient-and-remainder
  (fn [a b] (sort [a b]))
  [^long a ^long b]
  (let [[a d] %
        q (quot a d)
        r (rem a d)]
    (assert (= a (+ (* q d) r)))))
