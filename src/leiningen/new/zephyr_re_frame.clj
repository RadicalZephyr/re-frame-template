(ns leiningen.new.zephyr-re-frame
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files
                                             sanitize sanitize-ns project-name]]
            [leiningen.core.main :as main]
            [clojure.string :as s]
            [clojure.java.io :as io]))

(def render (renderer "zephyr-re-frame"))

(defn template-data [name]
  {:full-name            name
   :name                 (project-name name)
   :project-goog-module  (sanitize (sanitize-ns name))
   :project-ns           (sanitize-ns name)
   :sanitized            (name-to-path name)})

(def files-to-render
  ["project.clj"
   "Procfile"
   "LICENSE"
   "README.md"
   ".gitignore"
   ".hgignore"

   "doc/intro.md"

   "devsrc/re-frame/dev.cljs"

   "src/clj/re-frame/server.clj"
   "src/clj/re-frame/utils.clj"

   "src/cljs/re-frame/main.cljs"
   "src/cljs/re-frame/events.cljs"
   "src/cljs/re-frame/handlers.cljs"
   "src/cljs/re-frame/history.cljs"
   "src/cljs/re-frame/main.cljs"
   "src/cljs/re-frame/routes.cljs"
   "src/cljs/re-frame/scrolling.cljs"
   "src/cljs/re-frame/subscriptions.cljs"
   "src/cljs/re-frame/utils.cljs"

   ;; Foundation files
   "resources/public/css/app.css"
   "resources/public/css/foundation.css"
   "resources/public/css/foundation.min.css"
   "resources/public/css/normalize.css"
   "resources/public/img/.gitkeep"
   "resources/public/js/foundation/foundation.abide.js"
   "resources/public/js/foundation/foundation.accordion.js"
   "resources/public/js/foundation/foundation.alert.js"
   "resources/public/js/foundation/foundation.clearing.js"
   "resources/public/js/foundation/foundation.dropdown.js"
   "resources/public/js/foundation/foundation.equalizer.js"
   "resources/public/js/foundation/foundation.interchange.js"
   "resources/public/js/foundation/foundation.joyride.js"
   "resources/public/js/foundation/foundation.js"
   "resources/public/js/foundation/foundation.magellan.js"
   "resources/public/js/foundation/foundation.offcanvas.js"
   "resources/public/js/foundation/foundation.orbit.js"
   "resources/public/js/foundation/foundation.reveal.js"
   "resources/public/js/foundation/foundation.slider.js"
   "resources/public/js/foundation/foundation.tab.js"
   "resources/public/js/foundation/foundation.tooltip.js"
   "resources/public/js/foundation/foundation.topbar.js"
   "resources/public/js/foundation.min.js"
   "resources/public/js/vendor/fastclick.js"
   "resources/public/js/vendor/jquery.cookie.js"
   "resources/public/js/vendor/jquery.js"
   "resources/public/js/vendor/modernizr.js"
   "resources/public/js/vendor/placeholder.js"])

(defn format-files-args [name]
  (let [data (template-data name)
        render-file (fn [file]
                      [(s/replace file "re-frame" "{{sanitized}}")
                       (render file data)])]
    (cons data (map render-file
                    files-to-render))))

(defn zephyr-re-frame
  "Generate a new web app using re-frame."
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' re-frame project.")
    (apply ->files (format-files-args name))))
