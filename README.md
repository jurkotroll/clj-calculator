# Simple calculator - Clojure
## Project Description
Simple Calculator REST API using JSON. Supports input of mathematical expressions with the basic 
operations: +, -, *, and /. Allow the usage of parens and understand operator precedence. Also allow to get history.

## Install
To run service you'll need Java and Clojure (version at least "1.11.1") to be installed on your computer.  
Here you can get all information: [Install Clojure](https://clojure.org/guides/install_clojure)  

## Run project
In terminal run  
```
clj -M:repl/nrepl
```
If you want to use [Portal](https://github.com/djblue/portal#portal) run
```
clj -M:repl/nrepl:dev/deps
```
If you use Portal integrated with Intellij, its should open and connect automatically. 

In terminal you'll get information on which port nRepl have been started
> nREPL server started on port XXXXX on host localhost - nrepl://localhost:XXXXX

Find out how to connect your editor to the nRepl to use it.

## Start local server

To start server in nRepl run:
```
(server.core/start-server {:http-port 8000 :join? false})
```
You


starts on port 8000

In Postman use: 
For calculations

POST localhost:8000/calc

Body -> row 

{
"expression": "-1 * (2 * 6 / 3)"
}

you should get answer 

{
"result": -4.0
}

For history 

POST localhost:8000/history

Body -> row 

{
 "amount": x
}

where x is integer.

Your should get answer 

{
 "history-entries": 
 [
  {"result": -4.0,
  "expression": "-1 * (2 * 6 / 3)"}, 
  ...
 ]
}

