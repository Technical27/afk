(ns io.github.technical27.afk.plugin.Plugin
  (:gen-class
   :main false
   :extends io.github.technical27.afk.plugin.ClojurePlugin
   :methods [[isAFK [org.bukkit.entity.Player] Boolean]])
  (:require [io.github.technical27.afk.command :as cmd]
            [io.github.technical27.afk.placeholder :as placeholder]
            [io.github.technical27.afk.state :as state])
  (:import [io.github.technical27.afk.listener Listener]))

(defn -isAFK
  [_ player]
  (contains? @state/players player))

(defn -onEnable
  "Plugin enable funciton"
  [this]
  (println "afk: enable")
  (.setExecutor (.getCommand this "afk") cmd/afk)
  (.registerEvents (.getPluginManager (.getServer this)) (Listener.) this)
  (when-let [exp (placeholder/expansion)]
    (.register exp)))

(defn -onDisable
  "Plugin disable funciton"
  [_]
  (println "afk: disable"))
