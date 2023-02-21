(*Define a list*)
type 'a list =  
  | Nil 
  | Cons of 'a * 'a list
;;
let mylist = Cons(1,Cons(2,Nil));;
let rec length list = match list with
  | Nil -> 0
  | Cons(x,y) -> 1 + length y
;;

(*Q1*)
type ('a,'b) tree =
  | Leaf of 'a
  | Tree of ('a,'b) node
and
('a,'b) node = {
  operator: 'b;
  left: ('a,'b) tree;
  right: ('a,'b) tree
};;

(*Variant type, or (tree here ^)*)
(*Record type, and (node here ^)*)
(*
Extra Notes:
  Sum Types vs Product Types
  Variants | records/ tuples
*)
(*
type t =
  | C1 ot t1
;;
Variant constructors must start with a capital letter
 - Can include optional data carried by the constructor (See Empty below v)
*)

(*Q2*)
let rec nodes_and_leaves tree = match tree with
  | Leaf x -> ([], [x])
  | Tree {operator = op; left = x; right = y} -> 
    let (left_nodes, left_leaves) = nodes_and_leaves x 
    and (right_nodes, right_leaves) = nodes_and_leaves y
  in (op :: left_nodes @ right_nodes, left_leaves @ right_leaves);;

nodes_and_leaves (Tree {operator = "+"; left = Leaf 10; right = Leaf 12});;
nodes_and_leaves (Tree {operator = "+"; left = Tree {operator = "-"; left = Leaf 10; right = Leaf 20}; right =Tree {operator = "-"; left = Leaf 30; right = Leaf 40}});;

(*Q3*)
type 'a binary_tree =
	| Empty (*Empty of 'a if you want data in the leaf node*)
	| Node of 'a * 'a binary_tree * 'a binary_tree
;;

let rec num_of_leaves tree = match tree with
  | Empty -> 1
  | Node(x,l,r) -> (num_of_leaves l)+(num_of_leaves r)
;;

let rec get_all_leaves tree = match tree with
| Empty -> [Empty]
| Node(x,l,r) -> (get_all_leaves l)@(get_all_leaves r)
;;