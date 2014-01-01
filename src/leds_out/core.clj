(ns leds-out.core
  (:use [overtone.live :only [on-event]]
        [overtone.midi :only [midi-in midi-out]]
        [launchtone.core :only [make-app set-board! swap-board! on-button]]
        [launchtone.launchpad :only [note->point]]
        [launchtone.utils :only [enumerate debug]])
  (:require clojure.data))

(def new-board
  [[:e :e :e :e :e :e :e :e]
   [:e :e :e :e :e :e :e :e]
   [:e :e :e :e :e :e :e :e]
   [:e :e :e :g :e :e :e :e]
   [:e :e :g :g :g :e :e :e]
   [:e :e :e :g :e :e :e :e]
   [:e :e :e :e :e :e :e :e]
   [:e :e :e :e :e :e :e :e]])

(def app (make-app))

(defn legit?
  "Determine if a row or column is in bounds."
  [n]
  (and (>= n 0)
       (<= n 7)))

(defn tog
  "Toggle the value at a given row and column."
  [board row col]
  (when (and (legit? row)
             (legit? col))
    (let [old-row (board row)
          old-val (old-row col)
          new-val (if (= old-val :e) :g :e)]
      (assoc board row
             (assoc old-row col
                    new-val)))))

(defn move [row col]
  (debug "move...")
  (fn [board]
    (debug "move on board" board)
    (-> board
        (tog row col)
        (tog row (inc col))
        (tog row (dec col))
        (tog (dec row) col)
        (tog (inc row) col))))

(defn -main
  []
  (let [app (make-app)]
    (on-button app
               (fn [row col event-type m]
                 (let [board (@app :board)]
                   (debug "swapping board!")
                   (swap-board! app (move row col))))
               ::key-press)
    (set-board! app new-board)))
