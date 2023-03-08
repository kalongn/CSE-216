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

(*Tail of a List*)
let rec last list = match list with
  | [] -> None
  | h::[] -> Some h
  | h::t -> last t;;

(*Last Two Elements of a List*)
let rec last_two list = match list with
  | [] | [_] -> None
  | [x; y] -> Some (x,y)
  | _ :: t -> last_two t;;

(*N'th Element of a List*)
let rec atNth list index = match list with
  | [] -> None
  | h::t -> if index = 0 then Some (h) else atNth t (index-1);;

(*Length of a List*)
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

(*Reverse a List*)
let reverse_list list =
  let rec aux acc list = match list with
    | [] -> acc
    | h::t -> aux (h::acc) t 
  in aux [] list
;;

(*Palindrome*)
let pallindrome list =
  list = reverse_list list
;;

(*Flatten a List*)
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

(*Eliminate Duplicates*)
let rec compress list = match list with
  | x1::x2::xs -> if(x1 = x2) then (compress (x2::xs)) else x1::(compress (x2::xs))
  | [x] -> [x]
  | [] -> []
;;

(*Pack Consecutive Duplicates*)
let pack list =
  let rec aux current acc list = match list with
    | [] -> []    (* Can only be reached if original list is empty *)
    | [x] -> (x :: current) :: acc
    | a :: (b :: _ as t) ->
       if a = b then aux (a :: current) acc t else aux [] ((a :: current) :: acc) t 
  in List.rev (aux [] [] list)
;;

(*Run-Length Encoding*)
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

(*Insert an Element at a Given Position Into a List*)
let insert_at ins k list = 
  let rec aux acc ins k list = match list with
  | [] -> acc@[ins]
  | h::t -> if(k=0) then acc@[ins]@(h::t) else (aux (acc@[h]) ins (k-1) t)
in aux [] ins k list
;;

(*Create a List Containing All Integers Within a Given Range*)
let range beginNum endNum = 
  let rec aux acc beginNum endNum = match beginNum = endNum with
  | false -> if (beginNum > endNum) then (aux (acc@[beginNum]) (beginNum-1) endNum) else (aux (acc@[beginNum]) (beginNum+1) endNum)
  | true ->  acc@[endNum]
in aux [] beginNum endNum
;;

(*Tree Stuff*)

(*Generic Binary tree Variant type*)
type 'a binary_tree =
  | Empty
  | Node of 'a * 'a binary_tree * 'a binary_tree
;;

(*Count all leaves*)
let rec num_of_leaves tree = match tree with
  | Empty -> 1
  | Node(x,l,r) -> (num_of_leaves l)+(num_of_leaves r)
;;

(*Get all Leaf nodes*)
let rec get_all_leaves tree = match tree with
  | Empty -> [Empty]
  | Node(x,l,r) -> (get_all_leaves l)@(get_all_leaves r)
;;

(*Get the depth of Tree*)
let rec depthOfTree tree = match tree with
  | Empty -> 0
  | Node(x,l,r) -> if( (depthOfTree l)) >= (depthOfTree r) then (1 + depthOfTree l) else (1 + depthOfTree r)
;;

(*Check if 2 binary tree is mirror of each other, including the value the nodes is carrying.*)
let rec is_mirror leftTree rightTree = match leftTree, rightTree with
  | Empty, Empty -> true
  | Node(lx, ll, lr), Node(rx, rl,rr) -> (lx = rx) && (is_mirror ll rr) && (is_mirror lr rl)
  | _ -> false
;;

(*Check if 2 binary is structurally mirr of each other*)
let rec is_mirror_structure leftTree righTree = match leftTree, righTree with
  | Empty, Empty -> true
  | Node(_, ll, lr), Node(_, rl, rr) -> (is_mirror_structure ll rr) && (is_mirror_structure rl lr)
  | _ -> false
;;

(*Determine if the a binary tree is symmetric or not*)
let is_symmetric tree = match tree with
  | Empty -> true
  | Node(_, l, r) -> is_mirror_structure l r
;;

let rec insert tree x = match tree with
  | Empty -> Node(x, Empty, Empty)
  | Node(h, l ,r ) -> if (x = h) then tree else if(x > h) then Node(h, l, (insert r x)) else Node(h, (insert l x), r)
;;

let construct list = 
  let rec aux list currTree = match list with
  | [] -> currTree
  | h::t -> (aux t (insert currTree h))
in aux list Empty
;;