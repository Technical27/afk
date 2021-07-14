(ns io.github.technical27.afk.plugin.Plugin
  (:gen-class
   :main false
   :extends io.github.technical27.afk.plugin.ClojurePlugin
   :methods [[isAFK [org.bukkit.entity.Player] Boolean]
             [registerAFKCallback [java.lang.Runnable] void]])
  (:require [io.github.technical27.afk.command :as cmd]
            [io.github.technical27.afk.placeholder :as placeholder]
            [io.github.technical27.afk.state :as state]
            [io.github.technical27.afk.auto :as auto])
  (:import [io.github.technical27.afk.listener Listener]))

(defn -isAFK
  [_ player]
  (state/get-afk player))

(defn -onEnable
  "Plugin enable funciton"
  [this]
  (println "afk: enable")
  (.setExecutor (.getCommand this "afk") cmd/afk)
  (.registerEvents (.getPluginManager (.getServer this)) (Listener.) this)
  (.runTaskTimer (auto/runnable) this 200 200)
  (when-let [exp (placeholder/expansion)]
    (.register exp)))

(defn -onDisable
  "Plugin disable funciton"
  [_]
  (println "afk: disable"))
