(ns server.handler
  (:require
    [ring.util.response :as ring-util]
    [calculator.core :as calc-core]
    [calculator.history :as history]))


(defn return-result-and-save-to-history
  [result expression-string]
  (history/add-to-history
    history/calculator-history
    {:result     result
     :expression expression-string})
  (ring-util/response {:result result}))



(defn calculator-handler
  [request]
  (let [expression-string (get-in request [:json-params :expression])
        answer (calc-core/calculate expression-string)
        result (:result answer)]
    (if result
      (return-result-and-save-to-history result expression-string)
      {:status  400
       :headers {}
       :body    {:error      (:error answer)
                 :expression expression-string}})))


(defn history-handler
  [request]
  (let [amount (get-in request [:json-params :amount])]
    (ring-util/response
      {:history-entries (history/take-from-history
                          history/calculator-history
                          amount)})))


(comment

  (history-handler {:json-params {:amount 2}})

  )
;TODO: make history and calculation as two microservices
