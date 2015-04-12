(ns leiningen.new.zephyr-re-frame
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files
                                             sanitize sanitize-ns project-name]]
            [leiningen.core.main :as main]
            [clojure.string :as s]
            [clojure.java.io :as io]))

(def render (renderer "re-frame"))

(defn template-data [name]
  {:full-name            name
   :name                 (project-name name)
   :project-goog-module  (sanitize (sanitize-ns name))
   :project-ns           (sanitize-ns name)
   :sanitized            (name-to-path name)})

(def files-to-render
  ["project.clj"

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

   "LICENSE"
   "README.md"
   ".gitignore"
   ".hgignore"
   "Procfile"])

(defn foundation-files []
  (->> (io/file "src/leiningen/new/re_frame/resources")
       file-seq
       (remove #(.isDirectory %))
       (map #(s/replace-first (.getPath %)
                              "src/leiningen/new/re_frame/"
                              ""))))

(defn format-files-args [name]
  (let [data (template-data name)
        render-file (fn [file]
                      [(s/replace file "re-frame" "{{sanitized}}")
                       (render file data)])]
    (cons data (map render-file
                    (concat files-to-render
                            (foundation-files))))))

(defn re-frame
  "Generate a new web app using re-frame."
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' re-frame project.")
    (apply ->files (format-files-args name))))
