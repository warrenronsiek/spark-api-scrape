(ns spark-api-scrape.core)

(require '[clj-http.client :as client]
         '[cheshire.core :refer :all])

(def root-url "")
(defn get-url [url] (parse-string (:body (client/get url)) true))
(defn get-application-ids []
  (map :id (get-url (str root-url "/applications?status=completed&limit=1"))))

(defn get-stages [app-id] (get-url (str root-url "/applications/" app-id "/1/stages")))


(defn -main [] (println (first (get-application-ids))))
