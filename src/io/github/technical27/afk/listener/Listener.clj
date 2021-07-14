(ns io.github.technical27.afk.listener.Listener
  (:gen-class
   :main false
   :constructors {[] []}
   :methods [[^{org.bukkit.event.EventHandler {}} onPlayerMove [org.bukkit.event.player.PlayerMoveEvent] void]
             [^{org.bukkit.event.EventHandler {}} onEntityTarget [org.bukkit.event.entity.EntityTargetLivingEntityEvent] void]
             [^{org.bukkit.event.EventHandler {}} onPlayerLeave [org.bukkit.event.player.PlayerQuitEvent] void]
             [^{org.bukkit.event.EventHandler {}} onPlayerKick [org.bukkit.event.player.PlayerKickEvent] void]
             [^{org.bukkit.event.EventHandler {}} onPlayerJoin [org.bukkit.event.player.PlayerJoinEvent] void]]
   :implements [org.bukkit.event.Listener])
  (:require [io.github.technical27.afk.state :as state]
            [io.github.technical27.afk.util :as util]))

(defn- moved-from-block?
  [l1 l2]
  (not (and (= (.getBlockX l1) (.getBlockX l2))
            (= (.getBlockY l1) (.getBlockY l2))
            (= (.getBlockZ l1) (.getBlockZ l2))
            (= (.getWorld l1) (.getWorld l2)))))

(defn -onPlayerMove
  [_ event]
  (let [player (.getPlayer event)]
    (when (moved-from-block? (.getFrom event) (.getTo event))
      (state/update-last-moved player)
      (when (state/get-afk player)
        (util/afk-end player)))))

(defn -onPlayerJoin
  [_ event]
  (state/add-player (.getPlayer event)))

(defn -onPlayerLeave
  [_ event]
  (state/remove-player (.getPlayer event)))

(defn -onPlayerKick
  [_ event]
  (state/remove-player (.getPlayer event)))

(defn -onEntityTarget
  [_ event]
  (let [target (.getTarget event)]
    (when (and target (state/get-afk target))
      (.setCancelled event true))))
