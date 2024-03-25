package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static hexlet.code.Formatter.getResult;
import static hexlet.code.Parser.getMap;
import static hexlet.code.Formatter.getDifferMap;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException,
        JsonProcessingException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException,
        JsonProcessingException {
        var firstContent = getFileContent(Paths.get(filepath1));
        var secondContent = getFileContent(Paths.get(filepath2));

        Map<String, Object> firstMap;
        Map<String, Object> secondMap;

        try {
            firstMap = getMap(firstContent);
            secondMap = getMap(secondContent);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }

        var results = getDifferMap(firstMap, secondMap);

        // Чтобы не забыть про "но форматировать его нужно по всем правилам"
        // https://github.com/svdegron/java-project-71/blob/b86258561c4a280d4fc0cc7ed800ce93f0287058
        // /src/main/java/hexlet/code/Differ.java#L56 Такого не нужно.
        // Если файлы пустые, то и диф для них будет пустой, но форматировать его нужно по всем правилам

        return getResult(results, format);
    }

    public static String getFileContent(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new IOException("Check that the file \"" + path + "\" exists and can be accessed");
        }

        return Files.readString(path);
    }
}
