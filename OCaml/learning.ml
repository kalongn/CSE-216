(*Q1*)
let rec last list = match list with
| [] -> None
| h::[] -> Some h
| h::t -> last t;;

