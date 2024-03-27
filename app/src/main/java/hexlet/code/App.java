package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "gendiff 0.1",
    description = "Compares two configuration files and show a difference."
)
public class App implements Callable<Integer> {

    @Option(names = { "-f", "--format" }, description = "output format [default: stylish]")
    private String format;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @Override
    /**
     * Calculate the square of a rectangle.
     * @return integer number
     */
    public Integer call() {
        String diff;

        try {
            diff = Differ.generate(filepath1, filepath2, format);
        } catch (IOException e) {
            diff = e.getMessage();
        }

        System.out.println(diff);

        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
