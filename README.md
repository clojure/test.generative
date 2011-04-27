# test.generative

Test data generation and execution harness. Very early days.
This API will change. You have been warned.

## Usage

See the namespace comment for clojure.test.generative.

## Installation

test.generative is not yet available in Maven Central. To build

    mvn package

## Running Examples

    mvn dependency:build-classpath -Dmdep.outputFile=script/maven-classpath
    script/examples

Note the examples will fail until http://dev.clojure.org/jira/browse/CLJ-426 is fixed!

## License

Copyright Rich Hickey, Stuart Halloway, and contributors.

Licensed under the EPL. (See the file epl.html.)
