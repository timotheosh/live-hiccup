(ns live-hiccup.core
  (:require [stasis.core :as stasis]
            [juxt.dirwatch :refer [watch-dir]]
            [etaoin.api :as eta]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [html5]]))

(def watcher (atom nil))

(def tester (atom nil))

(def pages {"/index.html" (html5
                           [:h1 {:style "text-align: center; color: blue;"} "Hello from hiccup!"]
                           [:h2 "Reminds me of a CL program called " [:a {:href "https://ninuzzo.github.io/hyde/"} "Hyde"]]
                           [:p "Pellentesque dapibus suscipit ligula.  Donec posuere augue in quam.  Etiam vel tortor sodales tellus ultricies commodo.  Suspendisse potenti.  Aenean in sem ac leo mollis blandit.  Donec neque quam, dignissim in, mollis nec, sagittis eu, wisi.  Phasellus lacus.  Etiam laoreet quam sed arcu.  Phasellus at dui in ligula mollis ultricies.  Integer placerat tristique nisl.  Praesent augue.  Fusce commodo.  Vestibulum convallis, lorem a tempus semper, dui dui euismod elit, vitae placerat urna tortor vitae lacus.  Nullam libero mauris, consequat quis, varius et, dictum id, arcu.  Mauris mollis tincidunt felis.  Aliquam feugiat tellus ut neque.  Nulla facilisis, risus a rhoncus fermentum, tellus tellus lacinia purus, et dictum nunc justo sit amet elit.

"])})

(def app (stasis/serve-pages pages))

(defn start-watcher []
  (let [browser (eta/firefox {:port 9797})]
    (eta/go browser "http://localhost:3000")
    (reset! watcher
            (watch-dir
             (fn [event] (eta/refresh browser))
             (clojure.java.io/file "src/live_hiccup/")))))
