;   Copyright (c) Rich Hickey, Stuart Halloway, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test.generative.config
  (:require [clojure.string :as str]))

(def config-mapping
  [["clojure.test.generative.threads" [:threads] read-string (.availableProcessors (Runtime/getRuntime))]
   ["clojure.test.generative.msec" [:msec] read-string 10000]
   ["clojure.test.generative.handlers" [:handlers] #(str/split % #",") ["clojure.test.generative.io/console-reporter"]]])

(defn config
  []
  (reduce
   (fn [m [prop path coerce default]]
     (let [val (System/getProperty prop)]
       (if (seq val)
         (assoc-in m path (coerce val))
         (assoc-in m path default))))
   {}
   config-mapping))

