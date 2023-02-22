(ns calculator.core
  )

(defn calculate*
  [[action operand-1 operand-2]]
  (let [operation (get {:ADD + :SUB - :MUL * :DIV /} action)]
    (case action
      :NUMBER (parse-number operand-1)
      :PARENTH (calculate* operand-1)
      (operation (calculate* operand-1)
                 (calculate* operand-2)))))


(defn calculate-expression
  [[ident data]]
  (case ident
    :EXPRESSION {:result (calculate* data)}
    :EMPTY      {:error "expression is empty"}
    {:error "expression is incorrect"}))


(defn calculate-graph
  [graph]
  (if (insta/failure? graph)
    {:error "expression is incorrect"}
    (calculate-expression (first graph))))


(defn calculator
  [query-data]
  (let [graph (calculator-parser query-data)]
    (calculate-graph graph)))


(comment

  (calculator "--ut")
  (calculator-parser "-2+(5-7*9)/4-3")



  )