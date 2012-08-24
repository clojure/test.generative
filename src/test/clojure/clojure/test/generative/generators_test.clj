(ns clojure.test.generative.generators-test
  (:use clojure.test.generative
        [clojure.test :only (deftest) :as ct])
  (:require [clojure.test.generative.generators :as gen]))

(defspec test-repeatable-generation
  (constantly nil)
  [^int seed ^{:tag (uniform 1 100)} iters]
  (let [generator (fn []
                    (binding [gen/*rnd* (java.util.Random. seed)]
                      (into [] (repeatedly iters gen/anything))))
        gen-1 (generator)
        gen-2 (generator)]
    (dotimes [iter iters]
      (is (= (nth gen-1 iter)
             (nth gen-2 iter))))))

(defspec test-weighted-generation
  identity
  [^{:tag (vec #(weighted {boolean 8 long 1}))} _]
  (let [[longs bools] (split-with number? %)]
    (is (= (+ (count longs) (count bools))
           (count %)))))

(defspec test-shuffle
  gen/shuffle
  [^{:tag (vec long)} input]
  (is (= (sort input) (sort %))))
