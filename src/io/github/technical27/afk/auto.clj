(ns io.github.technical27.afk.auto
  (:require [io.github.technical27.afk.state :as state]
            [io.github.technical27.afk.util :as util]))

(defn- check-player
  [player]
  (let [time (state/get-last-moved player)]
    (when (and (not (state/get-afk player)) (util/check-auto-afk time))
      (util/afk-start player))))

(defn runnable
  []
  (proxy [org.bukkit.scheduler.BukkitRunnable] []
    (run []
      (doall (map check-player (keys @state/players))))))
