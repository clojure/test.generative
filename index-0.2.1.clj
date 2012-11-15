{:namespaces
 ({:source-url
   "https://github.com/clojure/test.generative/blob/9db921b9e1b9a3b421770e19bb06e8ccc84d798c/src/main/clojure/clojure/test/generative.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative/clojure.test.generative.generators-api.html",
   :name "clojure.test.generative.generators",
   :author "Stuart Halloway",
   :doc
   "Generators for clojure.test.generative.\n\nThe functions in this namespace define the DSL for generator\nspecs. You do not want to use this namespace directly in most\ncases, because\n\n1. Spec compilation uses these fns for you automatically.\n2. There are lot of collisions with clojure.core!"}
  {:source-url
   "https://github.com/clojure/test.generative/blob/03d113fde460bbf40601c3217aea2f2e6013a9ad/src/main/clojure/clojure/test/generative/io.clj",
   :wiki-url
   "http://clojure.github.com/test.generative/clojure.test.generative.io-api.html",
   :name "clojure.test.generative.io",
   :doc nil}
  {:source-url
   "https://github.com/clojure/test.generative/blob/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative/clojure.test.generative.runner-api.html",
   :name "clojure.test.generative.runner",
   :doc nil}),
 :vars
 ({:arglists ([name fn-to-test args & validator-body]),
   :name "defspec",
   :namespace "clojure.test.generative",
   :source-url
   "https://github.com/clojure/test.generative/blob/9db921b9e1b9a3b421770e19bb06e8ccc84d798c/src/main/clojure/clojure/test/generative.clj#L71",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/9db921b9e1b9a3b421770e19bb06e8ccc84d798c/src/main/clojure/clojure/test/generative.clj",
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
   "https://github.com/clojure/test.generative/blob/9db921b9e1b9a3b421770e19bb06e8ccc84d798c/src/main/clojure/clojure/test/generative.clj#L57",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/9db921b9e1b9a3b421770e19bb06e8ccc84d798c/src/main/clojure/clojure/test/generative.clj",
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
  {:name "*rnd*",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L23",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L29",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L272",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L121",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L168",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L116",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L168",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L132",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L168",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L266",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L137",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L67",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L168",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L62",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L168",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L49",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L191",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L168",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L240",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L143",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/list",
   :doc "Create a list with elements from f and sized from sizer.",
   :var-type "function",
   :line 143,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:file "src/main/clojure/clojure/test/generative/generators.clj",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L102",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L168",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L97",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L127",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L72",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L41",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L256",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L185",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L168",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/short-array",
   :doc "Create an array with elements from f and sized from sizer.",
   :var-type "function",
   :line 168,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([coll]),
   :name "shuffle",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L290",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/shuffle",
   :doc "Shuffle coll",
   :var-type "function",
   :line 290,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
  {:arglists ([] [f] [f sizer]),
   :name "string",
   :namespace "clojure.test.generative.generators",
   :source-url
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L200",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L234",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L78",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L55",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L179",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
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
   "https://github.com/clojure/test.generative/blob/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj#L83",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/5a59bf0fcddde12397d575e40aea3487f1f61825/src/main/clojure/clojure/test/generative/generators.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.generators/weighted",
   :doc
   "Given a map of generators and weights, return a value from one of\nthe generators, selecting generator based on weights.",
   :var-type "function",
   :line 83,
   :file "src/main/clojure/clojure/test/generative/generators.clj"}
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
  {:arglists ([& dirs]),
   :name "-main",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj#L307",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/-main",
   :doc
   "Command line entry point, runs all tests in dirs using clojure.test and\ntest.generative. Calls System.exit!",
   :var-type "function",
   :line 307,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([m]),
   :name "ct-adapter",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj#L97",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/ct-adapter",
   :doc "Adapt clojure.test event model to fire c.t.g events.",
   :var-type "function",
   :line 97,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([]),
   :name "failed!",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj#L26",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj",
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
   "https://github.com/clojure/test.generative/blob/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj#L241",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/run-all-tests",
   :doc "Run generative tests and clojure.test tests",
   :var-type "function",
   :line 241,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([tests nthreads test-msec]),
   :name "run-batch",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj#L159",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/run-batch",
   :doc
   "Run a batch of fs on nthreads each. Call each f repeatedly\nfor up to test-msec",
   :var-type "function",
   :line 159,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([test nthreads msec]),
   :name "run-for",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj#L115",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj",
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
   "https://github.com/clojure/test.generative/blob/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj#L212",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/run-generative-tests",
   :doc "Run generative tests.",
   :var-type "function",
   :line 212,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:arglists ([name f input]),
   :name "run-iter",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj#L103",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj",
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
   "https://github.com/clojure/test.generative/blob/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj#L291",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/save",
   :doc "Save results at info level or higher, using store.",
   :var-type "function",
   :line 291,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:file "src/main/clojure/clojure/test/generative/runner.clj",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj#L281",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/store",
   :namespace "clojure.test.generative.runner",
   :line 281,
   :var-type "var",
   :doc "store data in .tg/{process-id}",
   :name "store"}
  {:arglists ([& dirs]),
   :name "test-dirs",
   :namespace "clojure.test.generative.runner",
   :source-url
   "https://github.com/clojure/test.generative/blob/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj#L297",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/test-dirs",
   :doc
   "Runs tests in dirs, returning a map of test lib keyword\nto summary data",
   :var-type "function",
   :line 297,
   :file "src/main/clojure/clojure/test/generative/runner.clj"}
  {:file "src/main/clojure/clojure/test/generative/runner.clj",
   :raw-source-url
   "https://github.com/clojure/test.generative/raw/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj",
   :source-url
   "https://github.com/clojure/test.generative/blob/4eefa80376d8e3ad0a4da067b7972bf07c233016/src/main/clojure/clojure/test/generative/runner.clj#L186",
   :wiki-url
   "http://clojure.github.com/test.generative//clojure.test.generative-api.html#clojure.test.generative.runner/TestContainer",
   :namespace "clojure.test.generative.runner",
   :line 186,
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
   "Returns a collection of generative tests, where a test is a map with\n   :name     ns-qualified symbol\n   :fn       fn to test\n   :inputs   fn returning a (possibly infinite!) sequence of inputs\n\nAll input generation should use gen/*seed* and gen/*rnd*\nif a source of pseudo-randomness is needed.",
   :name "tests"})}
