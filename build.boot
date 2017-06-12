(set-env!
 :source-paths #{"src/main/clj"}
 :resource-paths #{"src/main/resources"}
 :dependencies '[[adzerk/boot-test "1.2.0"      :scope "test"]
                 [samestep/boot-refresh "0.1.0" :scope "test"]
                 [adzerk/boot-cljs "2.0.0"      :scope "test"]
                 [adzerk/boot-reload "0.5.1"    :scope "test"]
                 [pandeiro/boot-http "0.8.3"    :scope "test"]
                 [proto-repl "0.3.1"            :scope "test"]
                 [compojure "1.6.0"             :scope "test"]
                ;; project
                 [org.clojure/clojure "1.9.0-alpha17"]
                 [org.clojure/clojurescript "1.9.562" :scope "provided"]  ;; TODO: need to keep this out of jar builds
                 [reagent "0.6.2"]
                 [mount "0.1.11"]])

(task-options!
 pom {:project 'base-core
      :version "1.0.0-SNAPSHOT"
      :description "Core Base functionality."})

(require
 '[adzerk.boot-test      :refer :all]
 '[samestep.boot-refresh :refer [refresh]]
 '[pandeiro.boot-http    :refer [serve]]
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-reload    :refer [reload]])

(deftask dev []
         (let [_ (merge-env! :source-paths #{"src/env/local/clj"})
               _ (merge-env! :resource-paths #{"src/env/local/resources"})]
           (comp
            (serve) ; :handler 'remote.app/handler :reload true)
            (repl "-s")
            (watch)
            (refresh)
            (reload)
            (cljs "-sO" "none"))))

(deftask testing []
         (let [_ (set-env! :source-paths #{"src/test/clj"})]
           (comp
            (watch)
            (test))))
