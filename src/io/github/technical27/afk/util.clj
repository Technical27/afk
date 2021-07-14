(ns io.github.technical27.afk.util
  (:require [io.github.technical27.afk.mob :as mob]
            [io.github.technical27.afk.state :as state]
            [io.github.technical27.afk.messages :as messages])
  (:import [java.time Duration Instant]
           [org.bukkit Bukkit]
           [io.github.technical27.afk.events AFKStart AFKEnd]))

(defn check-auto-afk
  [time]
  (> (.toMinutes (Duration/between time (Instant/now))) 3))

(defn- send-afk-start-event
  [player]
  (.callEvent (Bukkit/getPluginManager) (AFKStart. player)))

(defn- send-afk-end-event
  [player]
  (.callEvent (Bukkit/getPluginManager) (AFKEnd. player)))

(defn afk-start
  [player]
  (messages/broadcast-afk-start player)
  (state/set-afk player true)
  (send-afk-start-event player)
  (mob/reset-nearby-entities player)
  (state/update-cooldown player))

(defn afk-end
  [player]
  (messages/broadcast-afk-end player)
  (state/set-afk player false)
  (send-afk-end-event player))
