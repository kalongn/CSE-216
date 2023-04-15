import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BijectionGroup {
    public static <T> Set<Function<T, T>> bijectionsOf(Set<T> domain) {
        Set<Function<T, T>> bijections = new HashSet<>();
        List<T> domainList = new ArrayList<>(domain);
        List<List<T>> allPermutations = getAllPermutations(domainList);
        for (List<T> permutation : allPermutations) {
            Map<T, T> map = new HashMap<>();
            for (int i = 0; i < domainList.size(); i++) {
                map.put(domainList.get(i), permutation.get(i));
            }
            bijections.add(t -> map.get(t));
        }
        return bijections;
    }

    private static <T> List<List<T>> getAllPermutations(List<T> list) {
        if (list.size() == 0) {
            List<List<T>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            T current = list.get(i);
            List<T> remaining = new ArrayList<>(list);
            remaining.remove(i);
            List<List<T>> permutations = getAllPermutations(remaining);
            for (List<T> permutation : permutations) {
                permutation.add(0, current);
                result.add(permutation);
            }
        }
        return result;
    }

    public static void main(String... args) {
        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
        // you have to figure out the data type in the line below
        Set<Function<Integer, Integer>> bijections = bijectionsOf(a_few);
        bijections.forEach(aBijection -> {
            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });
    }
}
