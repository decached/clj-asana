(ns clj-asana.tasks
  (:require [clj-asana.request :as request]))

(defn create-task
  "Creates a new task

  Args:
  task-name: Name for the task
  workspace-id: #id of a workspace
  assignee: (Optional) User to which this task is to be assigned
  due-on: Optional due date for task
  followers: (Optional) Followers for task
  notes: (Optional) notes to add to task
  "
  [api-key workspace-id task-name & {:keys [assignee assignee-status due-on followers notes]
                                     :or {:assignee nil :assignee-status "upcoming" :due-on nil :followers nil :notes nil}}]
  (request/api-post api-key "tasks"
                    (conj {:name task-name
                           :workspace workspace-id
                           :assignee_status assignee-status}
                          (if assignee {:assignee assignee})
                          (if due-on {:due_on due-on})
                          (if notes {:notes notes})
                          (if followers (into {} (map-indexed (fn [index value] [(format "followers[%d]" index) value]) followers))))))

(defn show-task
  "Shows all information about a task.

  Args:
  task-id: id# of task"
  [api-key task-id]
  (request/api-get api-key (format "tasks/%s" task-id)))

(defn update-task
  "Updates an existing task
  Args:
  task-id: #id of task to update
  name: Update task name
  assignee: Update assignee
  assignee-status: Update status
  completed: Update whether the task is completed
  due-on: Update due date
  notes: Update notes
  "
  [api-key task-id & {:keys [new-name assignee assignee-status completed due-on notes]
                      :or {new-name nil assignee nil assignee-status "upcoming" completed false due-on nil notes nil}}]
  (request/api-put api-key (format "tasks/%s" task-id)
                   (conj {} (if new-name {"name" new-name})
                         (if assignee {"assignee" assignee})
                         (if assignee-status {"assignee_status" assignee-status})
                         (if due-on {"due_on" due-on})
                         (if notes {"notes" notes})
                         (if completed {"completed" completed})
                         (if due-on {"due_on" due-on}))))

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

(defn list-subtasks
  "Lists subtasks.

  Args:
  task-id: #id of a task
  "
  [api-key task-id]
  (request/api-get api-key (format "tasks/%s/subtasks" task-id)))

(defn set-parent
  "Sets parent to an existing task.

  Args:
  task-id: id# of task
  parent-id: id# of project
  "
  [api-key task-id parent-id]
  (request/api-post api-key (format "tasks/%s/setProject" task-id) {:parent parent-id}))

(defn list-task-projects
  "Lists all projects associated with a task.

  Args:
  task-id: id# of task
  "
  [api-key task-id]
  (request/api-get api-key (format "tasks/%s/projects" task-id)))

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
