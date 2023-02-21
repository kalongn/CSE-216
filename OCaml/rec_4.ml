(*Define a list*)
type 'a list = Nil | Cons of 'a * 'a list;;
let mylist = Cons(1,Cons(2,Nil));;
let rec length list = match list with
| Nil -> 0
| Cons(x,y) -> 1 + length y;;