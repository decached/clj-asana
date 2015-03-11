(ns clj-asana.projects
  (:require [clj-asana.request :as request]))

(defn create-project
  "Creates a new project

  Args:
  workspace-id: #id of a workspace for the project
  project-name: Name for the project
  team-id: (Optional) #id of a team. If workspace is an organization then this is mandatory
  color: (Optional) Color for the project
  notes: (Optional) Notes for the project
  "
  [api-key workspace-id project-name & {:keys [team-id color notes] :or {:team-id nil :color nil :notes nil}}]
  (request/api-post api-key
                    "projects"
                    (conj {:name project-name :workspace workspace-id}
                          (if team-id {:team team-id})
                          (if color {:color color})
                          (if notes {:notes notes}))))

(defn show-project
  "Shows info about a project

  Args:
  project-id: id# of project
  "
  [api-key project-id]
  (request/api-get api-key (format "projects/%s" project-id)))

(defn update-project
  "Updates a project

  Args:
  project-id: #id of a project
  project-name: (Optional) Name for the project
  color: (Optional) Color for the project
  notes: (Optional) Notes for the project
  "
  [api-key project-id & {:keys [project-name color notes] :or {:project-name nil :color nil :notes nil}}]
  (request/api-put api-key
                    (format "projects/%s" project-id)
                    (conj {}
                          (if project-name {:name project-name})
                          (if color {:color color})
                          (if notes {:notes notes}))))

(defn rm-project
  "Deletes a project

  Args:
  project-id: id# of project"
  [project-id]
  (request/api-delete (format "projects/%s" project-id)))

(defn list-project-tasks
  "Gets tasks in a project

  Args:
  project-id: id# of a project
  "
  [api-key project-id]
  (request/api-get api-key (format "projects/%s/tasks" project-id)))

(defn list-projects
  "List all projects

  team-id: (Optional) #id of a team
  workspace-id: (Optional) #id of a workspace
  "
  [api-key & {:keys [team-id workspace-id] :or {:team-id nil :workspace-id nil}}]
  (request/api-get api-key (if (and (= workspace-id nil) (= team-id nil))
                              "projects"
                              (if workspace-id
                                (format "workspaces/%s/projects" workspace-id)
                                (format "teams/%s/projects" team-id)))))
