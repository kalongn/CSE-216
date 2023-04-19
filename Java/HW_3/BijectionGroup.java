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

    public interface Group<T> {
        /**
         * Performs the binary operation, as defined by the group, of one object with
         * the other specified
         * object. The implementer must take care to ensure that the binary operation is
         * <ul>
         * <li><b>closed</b> for the parameter type <code>T</code>. That is, the result
         * of the binary
         * operation is a valid member of the set that defines the type <code>T</code>
         * (taking the
         * denotational semantics view of data types). For example, addition is a binary
         * operation
         * that is closed for integers, but division is not.</li>
         * <li><b>associative</b>. That is, for any elements <code>x</code>,
         * <code>y</code>, and
         * <code>z</code> in this group,
         * <code>binaryOperation(binaryOperation(x, y), z)</code> is
         * equal to <code>binaryOperation(x, binaryOperation(y, z))</code>. For example,
         * addition is
         * an associative binary operation for integers.</li>
         * <li>respectful of the identity element. That is, for any element
         * <code>x</code> in this
         * group and the identity element <code>e</code> of this group,
         * <code>binaryOperation(x, e)</code> is equal to <code>x</code>, and
         * <code>binaryOperation(e, x)</code> is also equal to <code>x</code>. For
         * example,
         * <code>0</code> is the identity element of the group of integers under
         * addition.</li>
         * </ul>
         *
         * @param one   the object that is the first argument of the binary operation.
         * @param other the other object (the second argument of the binary operation)
         *              to be combined with
         *              this object as per the group’s binary operation.
         * @return the result of the binary operation on this object with the other
         *         specified object.
         */
        T binaryOperation(T one, T other);

        /**
         * @return the identity element of this group.
         */
        T identity();

        /**
         * In a group, every element <code>x</code> must have its inverse, which is an
         * element <code>y</code>
         * in the group such that <code>binaryOperation(x, y)</code> is equal to
         * <code>binaryOperation(y, x)</code>, and both yield the identity element of
         * the group. For example,
         * for the group of integers under addition, the negative of any integer is its
         * inverse.
         *
         * @return the inverse of this object.
         */
        T inverseOf(T t);

        /**
         * This is a utility function, serving as the definition of exponentiation for
         * this group.
         * Exponentiation is defined as <code>exponent(t, 0)</code> being the
         * <code>identity()</code>
         * element, and <code>exponent(t, n)</code> being
         * <code>binaryOperation(t, exponent(t, n-1))</code>.
         *
         * @param t the group element serving as the base.
         * @param k the integer exponent, indicating the number of times the binary
         *          operation is applied
         *          on <code>t</code>. The exponent must be a non-negative integer
         *          value.
         * @return the result of the binary operation applied <code>k</code> times on
         *         <code>t</code>.
         */
        default T exponent(T t, int k) {
            if (k < 0)
                throw new IllegalArgumentException("The exponent must be a non-negative integer value.");
            return k == 0 ? identity() : binaryOperation(t, exponent(t, k - 1));
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
                        return binaryOperation(fun, t).apply(i) == identity().apply(i);
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
