package project.calculate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class CalculateTest {

    Calculate calculate;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        calculate = new Calculate();
    }

    @ParameterizedTest
    @CsvSource({"2,2,4", "0,5,5"})
    void adds(int a, int b, int expected) {
        assertEquals(expected, calculate.sum(a, b));
    }

    @DisplayName("Sum")
    void sum() {
        assertEquals(8, Calculate.sum(5, 3));
    }

    @DisplayName("Sub")
    void sub() {
        assertEquals(2, Calculate.sub(5, 3));
    }
}