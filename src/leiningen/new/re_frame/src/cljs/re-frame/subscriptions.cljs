(ns {{project-ns}}.subscriptions
  (:require [re-frame.core :as rf]
            [reagent.ratom :refer-macros [reaction]]))

(defn init-subscriptions! []
  (rf/register-sub
   :page
   (fn [db _]
     (reaction (get @db :page)))))
