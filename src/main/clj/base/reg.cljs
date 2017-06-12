(ns base.reg
  (:require [reagent.core :as reagent]))

(defn hello []
  [:div "hello world"])

(reagent/render hello (.getElementById js/document "container"))
