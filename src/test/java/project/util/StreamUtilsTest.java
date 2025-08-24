package project.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

class StreamUtilsTest {

    private static List<Integer> parse(String input) {
        if (input == null || input.isBlank()) {
            return List.of();
        }
        return Arrays.stream(input.trim().split("\\s*[;, ]\\s*"))
                .filter(s -> !s.isBlank())
                .map(Integer::parseInt)
                .toList();
    }

    @ParameterizedTest(name = "sum({0}) = {1}")
    @CsvSource({
            "'', 0",
            "'5', 5",
            "'-1,2,-3,4', 2"
    })
    void sum_csv(String input, int expected) {
        var list = parse(input);
        assertEquals(expected, StreamUtils.sum(list));
    }

    @Test
    void summNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> StreamUtils.sum(null));
    }

    @Test
    void sum_empty_isZero() {
        assertEquals(0, StreamUtils.sum(List.of()));
    }

    @ParameterizedTest(name = "average({0}) = {1}")
    @CsvSource({
            "'1,2,3', 2.0",
            "'5', 5.0",
            "'-1,1', 0.0"
    })
    void average(String input, double expected) {
        OptionalDouble average = StreamUtils.average(parse(input));
        assertTrue(average.isPresent(), "average must be present for non-empty list");
        assertEquals(expected, average.getAsDouble(), 1e-9);
    }

    @Test
    void averageListIsNull() throws IllegalAccessException {
        assertThrows(IllegalArgumentException.class, () -> StreamUtils.average(null));
    }

    @Test
    void averageListIsEmpty() {
        assertTrue(StreamUtils.average(List.of()).isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "'-1,2,-3,4','-1;2;-3;4'",   // оба формата дадут 4 числа
            "'1,  2,   3','1;2;3'",       // пробелы вокруг запятых/точек с запятой
    })
    void parse_formats_ok(String csv, String semi)
    {
        assertEquals(parse(semi), parse(csv));
        assertEquals(parse(semi), parse(semi));
    }

    @Test
    void parse_double_separators_ok() {
        assertEquals(List.of(1,2), parse("1,,2"));     // благодаря filter() не падаем
        assertEquals(List.of(1,2), parse("1; ;2"));
    }

}