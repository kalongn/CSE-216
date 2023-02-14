(*Q2*)

let graph = [("","",0)];;

let minimum_weigh_edge list = 
  let rec aux min_edges list = match list with
    | [] -> min_edges
    | ((_,_,weight) as h)::t -> let (_,_,min) = min_edges in 
    if weight < min then aux h t else aux min_edges t
  in aux ("","",max_int) list
;;

minimum_weigh_edge graph;;

(*Q3*)
List.fold_left (fun( (_,_,x) as min) ((_,_,y) as curr) -> if y < x then curr else min) ("","",max_int) graph;;