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
  (request/api-get (if workspace-id (format "workspaces/%s/tasks" workspace-id) "tasks")
                   (if assignee {:assignee assignee})))
