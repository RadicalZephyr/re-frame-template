(ns {{project-ns}}.main
  (:require [{{project-ns}}.routes :as r
             :refer [setup-secretary!]]
            [{{project-ns}}.handlers :refer [init-handlers!]]
            [{{project-ns}}.subscriptions
             :refer [init-subscriptions!]]
            [{{project-ns}}.history
             :refer [hook-browser-navigation!]]
            [{{project-ns}}.scrolling
             :refer [setup-scrolling-events!]]
            [{{project-ns}}.utils :refer [titlize]]
            [reagent.core :as reagent :refer [atom]]
            [re-frame.core :as rf]
            [secretary.core :as secretary
             :refer-macros [defroute]]))

(defn four-oh-four []
  [:div
   [:h1 "Sorry!"]
   "There's nothing to see here."])

(defn {{name}} []
  (let [page (rf/subscribe [:page])]
    (fn []
      (case @page
        nil [:h1 "Hello World!"]
        [four-oh-four]))))

(defn ^:export run []
  (init-handlers!)
  (init-subscriptions!)
  (setup-scrolling-events!)
  (setup-secretary!)
  (try
    (hook-browser-navigation!)
    (catch js/Error e
      nil))
  (reagent/render [{{name}}]
                  (.getElementById js/document "app")))

(run)
