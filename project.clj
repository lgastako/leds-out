(defproject leds-out "0.1.0-SNAPSHOT"
  :description "leds-out game for Launchpad S."
  :url "http://github.com/lgastako/leds-out"
  :license {:name "Public Domain"
            :url "http://creativecommons.org/publicdomain/zero/1.0/"}
  :main ^:skip-aot leds-out.core
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [overtone "0.9.1"]
                 [launchtone "0.1.0-SNAPSHOT"]])
