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

let mytree = Node(1, Node(2 ,Empty, Empty), Node (3, Node (4 , Empty, Empty), Empty));;