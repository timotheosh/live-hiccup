(defproject live-hiccup "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [juxt/dirwatch "0.2.5"]
                 [etaoin "1.0.40"]
                 [stasis "2023.06.03"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:port 3000
         :handler live-hiccup.core/app
         ;;:init live-hiccup.core/start-watcher
         :auto-refresh? true
         :auto-reload? true}
  :repl-options {:init-ns live-hiccup.core})
