package Java.Rec_8;

import java.util.*;

public class Q2 {
    public static int sum(Collection<Integer> collection) {
        return collection.stream().reduce(0, (a,b) -> a + b);
    }
}
