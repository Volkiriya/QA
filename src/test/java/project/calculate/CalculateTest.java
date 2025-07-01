package project.calculate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class CalculateTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    void sum() {
        assertEquals(8, Calculate.sub(5, 3));
    }

    @org.junit.jupiter.api.Test
    void sub() {
        assertEquals(2, Calculate.sub(5, 3));
    }
}