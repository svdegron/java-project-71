package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "gendiff",
    version = "gendiff 0.1",
    description = "Compares two configuration files and show a difference."
)
public class App implements Runnable {

    @Option(names = { "-V", "--version"}, versionHelp = true)
    private boolean versionInfoRequested;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "Show this message and exit.")
    private boolean usageHelpRequested = false;

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("Hello World!");
    }
}