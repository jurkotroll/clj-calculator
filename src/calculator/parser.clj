(ns calculator.parser
  (:require [instaparse.core :as insta]))

;; insta/parser returns parser function
(def calculator-parser
  (insta/parser
    "<string> = (EXPRESSION | EMPTY)
    EMPTY = <spaces*>
    EXPRESSION = (term | ADD | SUB)
    ADD = term <#'\\+'> (term | ADD | SUB)
    SUB = term <#'\\-'> (term | ADD | SUB)
    <term> = (item | MUL | DIV)
    MUL = item <#'\\*'> (item | MUL | DIV)
    DIV = item <#'/'> (item | MUL | DIV)
    <item> = <spaces?> (NUMBER | parentheses) <spaces?>
    <parentheses> = <'('> PARENTH <')'>
    PARENTH = (term | ADD | SUB)
    NUMBER = #'[-+]*[0-9]+(\\.[0-9]+)?'
    <spaces> = #'\\s'*"))


(defn parse-number
  [string]
  (Double/parseDouble string))
