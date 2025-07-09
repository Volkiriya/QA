package project.calculate;

import java.text.DecimalFormat;

public class Calculate {

    public static int sum(int a, int b) {
        return a+b;
    }

    public static int sub(int a, int b) {
        return a-b;
    }

     public static int multiply(int a, int b) {
        return a*b;
     }

    public static Double divide(int a, int b) {
        if (b == 0) {
            return a == 0 ? Double.NaN : (a > 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY);
        }
        return (double) a / b;
    }
}