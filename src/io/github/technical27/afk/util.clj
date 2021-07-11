(ns io.github.technical27.afk.util
  (:import [java.time Instant])
  (:require [io.github.technical27.afk.mob :as mob]
            [io.github.technical27.afk.state :as state]
            [io.github.technical27.afk.messages :as messages]))

(defn afk-start
  [player]
  (mob/reset-nearby-entities player)
  (messages/broadcast-afk-start player)
  (swap! state/cooldowns conj {player (Instant/now)})
  (swap! state/players conj player))

(defn afk-end
  [player]
  (messages/broadcast-afk-end player)
  (swap! state/players disj player))
