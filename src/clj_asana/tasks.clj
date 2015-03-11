(ns clj-asana.tasks
  (:require [clj-asana.request :as request]))

(defn show-task
  "Shows all information about a task.

  Args:
  task-id: id# of task"
  [api-key task-id]
  (request/api-get api-key (format "tasks/%s" task-id)))

(defn delete-task
  "Deletes an existing task.

  Args:
  task-id: id# of task"
  [api-key task-id]
  (request/api-delete api-key (format "tasks/%s" task-id)))

(defn list-tasks
  "Lists tasks.

  Args:
  workspace: workspace id
  assignee: assignee
  "
  [api-key & {:keys [workspace-id assignee] :or {:workspace-id nil :assignee nil}}]
  (request/api-get api-key (if workspace-id (format "workspaces/%s/tasks" workspace-id) "tasks")
                   (if assignee {:assignee assignee})))

(defn add-project-to-task
  "Adds project to a task

  Args:
  task-id: id# of task
  project-id: id# of project
  "
  [api-key task-id project-id]
  (request/api-post api-key (format "tasks/%s/addProject" task-id) {:project project-id}))

(defn remove-project-from-task
  "Removes a project from task

  Args:
  task-id: id# of task
  project-id: id# of project
  "
  [api-key task-id project-id]
  (request/api-post api-key (format "tasks/%s/removeProject" task-id) {:project project-id}))

(defn list-task-tags
  "Lists tags that are associated with a task.

  Args:
  task-id: id# of task
  "
  [api-key task-id]
  (request/api-get api-key (format "tasks/%s/tags" task-id)))

(defn add-tag-to-task
  "Tags a task

  Args:
  task-id: id# of task
  tag-id: id# of tag to add
  "
  [api-key task-id tag-id]
  (request/api-post api-key (format "tasks/%s/addTag" task-id) {"tag" tag-id}))

(defn remove-tag-from-task
  "Removes a tag from a task.

  Args:
  task-id: id# of task
  tag-id: id# of tag to remove
  "
  [api-key task-id tag-id]
  (request/api-post api-key (format "tasks/%s/removeTag" task-id) {"tag" tag-id}))

(defn add-followers-to-task
  "Adds followers to a task

  Args:
  task-id: id# of task
  followers []: id#'s of followers
  "
  [api-key task-id followers]
  (request/api-post api-key (format "tasks/%s/addFollowers") (into {} (map-indexed (fn [index value] [(format "followers[%d]" index) value]) followers))))

(defn remove-followers-from-task
  "Removes followers from a task

  Args:
  task-id: id# of task
  followers []: id#'s of followers
  "
  [api-key task-id followers]
  (request/api-post api-key (format "tasks/%s/removeFollowers") (into {} (map-indexed (fn [index value] [(format "followers[%d]" index) value]) followers))))
