(ns user
  (:require [portal.api :as p]))


(add-tap #'p/submit)


(def p (p/open {:launcher :intellij}))
