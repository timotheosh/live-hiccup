(ns live-hiccup.core
  (:require [stasis.core :as stasis]
            [juxt.dirwatch :refer [watch-dir]]
            [etaoin.api :as eta]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [html5]]
            [live-hiccup.pages :refer [projectpage]]))

(def watcher (atom nil))

(def app (stasis/serve-pages projectpage))

(defn export []
  (let [target-dir "resources/public"]
    (stasis/empty-directory! target-dir)
    (stasis/export-pages projectpage target-dir)))

(defn start-watcher []
  (let [browser (eta/chrome {:port 9797})]
    (eta/go browser "http://localhost:3000")
    (reset! watcher
            (watch-dir
             (fn [event] (eta/refresh browser))
             (clojure.java.io/file "src/live_hiccup/")))))
