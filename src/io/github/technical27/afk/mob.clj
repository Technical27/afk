(ns io.github.technical27.afk.mob)

(defn is-mob?
  "returns if entity is a mob"
  [entity]
  (instance? org.bukkit.entity.Mob entity))

(defn is-player?
  "returns if entity is a player"
  [entity]
  (instance? org.bukkit.entity.Player entity))

(defn is-targeting-player?
  "checks if an entity is targeting a player"
  [entity player]
  (= (.getTarget entity) player))

(defn reset-target
  "resets a mobs target"
  [mob]
  (.setTarget mob nil))

(defn reset-if-targeting-player
  "resets the target if it is the player"
  [player]
  (fn [entity]
    (when (and (is-mob? entity) (is-targeting-player? entity player))
      (reset-target entity))))

(defn reset-nearby-entities
  "resets the target of nearby entities"
  [player]
  (doall (map (reset-if-targeting-player player) (.getNearbyEntities player 40 40 40))))
