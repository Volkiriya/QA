package project.calculate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import picocli.CommandLine;
import project.cli.SumCommand;
import project.exception.DivisionByZeroException;


class CalculateTest {

    Calculate calculate;

    @BeforeEach
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
        double result = Calculate.divideWithInfinity(a, b);
        if (Double.isInfinite(expected)) {
            assertEquals(expected, result);
        } else if (Double.isNaN(expected)) {
            assertTrue(Double.isNaN(result));
        } else {
            assertEquals(expected, result, 0.01);
        }
    }

    @Test
    void testSumCommand() {
        int exitCode = new CommandLine(new SumCommand()).execute("-a", "3", "-b", "5");
        assertEquals(0, exitCode);
    }

    @Test
    void divideByZeroShouldReturnInfinity() {
        double result = Calculate.divideWithInfinity(10, 0);
        assertEquals(Double.POSITIVE_INFINITY, result);
    }

    @Test
    void divideZeroByZeroShouldReturnNaN() {
        double result = Calculate.divideWithInfinity(0, 0);
        assertTrue(Double.isNaN(result));
    }

    @Test
    void divideNegativeByZeroShouldReturnNegativeInfinity() {
        double result = Calculate.divideWithInfinity(-10, 0);
        assertEquals(Double.NEGATIVE_INFINITY, result);
    }

    @Test
    void divideByNonZeroThrow() {
        assertThrows(DivisionByZeroException.class, () -> Calculate.divide(10, 0));
    }
}