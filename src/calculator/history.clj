(ns calculator.history)


(def calculator-history (atom []))


(defn add-to-history
  [history new-event]
  (swap! history
         (fn [coll]
           (if (< (count @history) 20)
             (conj coll new-event)
             (conj (vec (rest coll)) new-event)))))


(defn take-from-history
  [history amount]
  (into []
        (take amount (rseq @history))))


(defn clean-history
  [history]
  (reset! history []))


(comment

  (tap> calculator-history)

  )