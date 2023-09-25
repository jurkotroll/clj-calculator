(ns server.reitit-router
  (:require
    [reitit.core :as r]
    [reitit.ring :as ring]
    [server.handler :as handler]))


(def router
  (r/router
    [["/calc" ::calculations]
     ["/history" ::history]
     ["/order/:id" ::order-by-id]]))





(comment

  (r/match-by-path router "/some")

  (r/match-by-path router "/calc")

  (r/match-by-name router ::calculations)

  (tap>
    (r/partial-match? (r/match-by-name router ::order-by-id)))

  (tap> (r/match-by-name router ::order-by-id))

  )