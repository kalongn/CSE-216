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
  | 0 -> 1
  | 1 -> x
  | _ -> x * (pow x (n-1))
;;

let rec float_pow x n = match n with
  | 0 -> 1.0
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

(*Q5*)
(*let equiv f list = 
  let rec aux acc f list = match list with
  | [] -> acc
  | h::t -> match acc with
    | [] -> [h]::acc
    | 
;;*)

(*Q6*)
let isPrime n = 
  let n = abs n in
    let rec isNotADivisor divisor =
      divisor * divisor > n || (n mod divisor <> 0 && isNotADivisor (divisor + 1)) in
    n <> 1 && isNotADivisor 2
;;
let goldbachpair n =
  let rec aux beginN endN = if(isPrime beginN && isPrime endN) then (beginN, endN) else (aux (beginN+1) (endN-1))
in (aux (1) (n-1))
;;

(*Q7*)
let rec identical_on f g list = match list with
  | [] -> true
  | h::t -> if((f h) = (g h)) then (identical_on f g t) else false
;;

(*Q8*)
let pairwisefilter cmp list = 
  let rec aux acc cmp list = match list with
  | [] -> acc
  | [h] -> acc@[h]
  | x1::x2::xs -> (cmp x1 x2)::(aux acc cmp xs)
in aux [] cmp list
;;


(*Q9*)
(*let polynomial list x =
  let rec aux total list x = match list with
  | [] -> total
  | (coef,expo)::t -> (aux (total+(coef*(pow (x) (expo)))) t x)
in aux 0 list x
;;*)
let polynomial list x = 
  fold_left_list (fun total (coef, expo) -> total + (coef * (pow (x) (expo))) ) (0) (list)
;;


(*Q10*)

(*let powerset list = 
  let aux acc list = match list with
    | [] -> acc
    | h::t ->  match t with
      | [] -> acc@[[h];[]]
      | h2::t -> 
*)
(*[], [h] [h::t1] [h::t1::t2]*)
