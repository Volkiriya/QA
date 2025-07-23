package project.cli;


import picocli.CommandLine;

@CommandLine.Command(name = "calc", subcommands = {SumCommand.class, DivCommand.class})
public class MainEntry implements Runnable {
    public void run() {
        System.out.println("Выберите команду: sum или div");
    }

    public static void main(String[] args) {
        new CommandLine(new MainEntry()).execute(args);
    }
}