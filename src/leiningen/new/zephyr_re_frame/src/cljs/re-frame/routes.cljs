(ns {{project-ns}}.routes
  (:require [{{project-ns}}.history :as h]
            [re-frame.core :as rf]
            [secretary.core :as secretary
             :refer-macros [defroute]]))

(defroute hello "/" {:as _}
  (rf/dispatch [:hello]))

(defroute "*" {:as _}
  (rf/dispatch [:unknown]))

(defn go-to [page]
  (h/set-token page))

(defn setup-secretary! []
  (secretary/set-config! :prefix "/#"))
