(*1*)
(*a*)let double x = 2*x;;
(*b*)let square x = x*x;;
(*c*)let twice f x = f (f x);;
(*d*)let quad = twice double;;
(*e*)let fourth = twice square;;

(*2*)
(*a*)let tripleFloat x = 3.0*.x;;
(*b*)let thrice f x = f(f(f(x)));;
(*c*)let composition f g x = f(g(x));;
(*d*)let div x y = x/y;;
(*e*)let triple3 = thrice tripleFloat;;

(*3*)
let rec repeat f n x = match n with (*instead of match n with, you can use function keyword. The function keyword takes in the last input.*)
| 0 -> x
| _ -> repeat f (n-1) (f (x))
;;

let inc x = x + 1;;
let z = repeat inc 3 0;;
print_int z;;
print_endline

(*4*)
let f list =
  let rec aux acc = function
    | [] -> acc
    | h::t -> aux (h::acc) t 
  in aux [] list
;;

(*5*)
let rec remove list n = match list with
| [] -> []
| h::t -> if n=0 then t else h::(remove t (n-1))
;;

(*6*)
let rec returnLast list = match list with
| [] -> 0
| h::[] -> h
| h::t -> returnLast t
;;
