(ns calculator.history)


(def calculator-history (atom []))


(defn add-to-history
  [history new-event]
  (swap! history conj new-event))


(defn take-from-history
  [history amount]
  (into []
        (take amount (rseq @history))))


(defn clean-history
  [history]
  (reset! history []))
