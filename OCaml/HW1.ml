(*Helper Functions*)
let reverse_list list =
  let rec aux acc list = match list with
    | [] -> acc
    | h::t -> aux (h::acc) t 
  in aux [] list
;;

let rec map_list f list = match list with
  | [] -> []
  | h::t -> f(h)::(map_list f t)
;;

let rec fold_left_list f acc list = match list with
  | [] -> acc
  | h::t -> fold_left_list f (f acc h ) t
;;

let filter_list f list =
  let rec aux acc list = match list with
    | [] -> acc
    | h::t -> if(f(h)) then h::(aux acc t) else (aux acc t)
  in aux [] list
;;

(*Q1*)
let rec pow x n = match n with
  | 1 -> x
  | _ -> x * (pow x (n-1))
;;

let rec float_pow x n = match n with
  | 1 -> x
  | _ -> x *. (float_pow x (n-1))
;;

(*Q2*)
let rec compress list = match list with
  | x1::x2::xs -> if(x1=x2) then (compress (x2::xs)) else x1::(compress (x2::xs))
  | _ -> list
;;

(*Q3*)
let remove_if list f =
  let rec aux acc list = match list with
    | [] -> acc
    | h::t -> if(not(f(h))) then h::(aux acc t) else (aux acc t)
  in aux [] list
;;

(*Q4*)
let slice list begin_index end_index = 
  let rec aux acc list begin_index end_index = match list with
  | [] -> acc
  | h::t -> if(begin_index > 0) then (aux acc t (begin_index-1) end_index) else if(end_index > 0) then h::(aux acc t 0 (end_index-1)) else acc
in aux [] list begin_index (end_index-2)
;;