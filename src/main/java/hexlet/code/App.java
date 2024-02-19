package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "gendiff 0.1",
    description = "Compares two configuration files and show a difference."
)
public class App implements Runnable {

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("Hello World!");
    }
}