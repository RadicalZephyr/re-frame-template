(ns {{project-ns}}.routes
  (:require [{{project-ns}}.history :as h]
            [re-frame.core :as rf]
            [secretary.core :as secretary
             :refer-macros [defroute]]))

(defroute hello "/" {:as _}
  (rf/dispatch [:hello]))

(defroute "*" {:as _}
  (rf/dispatch [:unknown]))

;; When setting the history token, we don't want to have a "/#"
(defn go-to [page-url]
  (h/set-token page-url))

;; But if we set the URL directly, we probably want to prefix it with
;; the "/#" to avoid any weirdness with relative paths
(defn make-link [page-url]
  (str "/#" page-url))

(defn setup-secretary! []
  (secretary/set-config! :prefix ""))
