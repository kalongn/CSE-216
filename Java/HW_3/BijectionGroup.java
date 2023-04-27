import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BijectionGroup {
    public static <T> Set<Function<T, T>> bijectionsOf(Set<T> domain) {
        Set<Function<T, T>> bijections = new HashSet<>();
        if (domain.size() == 0) {
            return bijections;
        }
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
        List<List<T>> result = new ArrayList<>();
        backTrackPermutations(result, new ArrayList<>(), list);
        return result;
    }

    private static <T> void backTrackPermutations(List<List<T>> list, List<T> tempList, List<T> inputList) {
        if (tempList.size() == inputList.size()) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < inputList.size(); i++) {
                if (tempList.contains(inputList.get(i))) {
                    continue;
                }
                tempList.add(inputList.get(i));
                backTrackPermutations(list, tempList, inputList);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static <T> Group<Function<T, T>> bijectionGroup(Set<T> domain) {
        return new Group<Function<T, T>>() {

            @Override
            public Function<T, T> binaryOperation(Function<T, T> one, Function<T, T> other) {
                return one.compose(other);
            }

            @Override
            public Function<T, T> identity() {
                return new Function<T, T>() {
                    @Override
                    public T apply(T t) {
                        return t;
                    }
                };
            }

            @Override
            public Function<T, T> inverseOf(Function<T, T> t) {
                Set<Function<T, T>> allFunctions = bijectionsOf(domain);
                return allFunctions.stream().filter(fun -> {
                    return domain.stream().allMatch(i -> {
                        return binaryOperation(fun, t).apply(i).equals(identity().apply(i));
                    });
                }).findFirst().orElse(null);
            }
        };
    }

    public static void main(String... args) {

        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
        // you have to figure out the data type in the line below
        Set<Function<Integer, Integer>> bijections = bijectionsOf(a_few);
        bijections.forEach(aBijection -> {
            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });
        // you have to figure out the data types in the lines below
        // some of these data types are functional objects, so look into
        // java.util.function.Function
        Group<Function<Integer, Integer>> g = bijectionGroup(a_few);
        Function<Integer, Integer> f1 = bijectionsOf(a_few).stream().findFirst().get();
        Function<Integer, Integer> f2 = g.inverseOf(f1);
        Function<Integer, Integer> id = g.identity();
    }
}
