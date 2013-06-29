(ns clojure.test.generative.runner-test
  (:use [clojure.test.generative :as test]
        [clojure.test.generative.runner :as runner]))

(defmethod runner/var-tests ::custom-type
  [v]
  [{:name "roll-your-own"
    :f @v
    :inputs (fn [] [[::only-input]])}])

(defn ^{::test/type ::custom-type}
  roll-your-own
  [x]
  (assert (= x ::only-input)))
