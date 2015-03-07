(ns clj-asana.workspaces
  (:require [clj-asana.request :as request]))

(defn list-workspaces
  "Lists workspaces"
  [api-key]
  (request/api-get api-key "workspaces"))

(defn update-workspace
  "Updates workspace
  Args:
  workspace-id: id# of workspace
  new-name: Update name
  "
  [api-key workspace-id new-name]
  (request/api-put api-key (format "workspaces/%s" workspace-id) {"name" new-name}))
