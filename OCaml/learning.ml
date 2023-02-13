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