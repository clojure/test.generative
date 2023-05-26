clojure.test.generative
========================================

Generative test runner.

Releases and Dependency Information
========================================

Latest stable release: 1.0.0

* [All Released Versions](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22org.clojure%22%20AND%20a%3A%22test.generative%22)

* [Development Snapshot Versions](https://oss.sonatype.org/index.html#nexus-search;gav~org.clojure~test.generative~~~)

[CLI/`deps.edn`](https://clojure.org/reference/deps_and_cli) dependency information:
```clojure
org.clojure/test.generative {:mvn/version "1.0.0"}
```

[Leiningen](https://github.com/technomancy/leiningen) dependency information:

    [org.clojure/test.generative "1.0.0"]

[Maven](http://maven.apache.org/) dependency information:

    <dependency>
      <groupId>org.clojure</groupId>
      <artifactId>test.generative</artifactId>
      <version>1.0.0</version>
    </dependency>


Example Usages
========================================

A defspec consists of a name, a function to be tested, an input spec,
and a validator:

    (defspec integers-closed-over-addition
      (fn [a b] (+' a b))                    ;; input fn
      [^long a ^long b]                     ;; input spec
      (assert (integer? %)))                ;; 0 or more validator forms

To generate test data, see the fns in the generators namespace. Note
that these functions shadow a bunch of clojure.core names.

You can also create the underlying test data structures directly,
marking vars with :clojure.test.generative/specs so they are picked up
by the runner.  This can be useful e.g. to model relationships between
input parameters, or to specify a finite list of special cases. The
example below specifies five specific one-argument arg lists:

    (def ^::tgen/specs
      inc'-doesnt-overflow-specs
      [{:test 'clojure.test.math-test/inc'-doesnt-overflow
        :input-gen #(map vector [Long/MIN_VALUE -1 0 1 Long/MAX_VALUE])}])


Running Interactively During Development 
========================================

Specify the number of threads, the number of msec, and one or more
vars to test:

    (runner/run 2 1000 #'my/test-var)


Running in a CI environment
========================================

Wire your runner to call runner/-main with a list of directories where
tests can be found.  You can see bin/test.clj for an example.

You can use the following system properties to control the intensity
of your test

<table>
  <tr>
    <th>Java Property</th><th>Interpretation</th>
  </tr>
  <tr>
    <td>clojure.test.generative.threads</td><td>Number of concurrent threads</td>
  </tr>
  <tr>
    <td>clojure.test.generative.msec</td><td>Desired test run duration</td>
  </tr>
</table>

Developer Information
========================================

* [GitHub project](https://github.com/clojure/test.generative)
* [Bug Tracker](https://clojure.atlassian.net/browse/TGEN)
* [Continuous Integration](https://github.com/clojure/test.generative/actions/workflows/test.yml)

Related Projects
========================================

* [ClojureCheck](https://bitbucket.org/kotarak/clojurecheck) adds
  property based testing to clojure.test following the lines of
  [QuickCheck](http://en.wikipedia.org/wiki/QuickCheck) for Haskell.
* [simple-check](https://github.com/reiddraper/simple-check) is a
  Clojure property-based testing tool inspired by QuickCheck.

Copyright and License
========================================

Copyright (c) 2012-2023 Rich Hickey. All rights reserved.  The use and distribution terms for this software are covered by the Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can be found in the file epl-v10.html at the root of this distribution. By using this software in any fashion, you are agreeing to be bound bythe terms of this license.  You must not remove this notice, or any other, from this software.
