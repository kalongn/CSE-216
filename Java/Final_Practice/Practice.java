package Java.Final_Practice;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Practice {

    // Stream API Practice Below

    /**
     * Given a list of integers, find the sum of all even numbers in the list.
     * 
     * @param input
     *              input Integer List
     * @return
     *         the sum of all even number within the list.
     */
    public static int sumOfEven(List<Integer> input) {
        return input.stream().filter(x -> x % 2 == 0).reduce(0, (a, b) -> a + b);
        // chat-GPT answer:
        // input.stream().filter(n -> n % 2 == 0).reduce(0, Integer::sum);
    }

    public static void testSumOfEven() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(sumOfEven(integers));
    }

    /**
     * Given a list of strings, find the shortest string in the list.
     * 
     * @param input
     *              input String List
     * @return
     *         the shortest string (left priority) within the list.
     */
    public static String shortestString(List<String> input) {
        return input.stream().reduce((a, b) -> a.length() - b.length() > 0 ? b : a).orElse("");
        // chat-GPT answer:
        // strings.stream().min(Comparator.comparingInt(String::length)).orElse(null);
    }

    public static void testShortestString() {
        List<String> strings = Arrays.asList("apple", "banana", "pear", "kiwi", "orange");
        System.out.println(shortestString(strings));
    }

    /**
     * Given a list of integers, remove all duplicates and return the unique
     * elements in the list.
     * 
     * @param input
     * @return
     *         A list of integer that is distinct
     */
    public static <T> List<T> distinctInteger(List<T> input) {
        return input.stream().distinct().collect(Collectors.toList());
    }

    public static void testDistinctInteger() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 3, 5, 6, 5, 7, 8, 9, 8);
        distinctInteger(numbers).forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    /**
     * Given a list of strings, find the longest string that contains vowels.
     * 
     * @param input
     *              input String List
     * @return
     */
    public static String longestVowelString(List<String> input) {
        List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');
        return input.stream().filter(x -> {
            return vowels.stream().anyMatch(y -> x.indexOf(y) != -1);
        }).max(Comparator.comparingInt(String::length))
                .orElse(null);
    }

    public static void testLongestVowelString() {
        List<String> strings = Arrays.asList("apple", "banana", "aeiou", "kiwi", "orange", "a");
        System.out.println(longestVowelString(strings));
    }

    /**
     * This method takes a List of T elements as input and returns a List of
     * T that represent the most frequently occurring elements in the input
     * original List. If there are multiple elements with the same frequency, the
     * method should return all of them in ascending order.
     * 
     * @param nums
     *             input list of type T
     * @return
     *         a list of T where if they have the same frequence sorted in
     *         asccending order.
     */
    public static <T> List<T> getMostFrequentElements(List<T> input) {
        Map<T, Long> map = input.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long maxFrequent = Collections.max(map.values());
        return map.entrySet().stream().filter(e -> e.getValue() == maxFrequent).map(e -> e.getKey()).sorted()
                .collect(Collectors.toList());
    }

    public static void testGetMostFrequentElements() {
        List<Integer> nums = Arrays.asList(1, 5, 2, 8, 3, 5, 5, 2);
        List<Integer> result = getMostFrequentElements(nums);
        result.forEach(x -> System.out.print(x + " "));
        nums = Arrays.asList(1, 5, 2, 8, 3, 7);
        result = getMostFrequentElements(nums);
        System.out.println();
        result.forEach(x -> System.out.print(x + " "));
    }

    /**
     * Given List of Strings as input and returns a new List containing only the
     * Strings
     * that are palindrome.
     * 
     * @param input
     *              input list.
     * @return
     *         a new List containing only the Strings that are palindrome.
     */
    public static List<String> onlyPallindrome(List<String> input) {
        return input.stream().filter(x -> x.equals(new StringBuilder(x).reverse().toString()))
                .collect(Collectors.toList());
    }

    public static void testOnlyPallindrome() {
        List<String> strings = Arrays.asList("abba", "lol", "Banerjee", "Test", "T", "Lx.xx.xL");
        List<String> result = onlyPallindrome(strings);
        result.forEach(x -> System.out.print(x + " "));
    }

    /**
     * Given a list of integers, prints out key-value pairs where the key is a
     * unique
     * element in the list and its value is the number of occurrences of that
     * integer in the list. i.e.
     * {1,2,2,3,4,5,5,5,6} -> 1: 1, 2: 2, 3: 1,
     * 4: 1, 5: 3, 6: 1
     * 
     */
    public static void printFrequency(List<Integer> input) {
        Map<Integer, Long> result = input.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        result.entrySet().forEach(x -> System.out.print(x.getKey() + " -> " + x.getValue() + " "));
    }

    public static void testPrintFrequency() {
        System.out.println();
        List<Integer> nums = Arrays.asList(1,2,2,3,4,5,5,5,6);
        printFrequency(nums);
    }

    // Thunk Example Practice
    public static <T> Supplier<T> printLater(T input) {
        return new Supplier<T>() {

            @Override
            public T get() {
                System.out.println(input.toString());
                return input;
            }
        };
    }

    public static <T> void checkInput(T input) {
        if (input.equals("0")) {
            printLater(input).get();
        } else {
            System.out.println("Lz eval examples");
        }
    }

    public static <T> void testThunk() {
        checkInput("1");
        checkInput("0");
    }

    public static void main(String[] args) {

        // Stream API Practice
        testSumOfEven(); // should print 30
        testShortestString(); // should print "pear"
        testDistinctInteger(); // should print 1 2 3 4 5 6 7 8 9
        testLongestVowelString(); // should print banana
        testGetMostFrequentElements(); // should print 5 \n 1 2 3 5 7 8
        testOnlyPallindrome(); // should print “abba”, “lol”, “T”, “Lx.xx.xL”
        testPrintFrequency();
        System.out.println();
        // Thunk Example
        testThunk(); // should print Lz eval examples \n 0
    }
}
