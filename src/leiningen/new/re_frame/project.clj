(defproject {{full-name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License  v1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.5.0"
  :uberjar-name "{{{name}}}.jar"
  :source-paths ["src/clj"]
  :test-paths ["test/clj"]

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring "1.3.2"]
                 [fogus/ring-edn "0.2.0"]
                 [compojure "1.3.2"]
                 [hiccup "1.0.5"]
                 [enlive "1.1.5"]
                 [environ "1.0.0"]

                 [org.clojure/clojurescript "0.0-3058"]
                 [reagent "0.5.0-alpha3"]
                 [re-frame "0.2.0"
                  :exclusions [[org.clojure/clojurescript
                                :extension "jar"]]]
                 [secretary "1.2.3"]]

  :plugins      [[lein-ring "0.8.13"]
                 [lein-cljsbuild "1.0.5"]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled"]

  :ring  {:handler {{{project-ns}}}.server/app
          :port 8090}

  :cljsbuild {:builds
              {:client
               {:source-paths ["src/cljs"]
                :compiler
                {:output-to  "resources/public/js/compiled/main.js"
                 :output-dir "resources/public/js/compiled/out"
                 :asset-path "js/compiled/out"}}}}

  :profiles {:dev {:dependencies [[figwheel "0.2.5"]]
                   :plugins [[lein-figwheel "0.2.5"
                              :exclusions [[cider/cider-nrepl
                                            :extensions "jar"]]]]
                   :figwheel {:http-server-root "public"
                              :css-dirs ["resources/public/css"]
                              :nrepl-port 7888}
                   :cljsbuild
                   {:builds {:client {:source-paths ["devsrc"]
                                      :compiler
                                      {:main {{{project-ns}}}.dev
                                       :optimizations :none
                                       :source-map true
                                       :source-map-timestamp true}}}}}

             :uberjar {:hooks [leiningen.cljsbuild]
                       :omit-source true
                       :aot :all
                       :cljsbuild
                       {:builds {:client {:compiler
                                          {:main {{{project-ns}}}.main
                                           :optimizations :advanced
                                           :elide-asserts true
                                           :pretty-print false}}}}}})
