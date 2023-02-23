
let rec last list = match list with
  | [] -> failwith " Empty "
  | h::[] -> h
  | h::t -> last t
;;

let scan_left f list = 
  let rec aux f list acc = match list with
  | [] -> acc
  | h::t -> if(acc = []) then (aux f t (h::acc)) else (aux f t (acc@[(f (last acc) h )]))
in aux f list []
;;
