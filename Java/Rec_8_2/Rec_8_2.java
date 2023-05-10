package Java.Rec_8_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Q3 {
    public static String shortestString(List<String> input) {
        return input.stream().reduce((a, b) -> (a.length() < b.length() ? a : b)).orElse("");
    }
}

class Q4 {
    public static List<Integer> exponentIntegerOfTwo(List<Integer> input) {
        return input.stream().filter(e -> (e & e - 1) == 0).collect(Collectors.toList());
    }
}

class Q5 {
    public static Set<String> getWords(String path) {
        Stream<String> stream = null;
        try {
            stream = Files.lines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream.flatMap(line -> Stream.of(line.split(" "))).collect(Collectors.toSet());
    }
}

class Q6 {
    public static Map<String, Long> getWordsOccurances(String path) {
        try {
            return Files.lines(Paths.get(path)).flatMap(line -> Arrays.stream(line.split(" ")))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }
}

// print out that word and its occurance
class Q7 {

}

public class Rec_8_2 {

}
