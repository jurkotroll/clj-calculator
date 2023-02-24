(ns server.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.json :as ring-json]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [not-found]]
            [server.handler :as handler]))


(defroutes app
           (POST "/calc" [] handler/calculator-handler)
           (not-found "address not found"))


(defn wrap-tap-request-and-response
  [handler {:keys [ident]}]
  (fn [request]
    (tap> {:ident ident
           :request request})
    (let [response (handler request)]
      (tap> {:ident ident
             :response response})
      response)))

(def wrapped-app
  (-> app
      (wrap-tap-request-and-response {:ident :json-params})
      (ring-json/wrap-json-params {:keywords? true})
      (wrap-tap-request-and-response {:ident :json-response})
      (ring-json/wrap-json-response)
      (wrap-tap-request-and-response {:ident :from-client-to-client})))


(defonce server-instance (atom nil))


(defn start-server
  [{:keys [http-port join?]}]
  (println " Starting server on port: " http-port)
  (reset! server-instance
          (jetty/run-jetty #'wrapped-app {:port http-port :join? join?})))


(defn stop-server
  []
  (when-not (nil? @server-instance)
    (.stop @server-instance)
    (reset! server-instance nil)
    (println "Server shutting down...")))


(defn restart-server
  [{:keys [http-port join?]}]
  (stop-server)
  (start-server {:port http-port :join? join?}))


;(defn -main
;  "Select a value for the http port the app-server will listen to
;  and call start-server
;
;  The http port is either an argument passed to the function,
;  an operating system environment variable or a default value."
;
;  [& [http-port]]
;  (let [http-port (Integer. (or http-port (System/getenv "PORT") "8888"))]
;    (start-server http-port)))


(comment

  (start-server {:http-port 8000 :join? false})

  (stop-server)

  (restart-server {:http-port 8000 :join? false})


  )
