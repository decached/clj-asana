(ns clj-asana.typeahead
  (:require [clj-asana.request :as request]))

(defn search
  "Search results via typeahead query

  Args:
  workspace-id: #id of an organization
  object-type: The type of object to look up
  query: The value to look up
  quantity: (Optional) The number of results to return
  "
  [api-key workspace-id object-type query & {:keys [quantity]}]
  (request/api-get api-key
                   (format "workspaces/%s/typeahead" workspace-id)
                   :params {:type object-type
                            :query query
                            :count quantity}))
