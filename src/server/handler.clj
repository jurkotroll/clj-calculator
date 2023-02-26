(ns server.handler
  (:require
    [ring.util.response :as ring-util]
    [calculator.core :as calc-core]
    [calculator.history :as history]))


(defn return-result-and-save-to-history
  [answer expression-string]
  (let [result (:result answer)]
    (history/add-to-history
      history/calculator-history
      {:result     result
       :expression expression-string})
    (ring-util/response {:result result})))



(defn calculator-handler
  [request]
  (let [expression-string (get-in request [:json-params :expression])
        answer (calc-core/calculate expression-string)]
    (if (:result answer)
      (return-result-and-save-to-history answer expression-string)
      {:status  400
       :headers {}
       :body    {:error      (:error answer)
                 :expression expression-string}})))