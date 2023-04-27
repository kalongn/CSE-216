import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * This class is a testing class for HW_3 CSE 216 (Spring 2023).
 *
 * @author Ka Long Ngai
 */
public class HW3_Test {

    public static void main(String[] args) {
        // SimpleUtils.java Test methods below.
        System.out.println();
        testLeast();
        testFlatten();

        System.out.println("\n----------\n");
        // HigherOrderUtils.java Test methdos below.
        testAdd();
        testSubtract();
        testMultiply();
        testDivide();
        testZip();

        System.out.println("\n----------\n");
        // BijectionGroup.java Test methods below.
        testBijectionsOf();

        System.out.println();
    }

    // SimpleUtils.java Test methods below
    private static class Student implements Comparable<Student> {
        String name;
        int age;

        Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public boolean equals(HW3_Test.Student o) {
            if (!this.name.equals(o.name) || this.age != o.age) {
                return false;
            }
            return true;
        }

        @Override
        public int compareTo(HW3_Test.Student o) {
            return Integer.compare(this.age, o.age);
        }
    }

    private static void testLeast() {
        List<Integer> list = Arrays.asList(5, 8, 3, 6, 1, 7, 2, 4);
        if (!SimpleUtils.least(list, true).equals(1)) {
            throw new AssertionError("Failed to obatin Lowest Value at Least_Test_1");
        } else {
            System.out.println("Least_Test_1_Passed");
        }

        List<String> strings = Arrays.asList("apple", "banana", "cherry", "date");
        if (!SimpleUtils.least(strings, true).equals("apple")) {
            throw new AssertionError("Failed to obatin Lowest Value at Least_Test_2");
        } else {
            System.out.println("Least_Test_2_Passed");
        }

        Set<Double> doubles = new HashSet<>(Arrays.asList(1.2, 3.4, 0.9, -2.1, 5.6));
        if (!SimpleUtils.least(doubles, true).equals(-2.1)) {
            throw new AssertionError("Failed to obatin Lowest Value at Least_Test_3");
        } else {
            System.out.println("Least_Test_3_Passed");
        }

        List<Student> students = new ArrayList<>();
        students.add(new Student("Naxy", 20));
        students.add(new Student("Adam", 20));
        students.add(new Student("Banerjee", 40));
        if (!SimpleUtils.least(students, true).equals(new Student("Naxy", 20))) {
            throw new AssertionError("Failed to obatin Lowest Value at Least_Test_4_1");
        } else {
            System.out.println("Least_Test_4_1_Passed");
        }
        if (!SimpleUtils.least(students, false).equals(new Student("Adam", 20))) {
            throw new AssertionError("Failed to obatin Lowest Value at Least_Test_4_2");
        } else {
            System.out.println("Least_Test_4_2_Passed");
        }
    }

