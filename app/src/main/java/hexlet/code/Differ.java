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

    public static String generate(String filepath1, String filepath2) throws IOException {
        var firstContent = getFileContent(Paths.get(filepath1));
        var secondContent = getFileContent(Paths.get(filepath2));

        Map<String, Object> firstMap;
        Map<String, Object> secondMap;

        try {
            firstMap = getMap(firstContent);
            secondMap = getMap(secondContent);
        } catch (Exception e) {
            return null;
        }

        Map<String, List<Object>> map = getDifferMap(firstMap, secondMap);

        return getResult(map, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        if (format == null) {
            format = "stylish";
        }

        var firstContent = getFileContent(Paths.get(filepath1));
        var secondContent = getFileContent(Paths.get(filepath2));

        Map<String, Object> firstMap;
        Map<String, Object> secondMap;

        try {
            firstMap = getMap(firstContent);
            secondMap = getMap(secondContent);
        } catch (Exception e) {
            return null;
        }

        Map<String, List<Object>> map = getDifferMap(firstMap, secondMap);

        return getResult(map, format);
    }

    public static String getFileContent(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new IOException("Check that the files exists and can be accessed");
        }

        return Files.readString(path);
    }
}
