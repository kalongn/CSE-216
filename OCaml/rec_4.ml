(*Define a list*)
type 'a list = Nil | Cons of 'a * 'a list;;
let mylist = Cons(1,Cons(2,Nil));;
let rec length list = match list with
  | Nil -> 0
  | Cons(x,y) -> 1 + length y;;

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

(*Variant type, or (tree here)*)
(*Record type, and (node here)*)

(*Q2*)
let rec nodes_and_leaves tree = match tree with
  | Leaf x -> ([], [x])
  | Tree {operator = op; left = x; right = y} -> 
    let (left_nodes, left_leaves) = nodes_and_leaves x 
    and (right_nodes, right_leaves) = nodes_and_leaves y
  in (op :: left_nodes @ right_nodes, left_leaves @ right_leaves);;