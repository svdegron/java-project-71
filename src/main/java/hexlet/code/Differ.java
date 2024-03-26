package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static hexlet.code.Formatter.getResult;
import static hexlet.code.Parser.getMap;
import static hexlet.code.Formatter.getDifferMap;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        if (format == null) {
            format = "stylish";
        }

        Map<String, List<Object>> map = generate(filepath1, filepath2);

        return getResult(map, format);
    }

    public static Map<String, List<Object>> generate(String filepath1, String filepath2) throws IOException {
        var firstContent = getFileContent(Paths.get(filepath1));
        var secondContent = getFileContent(Paths.get(filepath2));

        Map<String, Object> firstMap = getMap(firstContent);
        Map<String, Object> secondMap = getMap(secondContent);

        return getDifferMap(firstMap, secondMap);
    }

    public static String getFileContent(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new IOException("Check 1 that the file \"" + path + "\" exists and can be accessed");
        }

        return Files.readString(path);
    }
}
