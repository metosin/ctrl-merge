(ns ctrl-merge.test-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [ctrl-merge.core-test]))

(doo-tests 'ctrl-merge.core-test)
