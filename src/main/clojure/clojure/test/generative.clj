;   Copyright (c) Rich Hickey, Stuart Halloway, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns
    ^{:author "Stuart Halloway"
      :doc "Generative testing.

A defspec consists of a name, a function to be tested, an input spec,
and a validator:

  (defspec integers-closed-over-addition
    (fn [a b] (+' a b))                    ;; input fn
    [^long a ^long b]                     ;; input spec
    (assert (integer? %)))                ;; 0 or more validator forms

Given a var, namespace, or directory, you can run the tests for it:

  (test-vars #'integers-closed-over-addition)
  (test-namespaces 'clojure.test.generative-test)
  (test-dirs \"src/test/clojure\")

Succesful test output includes :iterations, :msec, and the :spec
(var name) for each test run:

  {:iterations 44645, :msec 1429,
   :spec #'clojure.test.generative-test/numbers-closed-over-addition}

By default, an entire test run lasts for 10 seconds, but you can change
the test run duration by binding *msec*:

  ;; go get a snack...
  (binding [*msec* 1000000]
    (test-dirs \"src/test/clojure\"))

The test run will attempt to use all your cores. You can change this
by binding *cores* to a different number.

  ;; no snack needed, your other N cores still available...
  (binding [*msec* 1000000
            *cores* 1]
    (test-dirs \"src/test/clojure\"))

If you want to see the generated inputs, bind *verbose* to true:

  (binding [*msec* 1000
            *verbose* true]
    (test-vars #'integers-closed-over-addition))

  {:inputs (2 -7312771435595740594)}
  {:inputs (2 6571825658936471225)}
  {:inputs (-5298357432219421459 1)}
  ;; etc.

If you want to generate test data without running any tests, you can
call generate-test-data.

  (take 5 (generate-test-data '[bool (uniform 0 10)]))
  => ((false 6) (true 2) (false 9) (true 2) (true 7))
  
Common scalar generators:

  bool byte long unsigned-long printable-ascii-char string
  symbol keyword
  scalar (chooses scalar type at random)  

You can generate collections with a spec of the form
  (coll-type item-type):

  (take 5 (generate-test-data '[(vec bool)]))
  => (([true false true true])
      ([true false true false false false])
      ([true false true true true true false true true])
      ([false true false true false])
      ([true false true false false false false false]))

Common collection generators:

  vec set hash-map list
  tuple (fixed size vector)
  collection (chooses collection type at random)

You should assume that the generators use a probability distribution
that is out of a specific test's control. If you prefer, you can
choose an explicit distribution. (e.g. uniform or geometric).

  (take 5 (generate-test-data '[(geometric 0.2)]))
  => ((1) (9) (11) (17) (3))

Generators that create collections (or strings etc.) take an
optional sizer parameter. Sizers can be explicit numbers, or fns
that return sizes per some distribution:

  (take 3 (generate-test-data '[(vec bool 3)]))
  => (([false false true])
      ([true false false])
      ([false false false]))

When a test fails, output includes the failed form, the iteration,
the error message, and the random seed.

  {:form (user/all-numbers-are-positive -6169532649852302182),
   :iteration 1,
   :seed 42,
   :error \"Assert failed: (pos? l)\"}

At this point, if you are in a REPL, you can rerun the failing test:

  (eval (first (failed-forms)))

Note the :seed value in the error output above. The *rnd* and *seed*
values live in the clojure.test.generative.generators namespace, and
can be used to conrol the randomization used to generate test data.
This can be useful in generating reproducible inputs.

The test runner tracks old inputs, and will not submit the same
input to the same fn twice in a single test run. If the test data
generator cannot generate enough unique values to drive the test for
the expected msec duration, it will stop when it runs out of values,
and mark the test run as :exhausted.

You can write your own generators, either fns or collections.

  (def num-piggies [1 2 3])
  (defn houses [] (gen/rand-nth [\"straw\" \"sticks\" \"brick\"]))

When you name a spec with a precending backtick (`), the test data
generator will look for a var in the current namespace, instead of
one of the built-in generators:

  (take 5 (generate-test-data [`piggy-ordinals `houses]))
  => ((\"first\" \"brick\")
      (\"third\" \"brick\")
      (\"third\" \"straw\")
      (\"first\" \"straw\")
      (\"third\" \"sticks\"))

"}
    clojure.test.generative
  (:use [clojure.tools.namespace :only (find-namespaces-in-dir)]
        [clojure.pprint :only (pprint)]
        [clojure.walk :only (prewalk)])
  (:require [clojure.test.generative.generators :as gen]))

(def last-report (agent nil))

(defn- report
  "Report a result. Thread-safe, unlike prn."
  [result]
  (send-off last-report (fn [_] (prn result) result)))

(defn- deep-take
  "Recursively convert any collections in form to (take n)
   of those collections. Used as a signature function in
   mostly-unique."
  [n form]
  (prewalk
   #(if (coll? %) (take n %) %)
   form))

(defn- mostly-unique
  "Create a mostly unique series of items taken from coll (which
   is often a lazy, infinite sequence). 

   The 'mostly' arises from keeping track of the *signature* of
   past values instead of the past values themselves. If you use
   identity as a signature function, 'mostly' becomes 'exactly',
   at the memory cost of holding the entire sequence of values.

   Returned sequence ends on nil or when retry-limit consecutive
   attempts to produce a novel value fail."
  [coll signature retry-limit]
  (let [next-val
        (fn [[_ coll past]]
          (loop [i 0
                 [candidate & more] coll]
            (when (and candidate (< i retry-limit))
              (let [sig (signature candidate)]
                (if-not (contains? past sig)
                  [candidate more (conj past sig)]
                  (recur (inc i) more))))))]
    (->> (take-while identity
                     (iterate next-val [nil coll #{}]))
         (drop 1)
         (map first))))

(defn- fully-qualified
  "Qualify a name used in :spec metadata. Unqualified names are
   interpreted in the 'clojure.test.generative.generators, except
   for the fn-building symbols fn and fn*."
  [spec]
  (let [ns (cond
            (#{'fn*} spec) nil
            (#{'fn} spec) 'clojure.core
            (namespace spec) (namespace spec)
            :else 'clojure.test.generative.generators)]
    (if ns
      (symbol (str ns) (name spec))
      spec)))

(defn- spec-compile
  "Compile spec into a function."
  [spec]
  (if
   (seq? spec) (eval `(fn [] ~spec))
   (let [resolved @(try (resolve spec)
                        (catch Exception _ (throw (IllegalArgumentException. (str "Unable to resolve " spec)))))]
     (if (coll? resolved)
       (fn [] (rand-nth resolved))
       resolved))))

(defn- dequote
  "Used by defspec to remove the backquotes used to call out
   user-namespaced forms."
  [form]
  (prewalk
   #(if (and (sequential? %)
             (= 2 (count %))
             (= 'quote (first %)))
      (second %)
      %)
   form))

(defn- tag->spec
  [arg]
  (let [m (meta arg)
        spec (dequote (if (:spec m) (:spec m) (:tag m)))]
    (with-meta arg (-> (assoc m :spec spec)
                       (dissoc :tag)))))

(defn generate-test-data
  "Generate a lazy, infinite sequence of inputs conforming to specs."
  [specs]
  (let [resolved (prewalk (fn [s] (if (symbol? s) (fully-qualified s) s)) (map tag->spec specs))
        compiled (into [] (map spec-compile resolved))]
    (repeatedly #(map (fn [f] (f)) compiled))))

(def ^:private failed-forms-ref
  (atom nil))

(defn failed-forms
  []
  @failed-forms-ref)

(defn run-test
  "Tests function f with argument specs specs for up to msec
   milliseconds. Returns a map of :msec and :iterations completed"
  [fname f {:keys [specs msec verbose]}]
  (binding [gen/*rnd* (java.util.Random. gen/*seed*)]
    (let [start (System/currentTimeMillis)
          times-up? (if msec
                      (fn [] (> (System/currentTimeMillis) (+ msec start)))
                      (constantly true))]
      (loop [count 0
             [args & more] (mostly-unique (generate-test-data specs) (partial deep-take 8) 100)]
        (if (or (nil? args) (times-up?))
          (merge {:msec (- (System/currentTimeMillis) start)
                  :iterations count}
                 (if (nil? args) {:exhausted true} {}))
          (do
            (try
             (when verbose (report {:inputs args}))
             (apply f args)
             (catch Throwable t
               (let [failed-form `(~fname ~@args)]
                 (report {:form failed-form
                          :iteration count
                          :seed gen/*seed*
                          :error (.getMessage t)})
                 (swap! failed-forms-ref conj failed-form))
               (throw t)))
            (recur (inc count) more)))))))

(defn spec?
  "Is var a spec"
  [v]
  (boolean (:spec (meta v))))

(defn- find-vars-in-namespaces
  [& nses]
  (apply require nses)
  (reduce (fn [v ns] (into v (vals (ns-interns ns)))) [] nses))

(defn- find-vars-in-dirs
  [& dirs]
  (->> (mapcat #(find-namespaces-in-dir (java.io.File. ^String %)) dirs)
       (apply find-vars-in-namespaces)))

(defn- find-tests-in-vars
  [& vars]
  (filter spec? vars))

(def ^:dynamic
  *msec*
  "Desired duration for a test run. Defaults to 10 seconds."
  10000)

(def ^:dynamic
  *verbose*
  "Set to true to print test inputs as they are used."
  false)

(def ^:dynamic
  *cores*
  "Number of cores to attempt to utilize in a test run. Defaults
   to the number of processors available."
  (.availableProcessors (Runtime/getRuntime)))

(def ^:dynamic
  *seeds*
  "Random seeds for different cores. (If you bind this to fewer seeds
   than *cores*, you will get less utilization and less testing!)"
  (into [] (range 42 (+ 1024 42))))

(defn- spec-name
  [s]
  (resolve (symbol (str (.getName (.ns s)) "/" (.sym s)))))

(defn- var-specs
  [v]
  (into [] (->> (meta v) :arglists first (map (comp :spec meta)))))

(defn- run-test-vars
  [vars]
  (into
   []
   (map
    (fn [seed]
      (let [nvars (count vars)]
        (future
         (binding [gen/*seed* seed]
           (doseq [v vars]
             (let [specs (var-specs v)]
               (report (merge {:spec (spec-name v) :seed seed}
                              (run-test (symbol (str (.getName (.ns v)) "/" (.sym v)))
                                        @v
                                        (merge {:msec (/ *msec* nvars) :verbose *verbose* :specs specs}))))))))))
    (take *cores* *seeds*))))

(defn test-vars
  "Run tests for all vars. Returns vector of *cores* futures."
  [& vars]
  (run-test-vars (apply find-tests-in-vars vars)))

(defn test-namespaces
  "Run tests for all vars in nses. Returns vector of *cores* futures."
  [& nses]
  (run-test-vars (apply find-tests-in-vars (apply find-vars-in-namespaces nses))))

(defn test-dirs
  "Run tests for all vars in source code files in dirs.
   Returns vector of *cores* futures."
  [& dirs]
  (run-test-vars (apply find-tests-in-vars (apply find-vars-in-dirs dirs))))

(defmacro retest
  "Reload nses, and then test-namespaces. For REPL use."
  [& nses]
  `(do
     (require :reload-all ~(clojure.core/vec nses))
     (test-namespaces ~@nses)))

(defmacro defspec
  "Define a spec (a fn) with name name. When you run a spec var
   with any of the test- fns, inputs for fn-to-test will be generated
   based on the :tag metadata on args in argspecs.
   After each iteration, the validator-body will execute, with
   bindings to the vars in argspecs, plus a binding of '%' to the
   result of calling fn-to-test.

   Multiple arities in argspecs are not supported."
  [name fn-to-test tagged-args & validator-body]
  (let [args (into [] (map #(with-meta % nil) tagged-args))
        spec-args (into [] (map tag->spec tagged-args))]
    `(defn ~(with-meta name (assoc (meta name) :spec true))
       ~spec-args
       (let [~'% (apply ~fn-to-test ~args)]
         ~@validator-body))))