    private static void testFlatten() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);

        List<String> expected = Arrays.asList("one -> 1", "two -> 2", "three -> 3", "four -> 4");
        List<String> actual = SimpleUtils.flatten(map);
        if (!expected.equals(actual)) {
            throw new AssertionError();
        }

        Map<Character, String> anotherMap = new HashMap<>();
        anotherMap.put('a', "apple");
        anotherMap.put('b', "banana");
        anotherMap.put('c', "cherry");

        expected = Arrays.asList("a -> apple", "b -> banana", "c -> cherry");
        actual = SimpleUtils.flatten(anotherMap);
        if (!expected.equals(actual)) {
            throw new AssertionError("Failed to obatin correct Flatten result at Flatten_1_Test");
        } else {
            System.out.println("Flatten_1_Test_Passed");
        }

        Map<Integer, Boolean> emptyMap = Collections.emptyMap();
        expected = Collections.emptyList();
        actual = SimpleUtils.flatten(emptyMap);
        if (!expected.equals(actual)) {
            throw new AssertionError("Failed to obatin correct Flatten result at Flatten_2_Test");
        } else {
            System.out.println("Flatten_2_Test_Passed");
        }
    }

    // HigherOrderUtils.java Test methdos below
    private static void assertDoubleEquals(String functionName, double expected, double actual) {
        if (Math.abs(expected - actual) > 0.00001) {
            System.err.println(functionName + " failed: expected " + expected + ", but got " + actual);
        } else {
            System.out.println(functionName + " passed");
        }
    }

    private static void assertStringEquals(String functionName, String expected, String actual) {
        if (!expected.equals(actual)) {
            System.err.println(functionName + " failed: expected " + expected + ", but got " + actual);
        } else {
            System.out.println(functionName + " passed");
        }
    }

    private static void testAdd() {
        double expected = 5.0;
        double actual = HigherOrderUtils.add.apply(2.0, 3.0);
        assertDoubleEquals("add", expected, actual);
    }

    private static void testSubtract() {
        double expected = -1.0;
        double actual = HigherOrderUtils.subtract.apply(2.0, 3.0);
        assertDoubleEquals("subtract", expected, actual);
    }

    private static void testMultiply() {
        double expected = 6.0;
        double actual = HigherOrderUtils.multiply.apply(2.0, 3.0);
        assertDoubleEquals("multiply", expected, actual);
    }

    private static void testDivide() {
        double expected = 0.5;
        double actual = HigherOrderUtils.divide.apply(2.0, 4.0);
        assertDoubleEquals("divide", expected, actual);

        try {
            HigherOrderUtils.divide.apply(2.0, 0.0);
            System.err.println("testDivide with 0 divisor failed to throw an exception");
        } catch (ArithmeticException e) {
            System.out.println("testDivide with 0 divisor successfully throw an exception");
        }
    }

    private static void testZip() {
        List<Double> args = Arrays.asList(-0.5, 2.0, 3.0, 0.0, 4.0);
        List<HigherOrderUtils.NamedBiFunction<Double, Double, Double>> bfs = Arrays.asList(
                HigherOrderUtils.add, HigherOrderUtils.multiply, HigherOrderUtils.add, HigherOrderUtils.divide);

        double expected = 1.125;
        double actual = HigherOrderUtils.zip(args, bfs);
        assertDoubleEquals("zip_1", expected, actual);

        List<String> strings = Arrays.asList("a", "n", "t");
        BiFunction<String, String, String> concat = (s, t) -> s + t;
        String s = HigherOrderUtils.zip(strings, Arrays.asList(concat, concat));
        assertStringEquals("zip_2", "ant", s);
    }

    // BijectionGroup.java Test
    private static void testBijectionsOf() {
        // Test with an empty domain
        Set<Integer> emptyDomain = new HashSet<>();
        Set<Function<Integer, Integer>> emptyResult = BijectionGroup.bijectionsOf(emptyDomain);
        if (!emptyResult.isEmpty()) {
            System.out.println("bijectionsOf_Test_1_Size_Failed");
        } else {
            System.out.println("bijectionsOf_Test_1_Size_Passed");
        }
        System.out.println();

        Set<Integer> microDomain = new HashSet<>(Arrays.asList(1));
        Set<Function<Integer, Integer>> microResult = BijectionGroup.bijectionsOf(microDomain);
        if (factorial(microDomain.size()) != microResult.size()) {
            System.out.println("bijectionsOf_Test_1_2_Size_Failed");
        } else {
            System.out.println("bijectionsOf_Test_1_2_Size_Passed");
        }
        System.out.println();
        try {
            Set<Function<Integer, Integer>> nullResult = BijectionGroup.bijectionsOf(null);
            System.out.println("bijectionsOf_Test_1_3_Null_Passed");
        } catch (NullPointerException e) {
            System.out.println("bijectionsOf_Test_1_3_Null_Failed");
        }
        System.out.println();

        // Test with a small domain of integers
        Set<Integer> smallDomain = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Function<Integer, Integer>> smallResult = BijectionGroup.bijectionsOf(smallDomain);
        if (factorial(smallDomain.size()) != smallResult.size()) {
            System.out.println("bijectionsOf_Test_2_Size_Failed");
        } else {
            System.out.println("bijectionsOf_Test_2_Size_Passed");
        }
        System.out.println("bijectionsOf_Test_2_Display (Check Manually)");
        smallResult.forEach(aBijection -> {
            smallDomain.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });
        System.out.println("Inverses_Identity_Function_Test_2_Display (Check Manually)");

        Group<Function<Integer, Integer>> smallGroup = BijectionGroup.bijectionGroup(smallDomain);
        Function<Integer, Integer> f1_int = BijectionGroup.bijectionsOf(smallDomain).stream().findFirst().get();
        System.out.print("Normal: ");
        smallDomain.forEach(n -> System.out.printf("%d --> %d; ", n, f1_int.apply(n)));
        System.out.println();
        System.out.print("Inverse: ");
        Function<Integer, Integer> f2_int = smallGroup.inverseOf(f1_int);
        smallDomain.forEach(n -> System.out.printf("%d --> %d; ", n, f2_int.apply(n)));
        System.out.println();
        System.out.print("Identity: ");
        Function<Integer, Integer> id_int = smallGroup.identity();
        smallDomain.forEach(n -> System.out.printf("%d --> %d; ", n, id_int.apply(n)));
        System.out.println("\n");

        // Test with a larger domain of strings
        Set<String> largeDomain = new HashSet<>(Arrays.asList("a", "b", "c", "d"));
        Set<Function<String, String>> largeResult = BijectionGroup.bijectionsOf(largeDomain);
        if (factorial(largeDomain.size()) == largeResult.size()) {
            System.out.println("bijectionsOf_Test_3_Size_Passed");
        } else {
            System.out.println("bijectionsOf_Test_3_Size_Failed");
        }
        System.out.println("bijectionsOf_Test_3_Display (Check Manually)");
        largeResult.forEach(aBijection -> {
            largeDomain.forEach(n -> System.out.printf("%s --> %s; ", n, aBijection.apply(n)));
            System.out.println();
        });
        System.out.println("Inverses_Identity_Function_Test_3_Display (Check Manually)");
        Group<Function<String, String>> largeGroup = BijectionGroup.bijectionGroup(largeDomain);
        Function<String, String> f1_str = BijectionGroup.bijectionsOf(largeDomain).stream().findFirst().get();
        System.out.print("Normal: ");
        largeDomain.forEach(n -> System.out.printf("%s --> %s; ", n, f1_str.apply(n)));
        System.out.println();
        System.out.print("Inverse: ");
        Function<String, String> f2_str = largeGroup.inverseOf(f1_str);
        largeDomain.forEach(n -> System.out.printf("%s --> %s; ", n, f2_str.apply(n)));
        System.out.println();
        System.out.print("Identity: ");
        Function<String, String> id_str = largeGroup.identity();
        largeDomain.forEach(n -> System.out.printf("%s --> %s; ", n, id_str.apply(n)));
        System.out.println();
    }

    private static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

}
