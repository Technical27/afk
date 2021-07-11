(ns io.github.technical27.afk.placeholder
  (:import [org.bukkit Bukkit])
  (:require [io.github.technical27.afk.messages :as messages]
            [io.github.technical27.afk.state :as state]))

(defn- placeholder-request
  [player ident]
  (when (and player (= ident "status"))
    (when (contains? @state/players player)
      messages/afk-tag)))

(defn expansion
  []
  (when (.getPlugin (Bukkit/getPluginManager) "PlaceholderAPI")
    (proxy [me.clip.placeholderapi.expansion.PlaceholderExpansion] []
      (persist [] true)
      (canRegister [] true)
      (getAuthor [] "technical27")
      (getIdentifier [] "afk")
      (getVersion [] "0.0.69")
      (onPlaceholderRequest [player ident] (or (placeholder-request player ident) "")))))
