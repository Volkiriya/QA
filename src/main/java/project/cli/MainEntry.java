package project.cli;

import picocli.CommandLine;

@CommandLine.Command(name = "calc", subcommands = {SumCommand.class, DivCommand.class})
public class MainEntry implements Runnable {
    public void run() {
        System.out.println("Use commands: sum or div");
    }
}