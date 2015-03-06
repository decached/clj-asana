(ns clj-asana.request
  (:require [clj-http.client :as client]))

(def ^:private api-base-url "https://app.asana.com/api")

(def ^:private api-version "1.0")

(def ^:private api-url (format "%s/%s" api-base-url api-version))

(defn api-get
  "Peforms a GET request

  Args:
  api-key: Api key of a user
  endpoint: URI path for request
  "
  [api-key endpoint & {:keys [params]}]
  (let [response (client/get (format "%s/%s" api-url endpoint)
                             {:basic-auth [api-key ""]
                              :query-params params
                              :as :json
                              :content-type :json
                              :throw-entire-message? true})]
    (if (= 200 (:status response))
      (:data (:body response)))))
