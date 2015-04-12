(ns {{project-ns}}.utils
  (:require [clojure.tools.reader.edn :as edn]))

(defn unknown-val [tag val]
  {:unknown-tag tag
   :value val})

(defn safe-read-string [s]
  (edn/read-string {:default unknown-val} s))

(defn edn-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/edn"}
   :body (pr-str data)})
