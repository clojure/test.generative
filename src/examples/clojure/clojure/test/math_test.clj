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
  (:require [clojure.data.generators :as gen]))

(defn integer
  "Distribution of integers biased towards the small, but
   including all longs."
  []
  (gen/one-of #(gen/uniform -1 32) gen/byte gen/short gen/int gen/long))

(defn longable?
  [n]
  (try
   (long n)
   true
   (catch Exception _)))

(defspec integer-commutative-laws
  (partial map identity)
  [^{:tag `integer} a ^{:tag `integer} b]
  (if (longable? (+' a b))
    (assert (= (+ a b) (+ b a)
               (+' a b) (+' b a)
               (unchecked-add a b) (unchecked-add b a)))
    (assert (= (+' a b) (+' b a))))
  (if (longable? (*' a b))
    (assert (= (* a b) (* b a)
               (*' a b) (*' b a)
               (unchecked-multiply a b) (unchecked-multiply b a)))
    (assert (= (*' a b) (*' b a)))))

(defspec integer-associative-laws
  (partial map identity)
  [^{:tag `integer} a ^{:tag `integer} b ^{:tag `integer} c]
  (if (every? longable? [(+' a b) (+' b c) (+' a b c)])
    (assert (= (+ (+ a b) c) (+ a (+ b c))
               (+' (+' a b) c) (+' a (+' b c))
               (unchecked-add (unchecked-add a b) c) (unchecked-add a (unchecked-add b c))))
    (assert (= (+' (+' a b) c) (+' a (+' b c))
               (+ (+ (bigint a) b) c) (+ a (+ (bigint b) c)))))
  (if (every? longable? [(*' a b) (*' b c) (*' a b c)])
    (assert (= (* (* a b) c) (* a (* b c))
               (*' (*' a b) c) (*' a (*' b c))
               (unchecked-multiply (unchecked-multiply a b) c) (unchecked-multiply a (unchecked-multiply b c))))
    (assert (= (*' (*' a b) c) (*' a (*' b c))
               (* (* (bigint a) b) c) (* a (* (bigint b) c))))))

(defspec integer-distributive-laws
  (partial map identity)
  [^{:tag `integer} a ^{:tag `integer} b ^{:tag `integer} c]
  (if (every? longable? [(*' a (+' b c)) (+' (*' a b) (*' a c))
                         (*' a b) (*' a c) (+' b c)])
    (assert (= (* a (+ b c)) (+ (* a b) (* a c))
               (*' a (+' b c)) (+' (*' a b) (*' a c))
               (unchecked-multiply a (+' b c)) (+' (unchecked-multiply a b) (unchecked-multiply a c))))
    (assert (= (*' a (+' b c)) (+' (*' a b) (*' a c))
               (* a (+ (bigint b) c)) (+ (* (bigint a) b) (* (bigint a) c))))))

(defspec addition-undoes-subtraction
  (partial map identity)
  [^{:tag `integer} a ^{:tag `integer} b]
  (if (longable? (-' a b))
    (assert (= a
               (-> a (- b) (+ b))
               (-> a (unchecked-subtract b) (unchecked-add b)))))
  (assert (= a
             (-> a (-' b) (+' b)))))

(defspec quotient-and-remainder
  (fn [a b] (sort [a b]))
  [^{:tag `integer} a ^{:tag `integer} b]
  (when-not (zero? (second %))
    (let [[a d] %
          q (quot a d)
          r (rem a d)]
      (assert (= a
                 (+ (* q d) r)
                 (unchecked-add (unchecked-multiply q d) r))))))
