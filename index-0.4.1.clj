{:namespaces
 ({:source-url
   "https://github.com/clojure/test.generative/blob/4d384cf40f29f8a044a90f5711e11564218715ac/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative/clojure.test.generative-api.html",
   :name "clojure.test.generative",
   :doc nil}
  {:source-url
   "https://github.com/clojure/test.generative/blob/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj",
   :wiki-url
   "http://clojure.github.com/test.generative/clojure.test.generative.event-api.html",
   :name "clojure.test.generative.event",
   :doc nil}
  {:source-url
   "https://github.com/clojure/test.generative/blob/03d113fde460bbf40601c3217aea2f2e6013a9ad/src/main/clojure/clojure/test/generative/io.clj",
   :wiki-url
   "http://clojure.github.com/test.generative/clojure.test.generative.io-api.html",
   :name "clojure.test.generative.io",
   :doc nil}
  {:source-url
   "https://github.com/clojure/test.generative/blob/1afc5a6befa1e92dbad22ca336a1188b37d6bd11/src/main/clojure/clojure/test/generative/logback.clj",
   :wiki-url
   "http://clojure.github.com/test.generative/clojure.test.generative.logback-api.html",
   :name "clojure.test.generative.logback",
   :doc nil}
  {:source-url
   "https://github.com/clojure/test.generative/blob/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative/clojure.test.generative.runner-api.html",
   :name "clojure.test.generative.runner",
   :doc nil}),
 :vars
 ({:arglists ([name fn-to-test args & validator-body]),
   :name "defspec",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/4d384cf40f29f8a044a90f5711e11564218715ac/src/main/clojure/clojure/test/generative.clj#L71",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/4d384cf40f29f8a044a90f5711e11564218715ac/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/defspec",
   :doc
   "Defines a function named name that expects args. The defined\nfunction binds '%' to the result of calling fn-to-test with args,\nand runs validator-body forms (if any), which have access to both\nargs and %. The defined function.\n\nArgs must have type hints (i.e. :tag metdata), which are\ninterpreted as instructions for generating test input\ndata. Unquoted names in type hints are resolved in the\nc.t.g.generators namespace, which has generator functions for\ncommon Clojure data types. For example, the following argument list\ndeclares that 'seed' is an int, and that 'iters' is an int in the\nuniform distribution from 1 to 100:\n\n    [^int seed ^{:tag (uniform 1 100)} iters]\n\nBackquoted names in an argument list are resolved in the current\nnamespace, allowing arbitrary generators, e.g.\n\n    [^{:tag `scary-word} word]\n\nThe function c.t.g.runner/run-iter takes a var naming a test, and runs\na single test iteration, generating inputs based on the arg type hints.",
   :var-type "macro",
   :line 71,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:arglists ([v] [v msg]),
   :name "is",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/4d384cf40f29f8a044a90f5711e11564218715ac/src/main/clojure/clojure/test/generative.clj#L57",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/4d384cf40f29f8a044a90f5711e11564218715ac/src/main/clojure/clojure/test/generative.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative/is",
   :doc
   "Assert that v is true, otherwise fail the current generative\ntest (with optional msg).",
   :var-type "macro",
   :line 57,
   :file "src/main/clojure/clojure/test/generative.clj"}
  {:arglists ([f]),
   :name "add-handler",
   :namespace "clojure.test.generative.event",
   :source-url
   "https://github.com/clojure/test.generative/blob/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj#L71",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.event/add-handler",
   :doc "Add a handler. Idempotent",
   :var-type "function",
   :line 71,
   :file "src/main/clojure/clojure/test/generative/event.clj"}
  {:arglists ([m k v] [m k v & kvs]),
   :name "assocnn",
   :namespace "clojure.test.generative.event",
   :source-url
   "https://github.com/clojure/test.generative/blob/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj#L47",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.event/assocnn",
   :doc "Assoc but drop nils",
   :var-type "function",
   :line 47,
   :file "src/main/clojure/clojure/test/generative/event.clj"}
  {:arglists ([]),
   :name "install-default-handlers",
   :namespace "clojure.test.generative.event",
   :source-url
   "https://github.com/clojure/test.generative/blob/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj#L110",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.event/install-default-handlers",
   :doc
   "Installs handler functions, a comma-delimited list of fn names, from\nclojure.test.generative.event.handlers. If none are specified, install\nc.t.g.io/console-reporter",
   :var-type "function",
   :line 110,
   :file "src/main/clojure/clojure/test/generative/event.clj"}
  {:arglists ([event-level enable-level]),
   :name "level-enabled?",
   :namespace "clojure.test.generative.event",
   :source-url
   "https://github.com/clojure/test.generative/blob/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj#L34",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.event/level-enabled?",
   :doc "Is the event-level enabled?",
   :var-type "function",
   :line 34,
   :file "src/main/clojure/clojure/test/generative/event.clj"}
  {:arglists ([fqname]),
   :name "load-var-val",
   :namespace "clojure.test.generative.event",
   :source-url
   "https://github.com/clojure/test.generative/blob/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj#L91",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.event/load-var-val",
   :doc "Load and return the value of a var",
   :var-type "function",
   :line 91,
   :file "src/main/clojure/clojure/test/generative/event.clj"}
  {:arglists ([env]),
   :name "local-bindings",
   :namespace "clojure.test.generative.event",
   :source-url
   "https://github.com/clojure/test.generative/blob/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj#L133",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.event/local-bindings",
   :doc
   "Produces a map of the names of local bindings to their values.",
   :var-type "function",
   :line 133,
   :file "src/main/clojure/clojure/test/generative/event.clj"}
  {:file "src/main/clojure/clojure/test/generative/event.clj",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj#L43",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.event/pid",
   :namespace "clojure.test.generative.event",
   :line 43,
   :var-type "var",
   :doc "Process id",
   :name "pid"}
  {:arglists ([f]),
   :name "remove-handler",
   :namespace "clojure.test.generative.event",
   :source-url
   "https://github.com/clojure/test.generative/blob/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj#L82",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.event/remove-handler",
   :doc "Remove a handler. Idempotent",
   :var-type "function",
   :line 82,
   :file "src/main/clojure/clojure/test/generative/event.clj"}
  {:arglists ([type & args]),
   :name "report-context",
   :namespace "clojure.test.generative.event",
   :source-url
   "https://github.com/clojure/test.generative/blob/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj#L139",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.event/report-context",
   :doc "Report event with contextual ns, file, line, bindings.",
   :var-type "macro",
   :line 139,
   :file "src/main/clojure/clojure/test/generative/event.clj"}
  {:arglists ([event]),
   :name "report-fn",
   :namespace "clojure.test.generative.event",
   :source-url
   "https://github.com/clojure/test.generative/blob/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj#L119",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.event/report-fn",
   :doc
   "Call the installed handlers for an event, or io/pprint if no handlers\ninstalled.",
   :var-type "function",
   :line 119,
   :file "src/main/clojure/clojure/test/generative/event.clj"}
  {:arglists ([handler & body]),
   :name "with-handler",
   :namespace "clojure.test.generative.event",
   :source-url
   "https://github.com/clojure/test.generative/blob/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj#L100",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/d1aeae81971171e7548f0cf68863f64d9cfab967/src/main/clojure/clojure/test/generative/event.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.event/with-handler",
   :doc "Run with handler temporarily installed.",
   :var-type "macro",
   :line 100,
   :file "src/main/clojure/clojure/test/generative/event.clj"}
  {:file "src/main/clojure/clojure/test/generative/io.clj",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/03d113fde460bbf40601c3217aea2f2e6013a9ad/src/main/clojure/clojure/test/generative/io.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/03d113fde460bbf40601c3217aea2f2e6013a9ad/src/main/clojure/clojure/test/generative/io.clj#L52",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.io/pprint",
   :namespace "clojure.test.generative.io",
   :line 52,
   :var-type "var",
   :doc "threadsafe pprint with event print settings",
   :name "pprint"}
  {:arglists ([s]),
   :name "pr-str",
   :namespace "clojure.test.generative.io",
   :source-url
   "https://github.com/clojure/test.generative/blob/03d113fde460bbf40601c3217aea2f2e6013a9ad/src/main/clojure/clojure/test/generative/io.clj#L41",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/03d113fde460bbf40601c3217aea2f2e6013a9ad/src/main/clojure/clojure/test/generative/io.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.io/pr-str",
   :doc "Print with event print settings",
   :var-type "function",
   :line 41,
   :file "src/main/clojure/clojure/test/generative/io.clj"}
  {:file "src/main/clojure/clojure/test/generative/io.clj",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/03d113fde460bbf40601c3217aea2f2e6013a9ad/src/main/clojure/clojure/test/generative/io.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/03d113fde460bbf40601c3217aea2f2e6013a9ad/src/main/clojure/clojure/test/generative/io.clj#L48",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.io/println",
   :namespace "clojure.test.generative.io",
   :line 48,
   :var-type "var",
   :doc "threadsafe print with event print settings",
   :name "println"}
  {:arglists ([f] [f agt]),
   :name "serialized",
   :namespace "clojure.test.generative.io",
   :source-url
   "https://github.com/clojure/test.generative/blob/03d113fde460bbf40601c3217aea2f2e6013a9ad/src/main/clojure/clojure/test/generative/io.clj#L22",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/03d113fde460bbf40601c3217aea2f2e6013a9ad/src/main/clojure/clojure/test/generative/io.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.io/serialized",
   :doc
   "Returns a function that calls f for side effects, async,\nserialized by an agent",
   :var-type "function",
   :line 22,
   :file "src/main/clojure/clojure/test/generative/io.clj"}
  {:arglists ([event]),
   :name "event->logback",
   :namespace "clojure.test.generative.logback",
   :source-url
   "https://github.com/clojure/test.generative/blob/1afc5a6befa1e92dbad22ca336a1188b37d6bd11/src/main/clojure/clojure/test/generative/logback.clj#L32",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/1afc5a6befa1e92dbad22ca336a1188b37d6bd11/src/main/clojure/clojure/test/generative/logback.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.logback/event->logback",
   :doc "Returns map with keys :logger, :event, :fire",
   :var-type "function",
   :line 32,
   :file "src/main/clojure/clojure/test/generative/logback.clj"}
  {:arglists ([& dirs]),
   :name "-main",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj#L306",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/-main",
   :doc
   "Command line entry point, runs all tests in dirs using clojure.test and\ntest.generative. Calls System.exit!",
   :var-type "function",
   :line 306,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([m]),
   :name "ct-adapter",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj#L97",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/ct-adapter",
   :doc "Adapt clojure.test event model to fire c.t.g events.",
   :var-type "function",
   :line 97,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:file "src/main/clojure/clojure/test/generative/runner.clj",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj#L32",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/ctevent->event",
   :namespace "clojure.test.generative.runner",
   :line 32,
   :var-type "multimethod",
   :doc "Convert a clojure.test reporting event to an event.",
   :name "ctevent->event"}
  {:arglists ([]),
   :name "failed!",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj#L26",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/failed!",
   :doc "Tell the runner that a test failed",
   :var-type "function",
   :line 26,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([nses threads msec]),
   :name "run-all-tests",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj#L240",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/run-all-tests",
   :doc "Run generative tests and clojure.test tests",
   :var-type "function",
   :line 240,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([tests nthreads test-msec]),
   :name "run-batch",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj#L158",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/run-batch",
   :doc
   "Run a batch of fs on nthreads each. Call each f repeatedly\nfor up to test-msec",
   :var-type "function",
   :line 158,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([test nthreads msec]),
   :name "run-for",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj#L115",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/run-for",
   :doc
   "Run f (presumably for side effects) repeatedly on n threads,\nuntil msec has passed or somebody signals *failed*",
   :var-type "function",
   :line 115,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([nses nthreads msec]),
   :name "run-generative-tests",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj#L211",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/run-generative-tests",
   :doc "Run generative tests.",
   :var-type "function",
   :line 211,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([name f input]),
   :name "run-iter",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj#L103",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/run-iter",
   :doc "Run a single test iteration",
   :var-type "function",
   :line 103,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([e]),
   :name "save",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj#L290",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/save",
   :doc "Save results at info level or higher, using store.",
   :var-type "function",
   :line 290,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:file "src/main/clojure/clojure/test/generative/runner.clj",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj#L280",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/store",
   :namespace "clojure.test.generative.runner",
   :line 280,
   :var-type "var",
   :doc "store data in .tg/{process-id}",
   :name "store"}
  {:arglists ([& dirs]),
   :name "test-dirs",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj#L296",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/test-dirs",
   :doc
   "Runs tests in dirs, returning a map of test lib keyword\nto summary data",
   :var-type "function",
   :line 296,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:file "src/main/clojure/clojure/test/generative/runner.clj",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj#L166",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/var-tests",
   :namespace "clojure.test.generative.runner",
   :line 166,
   :var-type "multimethod",
   :doc
   "TestContainer.tests support for vars. To create custom test\ntypes, define vars that have :c.t.g/type metadata, and then add\na matching var-tests method that returns a collection of tests.",
   :name "var-tests"}
  {:file "src/main/clojure/clojure/test/generative/runner.clj",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/f7d531ba4969ef21ddd898f78812624175ebd7cf/src/main/clojure/clojure/test/generative/runner.clj#L185",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/TestContainer",
   :namespace "clojure.test.generative.runner",
   :line 185,
   :var-type "protocol",
   :doc nil,
   :name "TestContainer"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/tests",
   :namespace "clojure.test.generative.runner",
   :var-type "function",
   :arglists ([_]),
   :doc
   "Returns a collection of generative tests, where a test is a map with\n   :name     ns-qualified symbol\n   :fn       fn to test\n   :inputs   fn returning a (possibly infinite!) sequence of inputs\n\nAll input generation should use and gen/*rnd*\nif a source of pseudo-randomness is needed.",
   :name "tests"})}
