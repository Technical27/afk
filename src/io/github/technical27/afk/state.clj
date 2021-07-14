(ns io.github.technical27.afk.state
  (:import [java.time Instant]))

(def players (atom {}))

(defn get-player
  [player]
  (get @players player))

(defn get-cooldown
  [player]
  (:cooldown (get-player player)))

(defn update-cooldown
  [player]
  (swap! players update player assoc :cooldown (Instant/now)))

(defn get-last-moved
  [player]
  (:last-moved (get-player player)))

(defn update-last-moved
  [player]
  (swap! players update player assoc :last-moved (Instant/now)))

(defn get-afk
  [player]
  (:afk (get-player player)))

(defn set-afk
  [player bool]
  (swap! players update player assoc :afk bool))

(defn remove-player
  [player]
  (swap! players dissoc player))

(defn add-player
  [player]
  (swap! players assoc player {:afk false :cooldown nil :last-moved (Instant/now)}))
