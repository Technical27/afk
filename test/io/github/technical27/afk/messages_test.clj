(ns io.github.technical27.afk.messages-test
  (:require [clojure.test :refer :all]
            [io.github.technical27.afk.messages :refer :all]))

(deftest afk-start-test
  (testing "check afk start message"
    (let [msg (afk-start "fakename")]
      (is (.contains msg "is now AFK"))
      (is (.contains msg "fakename")))))

(deftest afk-end-test
  (testing "check afk end message"
    (let [msg (afk-end "fakename")]
      (is (.contains msg "is no longer AFK"))
      (is (.contains msg "fakename")))))

(deftest afk-tag-test
  (testing "check afk-tag placeholder"
    (is (.contains afk-tag "[AFK]"))))
