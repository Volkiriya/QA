package project.util;

import java.util.List;
import java.util.OptionalDouble;

public class StreamUtils {

    public static Integer sum(List<Integer> list) {
        if (list == null) throw new IllegalArgumentException("list is null");
        return list.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static OptionalDouble average(List<Integer> list) {
        if (list == null) throw new IllegalArgumentException("list is null");
        if (list.isEmpty()) return OptionalDouble.empty();
        return list.stream()
                .mapToInt(Integer::intValue)
                .average();
    }
}