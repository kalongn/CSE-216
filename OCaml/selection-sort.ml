let minValue list =
  let rec aux list min = match list with
  | [] -> min
  | h::t -> if(h < min) then (aux t h) else (aux t min)
in aux list max_int
;; 

let selectionSort list = 
  let rec aux acc list = match list with
  | [] -> acc
  | h::t -> if(h < minValue t) then (aux (acc@[h]) t) else (aux (acc@[minValue t]) t)
in aux [] list
;;

(*Could implement the minValue method into returning the index too? so it remove it from the tail list in the sort method*)