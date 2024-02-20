package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.nio.file.Path;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "gendiff 0.1",
    description = "Compares two configuration files and show a difference."
)
public class App implements Runnable {

    @Option(names = { "-f", "--format" }, description = "output format [default: stylish]")
    private String format;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private Path filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private Path filepath2;

    @Override
    public void run() {
        System.out.println("Hello World!");
    }

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
}