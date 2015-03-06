(ns clj-asana.users
  (:require [clj-asana.request :as request]))

(defn show-user
  "Shows info of yourself or other users.

  Args:
  user-id: target user
  "
  [api-key user-id]
  (request/api-get api-key (format "users/%s" user-id)))

(defn show-users
  "Shows users (based on workspace-id) and filters the results

  Args:
  workspace-id: Optional #id of workspace
  filters: Optional [] of filters you want to apply to listing
  "
  [api-key & {:keys [workspace-id filters] :or {workspace-id nil filters nil}}]
  (request/api-get api-key
                   (if workspace-id (format "workspaces/%s/users" workspace-id) "users")
                   :params (if filters
                             {:opt_fields (apply str (interpose ","
                                                                (map
                                                                  (fn [x] (clojure.string/lower-case (clojure.string/trim x)))
                                                                  filters)))}
                             {})))
