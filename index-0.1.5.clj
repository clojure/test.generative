{:namespaces
 ({:source-url
   "https://github.com/clojure/test.generative/blob/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative/clojure.test.generative-api.html",
   :name "clojure.test.generative",
   :author "Stuart Halloway",
   :doc
   "Generative testing.\n\nA defspec consists of a name, a function to be tested, an input spec,\nand a validator:\n\n  (defspec integers-closed-over-addition\n    (fn [a b] (+' a b))                    ;; input fn\n    [^long a ^long b]                     ;; input spec\n    (assert (integer? %)))                ;; 0 or more validator forms\n\nGiven a var, namespace, or directory, you can run the tests for it:\n\n  (test-vars #'integers-closed-over-addition)\n  (test-namespaces 'clojure.test.generative-test)\n  (test-dirs \"src/test/clojure\")\n\nSuccesful test output includes :iterations, :msec, and the :var for\neach test run:\n\n  {:iterations 44645, :msec 1429,\n   :var #'clojure.test.generative-test/numbers-closed-over-addition}\n\nBy default, an entire test run lasts for 10 seconds, but you can change\nthe test run duration by binding *msec*:\n\n  ;; go get a snack...\n  (binding [*msec* 1000000]\n    (test-dirs \"src/test/clojure\"))\n\nThe test run will attempt to use all your cores. You can change this\nby binding *cores* to a different number.\n\n  ;; no snack needed, your other N cores still available...\n  (binding [*msec* 1000000\n            *cores* 1]\n    (test-dirs \"src/test/clojure\"))\n\nIf you want to see the generated inputs, bind *verbose* to true:\n\n  (binding [*msec* 1000\n            *verbose* true]\n    (test-vars #'integers-closed-over-addition))\n\n  {:inputs (2 -7312771435595740594)}\n  {:inputs (2 6571825658936471225)}\n  {:inputs (-5298357432219421459 1)}\n  ;; etc.\n\nIf you want to generate test data without running any tests, you can\ncall generate-test-data.\n\n  (take 5 (generate-test-data '[bool (uniform 0 10)]))\n  => ((false 6) (true 2) (false 9) (true 2) (true 7))\n  \nCommon scalar generators:\n\n  bool byte long printable-ascii-char string\n  symbol keyword\n  scalar (chooses scalar type at random)  \n\nYou can generate collections with a spec of the form\n  (coll-type item-type):\n\n  (take 5 (generate-test-data '[(vec bool)]))\n  => (([true false true true])\n      ([true false true false false false])\n      ([true false true true true true false true true])\n      ([false true false true false])\n      ([true false true false false false false false]))\n\nCommon collection generators:\n\n  vec set hash-map list\n  tuple (fixed size vector)\n  collection (chooses collection type at random)\n\nYou should assume that the generators use a probability distribution\nthat is out of a specific test's control. If you prefer, you can\nchoose an explicit distribution. (e.g. uniform or geometric).\n\n  (take 5 (generate-test-data '[(geometric 0.2)]))\n  => ((1) (9) (11) (17) (3))\n\nGenerators that create collections (or strings etc.) take an\noptional sizer parameter. Sizers can be explicit numbers, or fns\nthat return sizes per some distribution:\n\n  (take 3 (generate-test-data '[(vec bool 3)]))\n  => (([false false true])\n      ([true false false])\n      ([false false false]))\n\nWhen a test fails, output includes the failed form, the iteration,\nthe error message, and the random seed.\n\n  {:form (user/all-numbers-are-positive -6169532649852302182),\n   :iteration 1,\n   :seed 42,\n   :error \"Assert failed: (pos? l)\"}\n\nNote the :seed value in the error output above. The *rnd* and *seed*\nvalues live in the clojure.test.generative.generators namespace, and\ncan be used to conrol the randomization used to generate test data.\nThis can be useful in generating reproducible inputs.\n\nThe test runner tracks old inputs, and tries not to submit the same\ninput to the same fn twice in a single test run. If the test data\ngenerator cannot generate enough unique values to drive the test for\nthe expected msec duration, it will stop when it runs out of values,\nand mark the test run as :exhausted.\n\nYou can write your own generators, either fns or collections.\n\n  (def num-piggies [1 2 3])\n  (defn houses [] (gen/rand-nth [\"straw\" \"sticks\" \"brick\"]))\n\nWhen you name a spec with a precending backtick (`), the test data\ngenerator will look for a var in the current namespace, instead of\none of the built-in generators:\n\n  (take 5 (generate-test-data [`piggy-ordinals `houses]))\n  => ((\"first\" \"brick\")\n      (\"third\" \"brick\")\n      (\"third\" \"straw\")\n      (\"first\" \"straw\")\n      (\"third\" \"sticks\"))"}
  {:source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative/clojure.test.generative.generators-api.html",
   :name "clojure.test.generative.generators",
   :author "Stuart Halloway",
   :doc
   "Generators for clojure.test.generative.\n\nThe functions in this namespace define the DSL for generator\nspecs. You do not want to use this namespace directly in most\ncases, because\n\n1. Spec compilation uses these fns for you automatically.\n2. There are lot of collisions with clojure.core!"}),
 :vars
 ({:name "*cores*",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj#L320",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/*cores*",
   :doc
   "Number of cores to attempt to utilize in a test run. Defaults\nto the number of processors available.",
   :var-type "var",
   :line 320,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:name "*msec*",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj#L310",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/*msec*",
   :doc "Desired duration for a test run. Defaults to 10 seconds.",
   :var-type "var",
   :line 310,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:name "*seeds*",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj#L326",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/*seeds*",
   :doc
   "Random seeds for different cores. (If you bind this to fewer seeds\nthan *cores*, you will get less utilization and less testing!)",
   :var-type "var",
   :line 326,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:name "*verbose*",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj#L315",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/*verbose*",
   :doc "Set to true to print test inputs as they are used.",
   :var-type "var",
   :line 315,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:arglists ([name fn-to-test args & validator-body]),
   :name "defspec",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj#L384",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/defspec",
   :doc
   "Define a spec (a fn) with name name. When you run a spec var\nwith any of the test- fns, inputs for fn-to-test will be generated\nbased on the :tag metadata on args in argspecs.\nAfter each iteration, the validator-body will execute, with\nbindings to the vars in argspecs, plus a binding of '%' to the\nresult of calling fn-to-test.\n\nMultiple arities in argspecs are not supported.",
   :var-type "macro",
   :line 384,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:arglists ([tags]),
   :name "generate-test-data",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj#L239",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/generate-test-data",
   :doc "Generate infinite sequece of test data based on tags.",
   :var-type "function",
   :line 239,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:arglists ([result]),
   :name "report",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj#L153",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/report",
   :doc "Report a result. Thread-safe, unlike prn.",
   :var-type "function",
   :line 153,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:file "src/main/clojure/clojure/test/generative.clj",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj#L148",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/report-fn",
   :namespace "clojure.test.generative",
   :line 148,
   :var-type "var",
   :doc
   "Reporting function, defaults to prn.\nreset! val to customize reporting.",
   :name "report-fn"}
  {:arglists ([& nses]),
   :name "retest",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj#L377",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/retest",
   :doc "Reload nses, and then test-namespaces. For REPL use.",
   :var-type "macro",
   :line 377,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:arglists ([& {:keys [fname f gen-inputs msec verbose]}]),
   :name "run-test",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj#L260",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/run-test",
   :doc
   "Tests function f with generator gen for up to msec\nmilliseconds. Returns a map of :msec and :iterations completed",
   :var-type "function",
   :line 260,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:arglists ([v]),
   :name "spec?",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj#L290",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/spec?",
   :doc "Is var a spec",
   :var-type "function",
   :line 290,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:arglists ([tags]),
   :name "test-data-generator",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj#L248",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/test-data-generator",
   :doc "Create a test data generator based on tags.",
   :var-type "function",
   :line 248,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:arglists ([& dirs]),
   :name "test-dirs",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj#L371",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/test-dirs",
   :doc
   "Run tests for all vars in source code files in dirs.\nReturns vector of *cores* futures.",
   :var-type "function",
   :line 371,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:arglists ([& nses]),
   :name "test-namespaces",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj#L366",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/test-namespaces",
   :doc
   "Run tests for all vars in nses. Returns vector of *cores* futures.",
   :var-type "function",
   :line 366,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:arglists ([& vars]),
   :name "test-vars",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj#L361",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/fd27a671815b06b5409b8c59a5645c89f35c31ce/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/test-vars",
   :doc "Run tests for all vars. Returns vector of *cores* futures.",
   :var-type "function",
   :line 361,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:name "*rnd*",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L23",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/*rnd*",
   :doc
   "Random instance for use in generators. By consistently using this\ninstance you can get a repeatable basis for tests.",
   :var-type "var",
   :line 23,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:name "*seed*",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L29",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/*seed*",
   :doc "Seed for random generator, rebind this to change your basis.",
   :var-type "var",
   :line 29,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([]),
   :name "anything",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L272",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/anything",
   :doc "Returns a random scalar or collection.",
   :var-type "function",
   :line 272,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([]),
   :name "boolean",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L121",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/boolean",
   :doc "Returns a random bool.",
   :var-type "function",
   :line 121,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([f] [f sizer]),
   :name "boolean-array",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L168",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/boolean-array",
   :doc "Create an array with elements from f and sized from sizer.",
   :var-type "function",
   :line 168,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([]),
   :name "byte",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L116",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/byte",
   :doc "Returns a random long in the byte range.",
   :var-type "function",
   :line 116,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([f] [f sizer]),
   :name "byte-array",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L168",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/byte-array",
   :doc "Create an array with elements from f and sized from sizer.",
   :var-type "function",
   :line 168,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([]),
   :name "char",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L132",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/char",
   :doc "Returns a random character in the range 0-65536.",
   :var-type "function",
   :line 132,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([f] [f sizer]),
   :name "char-array",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L168",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/char-array",
   :doc "Create an array with elements from f and sized from sizer.",
   :var-type "function",
   :line 168,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([]),
   :name "collection",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L266",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/collection",
   :doc "Returns a random collection of scalar elements.",
   :var-type "function",
   :line 266,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([]),
   :name "default-sizer",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L137",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/default-sizer",
   :doc
   "Default sizer used to run tests. If you want a specific distribution,\ncreate your own and pass it to a fn that wants a sizer.",
   :var-type "function",
   :line 137,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([]),
   :name "double",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L67",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/double",
   :doc "Generate a double.",
   :var-type "function",
   :line 67,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([f] [f sizer]),
   :name "double-array",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L168",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/double-array",
   :doc "Create an array with elements from f and sized from sizer.",
   :var-type "function",
   :line 168,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([]),
   :name "float",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L62",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/float",
   :doc "Generate a float.",
   :var-type "function",
   :line 62,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([f] [f sizer]),
   :name "float-array",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L168",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/float-array",
   :doc "Create an array with elements from f and sized from sizer.",
   :var-type "function",
   :line 168,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([p]),
   :name "geometric",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L49",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/geometric",
   :doc "Geometric distribution with mean 1/p.",
   :var-type "function",
   :line 49,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([fk fv] [fk fv sizer]),
   :name "hash-map",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L191",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/hash-map",
   :doc
   "Create a hash-map with keys from fk, vals from fv, and\nsized from sizer.",
   :var-type "function",
   :line 191,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([f] [f sizer]),
   :name "int-array",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L168",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/int-array",
   :doc "Create an array with elements from f and sized from sizer.",
   :var-type "function",
   :line 168,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([] [sizer]),
   :name "keyword",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L240",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/keyword",
   :doc "Create a keyword sized from sizer.",
   :var-type "function",
   :line 240,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([f] [f sizer]),
   :name "list",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L143",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/list",
   :doc "Create a list with elements from f and sized from sizer.",
   :var-type "function",
   :line 143,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:file "src/main/clojure/clojure/test/generative/generators.clj",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L102",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/long",
   :namespace "clojure.test.generative.generators",
   :line 102,
   :var-type "var",
   :doc "Returns a random long. Same as uniform.",
   :name "long"}
  {:arglists ([f] [f sizer]),
   :name "long-array",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L168",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/long-array",
   :doc "Create an array with elements from f and sized from sizer.",
   :var-type "function",
   :line 168,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([& specs]),
   :name "one-of",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L97",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/one-of",
   :doc
   "Generates one of the specs passed in, with equal probability.",
   :var-type "function",
   :line 97,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([]),
   :name "printable-ascii-char",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L127",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/printable-ascii-char",
   :doc "Returns a random printable ascii character.",
   :var-type "function",
   :line 127,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([coll]),
   :name "rand-nth",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L72",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/rand-nth",
   :doc
   "Replacement of core/rand-nth that allows control of the\nrandomization basis (through binding *seed*).",
   :var-type "function",
   :line 72,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([sizer f]),
   :name "reps",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L41",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/reps",
   :doc "Returns sizer repetitions of f (or (f) if f is a fn).",
   :var-type "function",
   :line 41,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([]),
   :name "scalar",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L256",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/scalar",
   :doc "Returns a random scalar.",
   :var-type "function",
   :line 256,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([f] [f sizer]),
   :name "set",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L185",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/set",
   :doc "Create a set with elements from f and sized from sizer.",
   :var-type "function",
   :line 185,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([f] [f sizer]),
   :name "short-array",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L168",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/short-array",
   :doc "Create an array with elements from f and sized from sizer.",
   :var-type "function",
   :line 168,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([] [f] [f sizer]),
   :name "string",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L200",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/string",
   :doc "Create a string with chars from v and sized from sizer.",
   :var-type "function",
   :line 200,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([] [sizer]),
   :name "symbol",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L234",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/symbol",
   :doc "Create a symbol sized from sizer.",
   :var-type "function",
   :line 234,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([& generators]),
   :name "tuple",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L78",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/tuple",
   :doc "Generate a tuple with one element from each generator.",
   :var-type "function",
   :line 78,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([] [lo hi]),
   :name "uniform",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L55",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/uniform",
   :doc
   "Uniform distribution from lo (inclusive) to high (exclusive).\nDefaults to range of Java long.",
   :var-type "function",
   :line 55,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([f] [f sizer]),
   :name "vec",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L179",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/vec",
   :doc "Create a vec with elements from f and sized from sizer.",
   :var-type "function",
   :line 179,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([m]),
   :name "weighted",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj#L83",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/6d1c8ffa31b31b6e84b188a223caae7ca993f885/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/weighted",
   :doc
   "Given a map of generators and weights, return a value from one of\nthe generators, selecting generator based on weights.",
   :var-type "function",
   :line 83,
   :file "src/main/clojure/clojure/test/generative/generators.clj"})}
