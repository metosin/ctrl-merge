(ns bb-test-runner
  (:require
   [clojure.test :as t]
   [ctrl-merge.core-test]))

(defn run-tests [& _args]
  (let [{:keys [fail error]}
        (t/run-tests
         'ctrl-merge.core-test)]
    (when (or (pos? fail) (pos? error))
      (System/exit 1))))
