(use '[clojure.test.generative])
(try
 (println "Testing on" *cores* "cores for" *msec* "msec.")
 (let [futures (test-dirs "src/examples/clojure")]
   (doseq [f futures]
     @f))
 (catch Throwable t
   (.printStackTrace t)
   (System/exit -1))
 (finally
  (shutdown-agents)))
(System/exit 0)


