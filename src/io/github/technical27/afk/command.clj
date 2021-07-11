(ns io.github.technical27.afk.command
  (:import [java.time Duration Instant]
           [org.bukkit ChatColor]
           [org.bukkit.command CommandExecutor])
  (:require [io.github.technical27.afk.state :as state]
            [io.github.technical27.afk.util :as util]
            [io.github.technical27.afk.mob :as mob]))

(defn- past-cooldown?
  [time]
  (> (.toSeconds (Duration/between time (Instant/now))) 20))

(defn- check-cooldown
  [player]
  (and (contains? @state/cooldowns player) (not (past-cooldown? (get @state/cooldowns player)))))

(defn- afk-handle
  [player]
  (if (contains? @state/players player)
    (util/afk-end player)
    (if (check-cooldown player)
      (.sendMessage player (str ChatColor/RED "Please wait a bit before running the command again"))
      (util/afk-start player))))

(def afk
  "/afk command constructor"
  (reify CommandExecutor
    (onCommand [this sender cmd label args]
      (do
        (if (mob/is-player? sender)
          (afk-handle sender)
          (.sendMessage sender (str ChatColor/DARK_RED "This can only be run as a Player")))
        true))))
