package project.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

class StreamUtilsTest {

    @Test
    void sum() {
        assertEquals(6, StreamUtils.sum(List.of(1, 5)));
    }

    @Test
    void average() {
        assertEquals(2.0, StreamUtils.average(List.of(1,2,3)), String.valueOf(0.001));
    }

    @Test
    void averageEmptyIsZero() {
        assertEquals(0.0, StreamUtils.average(List.of()));
    }
}