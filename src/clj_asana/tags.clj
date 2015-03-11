(ns clj-asana.tags
  (:require [clj-asana.request :as request]))

(defn create-tag
  "Creates a tag in the given workspace

  Args:
  workspace-id: id# of a workspace
  tag-name: Name for the tag
  color: (Optional) Color for the tag
  notes: (Optional) Notes for the tag
  "
  [api-key workspace-id tag-name & {:keys [color notes] :or {:color nil :notes nil}}]
  (request/api-post api-key
                    (format "workspaces/%s/tags" workspace-id)
                    (conj {:name tag-name}
                          (if color {:color color})
                          (if notes {:notes notes}))))

(defn show-tag
  "Shows info about a tag

  Args:
  tag-id: id# of tag
  "
  [api-key tag-id]
  (request/api-get api-key (format "tags/%s" tag-id)))

(defn update-tag
  "Creates a tag in the given workspace

  Args:
  tag-id: id# of tag
  tag-name: (Optional) Name for the tag
  color: (Optional) Color for the tag
  notes: (Optional) Notes for the tag
  "
  [api-key tag-id & {:keys [tag-name color notes] :or {:tag-name nil :color nil :notes nil}}]
  (request/api-put api-key
                    (format "tags/%s" tag-id)
                    (conj {}
                          (if tag-name {:name tag-name})
                          (if color {:color color})
                          (if notes {:notes notes}))))

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
