;   Copyright (c) Rich Hickey, Stuart Halloway, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.test.array-test
  (:use clojure.test.generative)
  (:require [clojure.test.generative.generators :as gen]))

(defn nan-or-=
  [a b]
  (or (= a b)
      (and (Double/isNaN a)
           (Double/isNaN b))))

(defmacro array-access-specs
  [pdescs]
  `(do
    ~@(map
       (fn [[prim comp]]
         `(defspec ~(symbol (str prim "-array-access"))
            vec
            [~(with-meta 'arr {:tag (list (symbol (str prim "-array")) prim)})]
            (assert (= (alength ~'arr) (count ~'%)))
            (dotimes [~'i (count ~'arr)]
              (assert (~comp (aget ~'arr ~'i) (get ~'% ~'i))))))
       pdescs)))

(defmacro copy-array-fns
  [& types]
  `(do
     ~@(map
        (fn [t]
          `(defn ~(symbol (str "copy-" t "-array"))
             [~'a]
            (let [~'copy ~(list (symbol (str t "-array")) 'a)]
              (dotimes [~'i (alength ~'a)]
                (aset ~'copy ~'i ~(with-meta `(aget ~'a ~'i)
                                    {:tag (symbol (str t "s"))})))
              ~'copy)))
        types)))

(copy-array-fns boolean byte char short int long float double object)

(defmacro aset-specs
  [pdescs]
  `(do
    ~@(map
       (fn [[prim comp]]
         `(defspec ~(symbol (str prim "-aset-spec"))
            copy-object-array
            [~(with-meta 'arr {:tag (list (symbol (str prim "-array")) prim)})]
            (assert (= (alength ~'arr) (alength ~'%)))
            (dotimes [~'i (count ~'arr)]
              (assert (~comp (aget ~'arr ~'i) (aget ~'% ~'i))))))
       pdescs)))

(array-access-specs {int =
                     long =
                     float nan-or-=
                     double nan-or-=
                     short =
                     byte =
                     char =
                     boolean =})

(aset-specs {int =
             long =
             float nan-or-=
             double nan-or-=
             short =
             byte =
             char =
             boolean =})


