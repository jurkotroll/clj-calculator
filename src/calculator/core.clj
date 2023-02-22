(ns calculator.core
  (:require
    [calculator.parser :as parser]))

(defn calculate*
  [[action operand-1 operand-2]]
  (let [operation (get {:ADD + :SUB - :MUL * :DIV /} action)]
    (case action
      :NUMBER (parser/parse-number operand-1)
      :PARENTH (calculate* operand-1)
      (operation (calculate* operand-1)
                 (calculate* operand-2)))))


(defn calculator
  "Gets graph vector from parser/parse-expression"
  [[ident data]]
  (case ident
    :EXPRESSION {:result (calculate* data)}
    :EMPTY      {:error "expression is empty"}
    :ERROR      {:error "expression is incorrect"}
    {:fail "instaparser haven't return any graph, nether error"}))


(comment

  (calculator
    (parser/parse-expression "-2+(5-7*9)/4-3"))



  )