;   Copyright (c) Rich Hickey, Stuart Halloway, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test.generative.clojure-test-adapter
  (:require [clojure.test.generative.event :as event]
            [clojure.test.generative.config :as config]
            [clojure.test.generative.io :as testio]
            [clojure.tools.namespace :as ns]
            [clojure.test :as ctest]))

(defmulti ctevent->event
  "Convert a clojure.test reporting event to an event."
  :type)

(defmethod ctevent->event :default
  [e]
  (event/create :clojure.test/unknown e))

(defmethod ctevent->event :pass
  [e]
  (event/create :type :assert/pass))

(defmethod ctevent->event :fail
  [e]
  (event/create :type :assert/fail
                ::ctest/testing-contexts (seq ctest/*testing-contexts*)))

(defmethod ctevent->event :error
  [e]
  (event/create :level :error
                :type :error
                ::ctest/testing-contexts (seq ctest/*testing-contexts*)
                :message (:message e)
                :test/expected (:expected e)
                :test/actual (:actual e)
                :file (:file e)
                :line (:line e)
                ::ctest/testing-vars (reverse (map #(:name (meta %)) ctest/*testing-vars*))))

(defmethod ctevent->event :summary
  [e]
  nil)

(defmethod ctevent->event :begin-test-ns
  [e]
  (event/create :type :test/group
                :tags #{:begin}
                :name (ns-name (:ns e))))

(defmethod ctevent->event :end-test-ns
  [e]
  (event/create :type :test/group
                :tags #{:end}
                :name (ns-name (:ns e))))

(defmethod ctevent->event :begin-test-var
  [e]
  (event/create :type :test/test
                :tags #{:begin}
                :name (event/fqname (:var e))))

(defmethod ctevent->event :end-test-var
  [e]
  (event/create :type :test/test
                :tags #{:end}
                :name (event/fqname (:var e))))

(defn report-adapter
  "Adapt clojure.test event model to fire c.t.g events."
  [m]
  (when-let [e (ctevent->event m)]
    (event/report-fn e)))



