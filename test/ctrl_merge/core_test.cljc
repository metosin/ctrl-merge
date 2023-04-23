(ns ctrl-merge.core-test
  (:require #?(:clj  [clojure.test :refer :all]
               :cljs [cljs.test :refer-macros [deftest is testing]])
            [ctrl-merge.core :as cm]))

(deftest test-meta-merge
  (testing "simple merge"
    (is (= (cm/merge {:a 1 :b 2} {:b 3 :c 4})
           {:a 1 :b 3 :c 4})))

  (testing "inner map merge"
    (is (= (cm/merge {:a {:b 1 :c 2}} {:a {:c 3}})
           {:a {:b 1 :c 3}})))

  (testing "inner set merge"
    (is (= (cm/merge {:a #{:b :c}} {:a #{:c :d}})
           {:a #{:b :c :d}})))

  (testing "inner vector merge"
    (is (= (cm/merge {:a [:b :c]} {:a [:d]})
           {:a [:b :c :d]})))

  (testing "meta replace"
    (is (= (cm/merge {:a [:b :c]} {:a ^:replace [:d]})
           {:a [:d]})))

  (testing "meta displace"
    (is (= (cm/merge {:a [:b :c]} {:a ^:displace [:d]})
           {:a [:b :c]})))

  (testing "meta prepend"
    (is (= (cm/merge {:a [:b :c]} {:a ^:prepend [:d]})
           {:a [:d :b :c]})))

  (testing "deep inner merge"
    (is (= (cm/merge {:a {:b {:c [:d]}}} {:a {:b {:c [:e] :f :g}}})
           {:a {:b {:c [:d :e] :f :g}}})))

  (testing "collection type remains the same"
    (is (map? (cm/merge {:a :b} {:c :d})))
    (is (vector? (cm/merge [:a :b] [:c])))
    (is (set? (cm/merge #{:a :b} #{:c})))
    (is (list? (cm/merge '(:a :b) '(:c)))))

  (testing "nil displace"
    (is (= (cm/merge {:b :c} {:a ^:displace [:d]})
           {:a [:d] :b :c})))

  (testing "varargs"
    (is (= (cm/merge)
           {}))
    (is (= (cm/merge {:a :b})
           {:a :b}))
    (is (= (cm/merge {:a :b :x 1} {:a :c :y 2} {:a :d})
           {:a :d :x 1 :y 2}))
    (is (= (cm/merge {:a :b :x 1} {:a :c :y 2} {:a :d} {:y 4 :z 3})
           {:a :d :x 1 :y 4 :z 3}))))
