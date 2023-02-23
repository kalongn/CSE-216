let rec selection_sort l =
  let rec getMin list = match list with
    | [] -> failwith " Empty "
    | [m] -> m
    | h::t -> min h (getMin t)
  in let rec removeMin m list = match list with
      | [] -> failwith " Empty "
      | h::t -> if (h = m) then t else h::(removeMin m t)
    in let rec sort list = match list with 
        | [] -> []
        | liste -> let min_val = getMin liste in min_val::(sort(removeMin min_val liste))
in sort l
;;
