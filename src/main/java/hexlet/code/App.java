package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

//import static com.fasterxml.jackson.databind.util.ClassUtil.defaultValue;

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

//    public abstract String defaultValue

    @Override
    public Integer call() throws IOException {
        try {
//            System.out.println(defaultValue());
            String diff;
            // ? format всегда есть из-за @Option(… defaultValue = "stylish")
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

        System.exit(0);
    }
}
