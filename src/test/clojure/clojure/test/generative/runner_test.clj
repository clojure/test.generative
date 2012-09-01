(ns clojure.test.generative.runner-test
  (:use [clojure.test :only (deftest) :as ctest]
        [clojure.test.generative :only (is) :as test]
        [clojure.test.generative.event :as event])
  (:require [clojure.test.generative.runner :as runner]))

(deftest zero-inputs
  (runner/run-for
   {:name 'test.generative.runner-test/zero-inputs-example
    :fn (fn [] (assert false "unreachable"))
    :inputs (fn [] nil)}
   1
   100))

(deftest finite-inputs
  (let [adder (atom 0)
        inputs [[1] [2] [3]]]
    (runner/run-for
     {:name 'test.generative.runner-test/finite-inputs-example
      :fn (fn [n] (swap! adder + n))
      :inputs (fn [] [[1] [2] [3]])}
     1
     100)
    (is (= @adder (apply + (map first inputs))))))

(defmethod runner/var-tests ::custom-type
  [v]
  [{:name (event/fqname v)
    :fn @v
    :inputs (fn [] [[::only-input]])}])

(defn ^{::test/type ::custom-type}
  roll-your-own
  [x]
  (is (= x ::only-input)))
