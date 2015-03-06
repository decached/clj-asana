(ns clj-asana.teams
  (:require [clj-asana.request :as request]))

(defn list-teams
  "Lists all teams you’re a member of in an organization.

  Args:
  organization-id: #id of an organization
  "
  [api-key organization-id]
  (request/api-get api-key (format "organizations/%s/teams" organization-id)))
