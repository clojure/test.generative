;   Copyright (c) Rich Hickey, Stuart Halloway, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test.generative-test
  (:use clojure.test.generative
        [clojure.test :exclude [is]])
  (:require [clojure.test.generative.generators :as gen]
            [clojure.test.generative.event :as event]
            [clojure.test.generative.clojure-test :as clojure-test]))

(clojure-test/run-generative-tests)

(defspec test-anything-goes
  identity
  [^anything s])

(defspec test-weighted-generation
  identity
  [^{:tag (vec #(weighted {boolean 8 long 1}))} _]
  (let [[longs bools] (split-with number? %)]
    (is (= (+ (count longs) (count bools))
               (count %)))))

(defspec integers-closed-over-addition
  (fn [a b] (+' a b))
  [^long a ^long b]
  (is (integer? %)))





