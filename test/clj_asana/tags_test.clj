(ns clj-asana.tags-test
  (:require [clojure.test :refer :all]
            [clj-asana.tags :as tags]
            [environ.core :as environ]))

(def ^:private api-key (environ/env :asana-api-key))

(deftest test-list-tags
  (is (= (type (tags/list-tags api-key))
         clojure.lang.PersistentVector)))

(deftest test-show-tag
  (is (= (type (let [tags-list (tags/list-tags api-key)]
                 (tags/show-tag api-key (:id (nth tags-list 0)))))
         clojure.lang.PersistentArrayMap)))

(deftest test-list-tagged-tasks
  (is (= (type (let [tags-list (tags/list-tags api-key)]
                 (tags/list-tagged-tasks api-key (:id (nth tags-list 0)))))
         clojure.lang.PersistentVector)))
