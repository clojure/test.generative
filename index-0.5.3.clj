{:namespaces
 ({:source-url
   "https://github.com/clojure/test.generative/blob/fe4922b988df76fff56e287eec41e7d5183b7a40/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative/clojure.test.generative-api.html",
   :name "clojure.test.generative",
   :doc nil}
  {:source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative/clojure.test.generative.runner-api.html",
   :name "clojure.test.generative.runner",
   :doc nil}),
 :vars
 ({:arglists ([name fn-to-test args & validator-body]),
   :name "defspec",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/fe4922b988df76fff56e287eec41e7d5183b7a40/src/main/clojure/clojure/test/generative.clj#L46",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/fe4922b988df76fff56e287eec41e7d5183b7a40/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/defspec",
   :doc
   "Defines a function named name that expects args. The defined\nfunction binds '%' to the result of calling fn-to-test with args,\nand runs validator-body forms (if any), which have access to both\nargs and %. The defined function.\n\nArgs must have type hints (i.e. :tag metadata), which are\ninterpreted as instructions for generating test input\ndata. Unquoted names in type hints are resolved in the\nc.t.g.generators namespace, which has generator functions for\ncommon Clojure data types. For example, the following argument list\ndeclares that 'seed' is an int, and that 'iters' is an int in the\nuniform distribution from 1 to 100:\n\n    [^int seed ^{:tag (uniform 1 100)} iters]\n\nBackquoted names in an argument list are resolved in the current\nnamespace, allowing arbitrary generators, e.g.\n\n    [^{:tag `scary-word} word]\n\nThe function c.t.g.runner/run-iter takes a var naming a test, and runs\na single test iteration, generating inputs based on the arg type hints.",
   :var-type "macro",
   :line 46,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:arglists ([& dirs]),
   :name "-main",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj#L203",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/-main",
   :doc "Command line entry point. Calls System.exit!",
   :var-type "function",
   :line 203,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([]),
   :name "config",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj#L28",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/config",
   :doc "Returns runner configuration derived from system properties.",
   :var-type "function",
   :line 28,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([dirs]),
   :name "dir-tests",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj#L155",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/dir-tests",
   :doc "Returns all tests in dirs",
   :var-type "function",
   :line 155,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([test]),
   :name "inputs",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj#L164",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/inputs",
   :doc
   "For interactive use.  Returns an infinite sequence of inputs for\na test.",
   :var-type "function",
   :line 164,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:file "src/main/clojure/clojure/test/generative/runner.clj",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj#L143",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/prf",
   :namespace "clojure.test.generative.runner",
   :line 143,
   :var-type "var",
   :doc "Print and flush.",
   :name "prf"}
  {:arglists ([nthreads msec & test-containers]),
   :name "run",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj#L170",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/run",
   :doc
   "Designed for interactive use.  Prints results to *out* and throws\non first failure encountered.",
   :var-type "function",
   :line 170,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([{:keys [nthreads msec progress]} tests]),
   :name "run-suite",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj#L181",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/run-suite",
   :doc "Designed for test suite use.",
   :var-type "function",
   :line 181,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([f] [f agt]),
   :name "serialized",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj#L128",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/635f98e948e6ec585fb575809b1d62d9593ae402/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/serialized",
   :doc
   "Returns a function that calls f for side effects, async,\nserialized by an agent",
   :var-type "function",
   :line 128,
   :file "src/main/clojure/clojure/test/generative/runner.clj"})}
