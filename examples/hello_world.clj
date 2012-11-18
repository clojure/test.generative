(require
 '[clojure.data.generators :as gen]
 '[clojure.test.generative :as test :refer (defspec)]
 '[clojure.test.generative.event :as event]
 '[clojure.test.generative.runner :as runner])

;; generators have names that shadow core names of things generated
(gen/long)

;; generation is repeatable
(repeatedly
 2 
 #(binding [gen/*rnd* (java.util.Random. 42)]
   (gen/short)))

;; generation is composable
(gen/vec gen/short)

;; size is parameterized
(gen/vec gen/short 2)

;; size parameter can of course also be a generator
(gen/vec gen/short (gen/uniform 3 5))

;; generators are in scope as "types" in a defspec
(defspec longs-are-closed-under-increment
  inc ;; function under test
  [^long l]  ;; indicates generation via gen/long
  (assert (instance? Long %)))

;; specs are functions
(longs-are-closed-under-increment 4)

;; the next two steps are executed for you by the standard runner...

;; tests are extracted from vars
(def tests  (runner/var-tests #'longs-are-closed-under-increment))

;; install awesome console UI for tests
(event/install-default-handlers)

;; run test with some generated inputs
(runner/run-for
 (first tests)
 1
 1000)

;; actual test data structure exposes a lazy infinite seq of inputs
(take 2 ((:inputs (first tests))))

;; the real story behind that console ui
(reset! @#'event/handlers [])
(runner/run-for
 (first tests)
 1
 10)

