(*Q1*)
let rec last list = match list with
  | [] -> None
  | h::[] -> Some h
  | h::t -> last t;;

(*Q2*)
let rec last_two list = match list with
  | [] | [_] -> None
  | [x; y] -> Some (x,y)
  | _ :: t -> last_two t;;

(*Q3*)
let rec atNth list index = match list with
  | [] -> None
  | h::t -> if index = 0 then Some (h) else atNth t (index-1);;

(*Q4*)
(*Non tail-recursive method*)
let rec length_non_tail list = match list with
| [] -> 1
| h::t -> 1 + length_non_tail t;;
(*Tail-recursive method*)
let length_tail list = 
  let rec aux n list = match list with
    | [] -> n
    | h::t -> aux (n+1) t
in aux 0 list;;
