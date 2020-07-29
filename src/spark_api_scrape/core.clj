(ns spark-api-scrape.core)

(require '[clj-http.client :as client]
         '[cheshire.core :refer :all])

(def root-url "")
(defn get-url [url] (parse-string (:body (client/get url)) true))
(defn get-application-ids []
  (map :id (get-url (str root-url "/applications?status=completed&limit=1"))))

(defn get-stage-ids [app-id]
  (map :stageId (get-url (str root-url "/applications/" app-id "/1/stages"))))

(defn get-stage-quantiles [app-id stage-id]
  (get-url (str root-url "/applications/" app-id "/stages/" stage-id "/1/taskSummary")))

(defn test []
  (let [app-id (first (get-application-ids))
        stage-id (first (get-stage-ids app-id))]
    (get-stage-quantiles app-id stage-id)))
