{:namespaces
 ({:doc nil,
   :name "clojure.test.generative",
   :wiki-url "http://clojure.github.io/test.generative/index.html",
   :source-url
   "https://github.com/clojure/test.generative/blob/fe4922b988df76fff56e287eec41e7d5183b7a40/src/main/clojure/clojure/test/generative.clj"}
  {:doc nil,
   :name "clojure.test.generative.runner",
   :wiki-url
   "http://clojure.github.io/test.generative/index.html#clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj"}),
 :vars
 ({:raw-source-url
   "https://github.com/clojure/test.generative/raw/fe4922b988df76fff56e287eec41e7d5183b7a40/src/main/clojure/clojure/test/generative.clj",
   :name "defspec",
   :file "src/main/clojure/clojure/test/generative.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/fe4922b988df76fff56e287eec41e7d5183b7a40/src/main/clojure/clojure/test/generative.clj#L46",
   :line 46,
   :var-type "macro",
   :arglists ([name fn-to-test args & validator-body]),
   :doc
   "Defines a function named name that expects args. The defined\nfunction binds '%' to the result of calling fn-to-test with args,\nand runs validator-body forms (if any), which have access to both\nargs and %. The defined function.\n\nArgs must have type hints (i.e. :tag metadata), which are\ninterpreted as instructions for generating test input\ndata. Unquoted names in type hints are resolved in the\nc.t.g.generators namespace, which has generator functions for\ncommon Clojure data types. For example, the following argument list\ndeclares that 'seed' is an int, and that 'iters' is an int in the\nuniform distribution from 1 to 100:\n\n    [^int seed ^{:tag (uniform 1 100)} iters]\n\nBackquoted names in an argument list are resolved in the current\nnamespace, allowing arbitrary generators, e.g.\n\n    [^{:tag `scary-word} word]\n\nThe function c.t.g.runner/run-iter takes a var naming a test, and runs\na single test iteration, generating inputs based on the arg type hints.",
   :namespace "clojure.test.generative",
   :wiki-url
   "http://clojure.github.io/test.generative//index.html#clojure.test.generative/defspec"}
  {:raw-source-url
   "https://github.com/clojure/test.generative/raw/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :name "-main",
   :file "src/main/clojure/clojure/test/generative/runner.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj#L203",
   :line 203,
   :var-type "function",
   :arglists ([& dirs]),
   :doc "Command line entry point. Calls System.exit!",
   :namespace "clojure.test.generative.runner",
   :wiki-url
   "http://clojure.github.io/test.generative//index.html#clojure.test.generative.runner/-main"}
  {:raw-source-url
   "https://github.com/clojure/test.generative/raw/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :name "config",
   :file "src/main/clojure/clojure/test/generative/runner.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj#L28",
   :line 28,
   :var-type "function",
   :arglists ([]),
   :doc "Returns runner configuration derived from system properties.",
   :namespace "clojure.test.generative.runner",
   :wiki-url
   "http://clojure.github.io/test.generative//index.html#clojure.test.generative.runner/config"}
  {:raw-source-url
   "https://github.com/clojure/test.generative/raw/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :name "dir-tests",
   :file "src/main/clojure/clojure/test/generative/runner.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj#L155",
   :line 155,
   :var-type "function",
   :arglists ([dirs]),
   :doc "Returns all tests in dirs",
   :namespace "clojure.test.generative.runner",
   :wiki-url
   "http://clojure.github.io/test.generative//index.html#clojure.test.generative.runner/dir-tests"}
  {:raw-source-url
   "https://github.com/clojure/test.generative/raw/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :name "inputs",
   :file "src/main/clojure/clojure/test/generative/runner.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj#L164",
   :line 164,
   :var-type "function",
   :arglists ([test]),
   :doc
   "For interactive use.  Returns an infinite sequence of inputs for\na test.",
   :namespace "clojure.test.generative.runner",
   :wiki-url
   "http://clojure.github.io/test.generative//index.html#clojure.test.generative.runner/inputs"}
  {:raw-source-url
   "https://github.com/clojure/test.generative/raw/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :name "prf",
   :file "src/main/clojure/clojure/test/generative/runner.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj#L143",
   :line 143,
   :var-type "var",
   :arglists nil,
   :doc "Print and flush.",
   :namespace "clojure.test.generative.runner",
   :wiki-url
   "http://clojure.github.io/test.generative//index.html#clojure.test.generative.runner/prf"}
  {:raw-source-url
   "https://github.com/clojure/test.generative/raw/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :name "run",
   :file "src/main/clojure/clojure/test/generative/runner.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj#L170",
   :line 170,
   :var-type "function",
   :arglists ([nthreads msec & test-containers]),
   :doc
   "Designed for interactive use.  Prints results to *out* and throws\non first failure encountered.",
   :namespace "clojure.test.generative.runner",
   :wiki-url
   "http://clojure.github.io/test.generative//index.html#clojure.test.generative.runner/run"}
  {:raw-source-url
   "https://github.com/clojure/test.generative/raw/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :name "run-suite",
   :file "src/main/clojure/clojure/test/generative/runner.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj#L181",
   :line 181,
   :var-type "function",
   :arglists ([{:keys [nthreads msec progress]} tests]),
   :doc "Designed for test suite use.",
   :namespace "clojure.test.generative.runner",
   :wiki-url
   "http://clojure.github.io/test.generative//index.html#clojure.test.generative.runner/run-suite"}
  {:raw-source-url
   "https://github.com/clojure/test.generative/raw/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :name "serialized",
   :file "src/main/clojure/clojure/test/generative/runner.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj#L128",
   :line 128,
   :var-type "function",
   :arglists ([f] [f agt]),
   :doc
   "Returns a function that calls f for side effects, async,\nserialized by an agent",
   :namespace "clojure.test.generative.runner",
   :wiki-url
   "http://clojure.github.io/test.generative//index.html#clojure.test.generative.runner/serialized"})}
