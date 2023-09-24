(ns user
  (:require [portal.api :as p]
            [server.core :as server]))

(def p (p/open {:launcher :intellij}))


(add-tap #'p/submit)

(tap> "Hello Portal")

#_(server/start-server {:http-port 8000 :join? false})

#_(tap> "Hello server")


(comment

  )