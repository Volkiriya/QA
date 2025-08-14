package project.util;

import java.util.List;

public class StreamUtils {

    public static Integer sum(List<Integer> list) {
        if (list == null || list.isEmpty()) throw new IllegalArgumentException("list is null or empty");
        return list.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static double average(List<Integer> list) {
        return list.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
    }
}
