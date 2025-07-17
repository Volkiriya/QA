package project.cli;

import picocli.CommandLine;
import project.calculate.Calculate;

@CommandLine.Command(name = "sum", description = "Addition two numbers")
public class SumCommand implements Runnable {
    @CommandLine.Option(names = {"-a"}, required = true)
    int a;
    @CommandLine.Option(names = {"-b"}, required = true)
    int b;

    public void run() {
        System.out.printf("%d + %d = %d%n", a, b, Calculate.sum(a,b));
    }
}
