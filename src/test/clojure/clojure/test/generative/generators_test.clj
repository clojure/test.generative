(ns clojure.test.generative.generators-test
  (:use clojure.test.generative)
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
