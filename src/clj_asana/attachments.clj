(ns clj-asana.attachments
  (:require [clj-asana.request :as request]))

(defn show-attachment
  "Shows a single attachment

  Args:
  attachment-id: id# of attachment
  "
  [api-key attachment-id]
  (request/api-get api-key (format "attachments/%s" attachment-id)))

(defn list-attachements
  "Shows all attachments on a task

  Args:
  task-id: id# of task
  "
  [api-key task-id]
  (request/api-get api-key (format "tasks/%s/attachments" task-id)))
