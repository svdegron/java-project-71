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

    @Option(names = { "-f", "--format" }, description = "output format [default: stylish]", defaultValue = "stylish")
    public String format;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @Override
    public Integer call() throws IOException {
        try {
            String diff;
            // если format всегда есть из App.java#L21 @Option(… defaultValue = "stylish")
            // то как применить перезагруженный метод? а он точно нужен?
            if ("stylish".equals(format)) {
                diff = Differ.generate(filepath1, filepath2);
            } else {
                diff = Differ.generate(filepath1, filepath2, format);
            }

            System.out.println(diff);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
