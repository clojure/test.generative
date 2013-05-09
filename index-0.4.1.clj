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
   :doc nil}),
 :vars
 ({:arglists ([f]),
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
   :file "src/main/clojure/clojure/test/generative/io.clj"})}
