(ns calculator.core
  (:require
    [calculator.parser :as parser]))


(defn operation
  [action]
  (case action
    :ADD +
    :SUB -
    :MUL *
    :DIV /))


(defn calculate-expression
  [[action operand-1 operand-2]]
  (case action
    :NUMBER (parser/parse-number operand-1)
    :PARENTH (calculate-expression operand-1)
    (apply (operation action)
           (map calculate-expression [operand-1 operand-2]))))


;(defn calculate
;  [[action operand-1 operand-2]]
;  (case action
;    :NUMBER (parser/parse-number operand-1)
;    :PARENTH (calculate operand-1)
;    (apply (operation action) [(calculate operand-1)
;                               (calculate operand-2)])))


(defn calculator
  "Gets graph vector from parser/parse-expression"
  [[ident data]]
  (case ident
    :EXPRESSION {:result (calculate-expression data)}
    :EMPTY      {:error "expression is empty"}
    :ERROR      {:error "expression is incorrect"}
    {:error "input can't be parse"}))


(defn calculate
  [expression-string]
  (calculator
    (parser/parse-expression expression-string)))


(comment

  (calculator
    (parser/parse-expression "-2+(5-7*9)/4-3-(92+(5-3/3))"))




  )