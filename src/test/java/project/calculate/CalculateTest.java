package project.calculate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class CalculateTest {

    Calculate calculate;

    @Test
    void setUp() {
        calculate = new Calculate();
    }

    @ParameterizedTest
    @CsvSource({"2,2,4", "0,5,5", "-3, 10, 7"})
    void adds(int a, int b, int expected) {
        assertEquals(expected, calculate.sum(a, b));
    }

    @DisplayName("Sum")
    @Test
    void sum() {
        assertEquals(8, Calculate.sum(5, 3));
    }

    @DisplayName("Sub")
    @Test
    void sub() {
        assertEquals(2, Calculate.sub(5, 3));
    }

    @DisplayName("Multiply")
    @ParameterizedTest
    @CsvSource({"1, -1,-1",
            "10, 2,20",
            "-8,4,-32",
            "4,-2, -8"})
    void multiply(int a, int b, int expected) {
        assertEquals(expected, Calculate.multiply(a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "10, 2, 5.0",
            "-9, 3, -3.0",
            "0, 5, 0.0",
            "10, 0, Infinity",
            "-10, 0, -Infinity",
            "0, 0, NaN"
    })
    void divideTest(int a, int b, double expected) {
        double result = Calculate.divide(a, b);
        if (Double.isNaN(expected)) {
            assertTrue(Double.isNaN(result));
        } else {
            assertEquals(expected, result, 0.01);
        }
    }

}