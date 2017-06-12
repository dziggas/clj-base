(ns remote.app
  (:require [compojure.core :refer :all]
            [compojure.route :as route]))

(defroutes handler
           (route/resources "/"))
