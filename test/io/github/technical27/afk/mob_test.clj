(ns io.github.technical27.afk.mob-test
  (:require [clojure.test :refer :all]
            [io.github.technical27.afk.mob :refer :all]))

(deftest is-mob?-test
  (testing "check is-mob?"
    (is (not (is-mob? String)))))

(deftest is-player?-test
  (testing "check is-player?"
    (is (not (is-player? String)))))

(deftest reset-if-targeting-player-test
  (testing "check return of reset-if-targeting-player"
    (is (reset-if-targeting-player nil))))

(deftest reset-target-test
  (testing "check reset-target"
    (is (thrown? IllegalArgumentException (reset-target "not a mob")))))
