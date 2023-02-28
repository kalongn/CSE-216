(*Q1*)
type 'a binary_tree =
    | Empty
    | Node of 'a * 'a binary_tree * 'a binary_tree;;

let rec num_of_leaves tree = match tree with
  | Empty -> 1
  | Node(x,l,r) -> (num_of_leaves l)+(num_of_leaves r)
;;

let rec get_all_leaves tree = match tree with
  | Empty -> [Empty]
  | Node(x,l,r) -> (get_all_leaves l)@(get_all_leaves r)
;;

let rec depthOfTree tree = match tree with
| Empty -> 0
| Node(x,l,r) -> if( (depthOfTree l)) >= (depthOfTree r) then (1 + depthOfTree l) else (1 + depthOfTree r)
;;

let mytree = Node(1, Node(2 ,Empty, Empty), Node (3, Node (4 , Empty, Empty), Empty));;

(*Q2*)
type graph = {
  nodes : char list;
  edges : (char*char) list;
}
let g = {nodes = ['a'; 'b'; 'c'; 'd']; edges = [('d', 'c'); ('a', 'b'); ('a', 'c'); ('c', 'b')]};;

let rec neighbors g a cond = 
  let edge l (b,c) = if (b = a && cond c) then c::l else if c = a && cond b then b :: l else l
in List.fold_left edge [] g.edges 
and 
list_path g a to_b = match to_b with
| [] -> assert false
| a' :: _ -> if a' = a then [to_b] else
    let n = neighbors g a' ( fun c -> not (List.mem c to_b))
      in List.concat (List.map (fun c -> list_path g a (c :: to_b)) n)
and 
paths g a b = list_path g a [b];;  