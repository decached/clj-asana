(ns clj-asana.stories
  (:require [clj-asana.request :as request]))

(defn list-stories
  "Lists stories of a task.

  Args:
  task-id: id# of task
  "
  [api-key task-id]
  (request/api-get api-key (format "tasks/%s/stories" task-id)))

(defn show-story
  "Shows full story.

  Args:
  story-id: id# of a story
  "
  [api-key story-id]
  (request/api-get api-key (format "stories/%s" story-id)))

(defn add-comment
  "Adds a comment to an object.

  Args:
  task-id: #id of a task to add a comment on
  text: Comment to be posted
  "
  [api-key task-id text]
  (request/api-post api-key (format "tasks/%s/stories" task-id) {"text" text}))
