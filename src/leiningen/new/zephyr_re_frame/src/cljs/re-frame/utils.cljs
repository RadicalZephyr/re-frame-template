(ns {{project-ns}}.utils
  (:require [clojure.string :as s]))

(defn titlize [title & {:keys [to-spaces]
                        :or {to-spaces #" "}}]
  (->>
   (-> title
       (s/replace to-spaces " ")
       (s/split #" "))
   (map s/capitalize)
   (s/join " ")))
