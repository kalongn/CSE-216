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

let rec exist_list f list = match list with
  | [] -> false
  | h::t -> if(f(h)) then true else exist_list f t
;;

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

(*Q8*)
let rec compress list = match list with
  | x1::x2::xs -> if(x1 = x2) then (compress (x2::xs)) else x1::(compress (x2::xs))
  | [x] -> [x]
  | [] -> []
;;

(*Q9*)
let pack list =
  let rec aux current acc list = match list with
    | [] -> []    (* Can only be reached if original list is empty *)
    | [x] -> (x :: current) :: acc
    | a :: (b :: _ as t) ->
       if a = b then aux (a :: current) acc t else aux [] ((a :: current) :: acc) t 
  in List.rev (aux [] [] list)
;;

(*Q10*)
let encode list =
  let rec aux count acc list = match list with
    | [] -> [] (* Can only be reached if original list is empty *)
    | [x] -> (count + 1, x) :: acc
    | a :: (b :: _ as t) -> if a = b then aux (count + 1) acc t else aux 0 ((count + 1, a) :: acc) t 
  in List.rev (aux 0 [] list)
;;

(*Rotate a list N places to the left.*)
let rotate list n = 
  let rec aux acc list n = match list with
  | [] -> acc
  | h::t -> if(n > 0) then (aux (acc@[h]) t (n-1))else (h::t)@acc
in aux [] list n
;;

(*Remove the K'th element from a list.*)
let rec remove_at k list = match list with
  | [] -> failwith "Wrong indexes"
  | h::t -> if(k = 0) then t else h::(remove_at (k-1) t)
;;