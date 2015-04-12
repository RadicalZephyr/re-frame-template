(ns {{project-ns}}.server
  (:gen-class)
  (:require [{{project-ns}}.utils :refer [edn-response
                                          safe-read-string]]
            [ring.util.response :as response]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.edn :refer [wrap-edn-params]]
            [compojure.core  :as c]
            [compojure.route :as route]
            [hiccup.page :as hp]
            [environ.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]))

(c/defroutes routes
  (c/GET "/" [] (hp/html5
                 [:head
                  (hp/include-css "css/normalize.css"
                                  "css/foundation.min.css"
                                  "css/app.css")
                  (hp/include-js "js/vendor/modernizr.js")]
                 [:body
                  [:div.row
                   [:div#app.small-12.columns]]
                  [:input#history_state {:type "hidden"}]
                  (hp/include-js "js/vendor/jquery.js"
                                 "js/vendor/fastclick.js"
                                 "js/foundation.min.js"
                                 "js/compiled/main.js")]))
  (c/GET "/blank" [] "")
  (c/context "/api/v1" []
    )

  (route/resources "/"))

(def app (-> routes
             wrap-params
             wrap-edn-params))

(defn run-web-server [& [port]]
  (let [port (Integer. (or port (env :port) 10555))]
    (print "Starting web server on port" port ".\n")
    (run-jetty app {:port port :join? false})))

(defn -main [& [port]]
  (run-web-server port))
