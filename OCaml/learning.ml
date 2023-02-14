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

(*Q5*)
let reverse_list list =
  let rec aux acc list = match list with
    | [] -> acc
    | h::t -> aux (h::acc) t 
  in aux [] list
;;

(*Q6*)
let pallindrome list =
  list = reverse_list list
;;

(*Q7*)
type 'a node =
  | One of 'a 
  | Many of 'a node list
let flatten_list list = 
  let rec aux acc list = match list with
    | [] -> acc
    | One h::t -> aux (h::acc) t
    | Many hl::t -> aux (aux acc hl) t
  in reverse_list (aux [] list)
;; 