package project.cli;

import picocli.CommandLine;
import project.calculate.Calculate;

@CommandLine.Command(name = "div", description = "Diving of two numbers")
public class DivCommand implements Runnable {
    @CommandLine.Option(names = {"-a"}, required = true) int a;
    @CommandLine.Option(names = {"-b"}, required = true) int b;

    public void run() {
        System.out.printf("%d / %d = %.2f%n", a, b, Calculate.divide(a,b));
    }
}
