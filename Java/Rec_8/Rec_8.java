package Java.Rec_8;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;


class Q1 {
    public static void main(String[] args) {
        final int number = 4;
        Predicate<Integer> compute = p -> p > 5;
        Predicate<Integer> process = p -> p % 3 == 0;
        BiPredicate<Integer, String> matchesLength = (p1, p2) -> p1 == p2.length();
        Function<Integer, Double> anotherCompute = integer -> integer * 2.5;
        String a = compute.test(number) && process.test(number) ? "TRUE" : "FALSE";
        System.out.println(a);
        System.out.println(matchesLength.test(3, "yes"));
        System.out.println(anotherCompute.apply(6));
        System.out.println(anotherCompute.andThen(b -> b + 5.0).apply(5));
        // System.out.println(anotherCompute.compose().apply(5));
        System.out.println(anotherCompute.apply(10));
    }
}

class Q2 {
    public static int sum(Collection<Integer> collection) {
        return collection.stream().reduce(0, (a, b) -> a + b);
    }

    public static void square(Collection<Integer> collection) {
        collection.stream().map(a -> a * a).forEach(System.out::println);

    }
}

class Q3 {
}

public class Rec_8 {

}
