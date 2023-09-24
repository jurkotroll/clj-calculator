(ns user
  (:require [portal.api :as p]
            [server.core :as server]))

(def p (p/open {:launcher :intellij}))


(add-tap #'p/submit)

(tap> "Hello Portal")

(def p (p/open {:launcher :intellij}))
