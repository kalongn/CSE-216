package Java.Rec_8;

import java.util.function.Predicate;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class Test {
    public static void main(String[] args) {
        final int number = 4;
        Predicate<Integer> compute = p -> p > 5;
        Predicate<Integer> process = p -> p % 3 == 0;
        BiPredicate<Integer,String> matchesLength = (p1,p2) -> p1==p2.length();
        Function<Integer, Double> anotherCompute = integer -> integer * 2.5;
        String a = compute.test(number) && process.test(number) ? "TRUE" : "FALSE";
        System.out.println(a);
        System.out.println(matchesLength.test(3, "yes"));
        System.out.println(anotherCompute.apply(6));
        System.out.println(anotherCompute.andThen(b -> b + 5.0).apply(5));
        //System.out.println(anotherCompute.compose().apply(5));
        System.out.println(anotherCompute.apply(10));
        
    }
}
