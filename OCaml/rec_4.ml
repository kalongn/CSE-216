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