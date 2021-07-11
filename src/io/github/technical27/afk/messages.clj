(ns io.github.technical27.afk.messages
  (:import [org.bukkit Bukkit ChatColor]))

(defn- afk
  [name s]
  (str ChatColor/GOLD name ChatColor/DARK_AQUA s))

(defn afk-start
  [name]
  (afk name " is now AFK"))

(defn afk-end
  [name]
  (afk name " is no longer AFK"))

(defn broadcast-afk-start
  [player]
  (Bukkit/broadcastMessage (afk-start (.getName player))))

(defn broadcast-afk-end
  [player]
  (Bukkit/broadcastMessage (afk-end (.getName player))))

(def afk-tag (str ChatColor/GOLD "[AFK]" ChatColor/RESET " "))
