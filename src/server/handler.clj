(ns server.handler
  (:require
    [ring.util.response :as ring-util]
    [calculator.core :as calc-core]))


(defn calculator-handler
  [request]
  (tap> {:handler-request request})
  (let [expression-string (get-in request [:json-params :expression])
        result (calc-core/calculate expression-string)]
    (if (:result result)
      (ring-util/response {:result (:result result)})
      {:status 400
       :headers {}
       :body {:error (:error result)
              :expression expression-string}})))
