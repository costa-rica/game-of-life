(ns game-of-life.draw
  (:require [game-of-life.game :as game]))

(def cell-size 10)
(def board-size (* cell-size 50))
(def glider #{[1 1] [1 3] [2 3] [2 2] [3 2]})

(defn draw [living-cells]
  (let [canvas (.getElementById js/document "canvas")
        context (.getContext canvas "2d")]
    (set! (.-width canvas) board-size)
    (set! (.-height canvas) board-size)
    (set! (.-fillStyle context) "rgb(0, 0, 0)")
    (.clearRect context 0 0 board-size board-size)
    (doseq [[x y] living-cells]
      (.fillRect context (* cell-size x) (* cell-size y) cell-size cell-size))))

(defn run [board]
  (draw board)
  (js/setTimeout (fn [] (run (game/advance board))) 100))

(defn start []
  (run glider))