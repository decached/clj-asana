(ns clj-asana.tags
  (:require [clj-asana.request :as request]))

(defn show-tag
  "Shows info about a tag

  Args:
  tag-id: id# of tag
  "
  [api-key tag-id]
  (request/api-get api-key (format "tags/%s" tag-id)))

(defn list-tagged-tasks
  "Gets tasks for a tag

  Args:
  tag-id: id# of task
  "
  [api-key tag-id]
  (request/api-get api-key (format "tags/%s/tasks" tag-id)))

(defn list-tags
  "Shows available tags for workspace

  Args:
  workspace-id: id# of workspace
  "
  ([api-key] (request/api-get api-key "tags"))
  ([api-key workspace-id] (request/api-get api-key (format "workspaces/%s/tags" workspace-id))))
