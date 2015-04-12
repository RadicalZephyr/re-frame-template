(ns {{project-ns}}.handlers
  (:require [cljs.reader :refer [read-string]]
            [{{project-ns}}.history :as h]
            [{{project-ns}}.routes :as r]
            [re-frame.core :as rf]
            [secretary.core :as secretary]))

(defn init-handlers! []
  (rf/register-handler
   :hello
   (fn [db [page]]
     (assoc db :page page)))

  (rf/register-handler
   :unknown
   (fn [db [page]]
     (assoc db :page page)))

  )
