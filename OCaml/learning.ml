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