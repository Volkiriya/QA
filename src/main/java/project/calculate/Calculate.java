package project.calculate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.exception.DivisionByZeroException;

public class Calculate {

    private static final Logger log = LoggerFactory.getLogger(Calculate.class);

    public static int sum(int a, int b) {
        log.debug("a=" + a + ", b=" + b);
        return a+b;
    }

    public static int sub(int a, int b) {
        log.debug("{} - {} = {}", a, b, a-b);
        return a-b;
    }

     public static int multiply(int a, int b) {
        return a*b;
     }

    public static double divideWithInfinity(int a, int b) {
        if (b == 0) {
            return a == 0 ? Double.NaN : (a > 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY);
        }
        return (double) a / b;
    }

    public static double divide(int a, int b) {
        if (b == 0) {
            log.error("Divide by zero");
            throw new DivisionByZeroException("Division by zero");
        }
        return (double) a / b;
    }
}