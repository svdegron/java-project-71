package hexlet.code;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static hexlet.code.Formatter.addItem;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        var fileMap1 = Parser.getMap(Paths.get(filepath1));
        var fileMap2 = Parser.getMap(Paths.get(filepath2));

        var results = new TreeMap<String, String>();

        for (var key : fileMap1.keySet()) {
            var entry1 = fileMap1.get(key);
            String result;

            if (fileMap2.containsKey(key)) {
                var entry2 = fileMap2.get(key);

                if (entry1.equals(entry2)) {
                    result = addItem(format, Formatter.EQUAL, key, entry1);
                    results.put(key, result);
                } else {
                    result = addItem(format, Formatter.EDIT, key, entry1, entry2);
                    results.put(key, result);
                }

                fileMap2.remove(key);
            } else {
                result = addItem(format, Formatter.DELETE, key, entry1);
                results.put(key, result);
            }
        }

        for (var key : fileMap2.keySet()) {
            var result = addItem(format, Formatter.ADD, key, fileMap2.get(key));
            results.put(key, result);
        }

        if (results.isEmpty()) {
            return "Files are empty";
        }

        var result = results.entrySet().stream()
            .map(Map.Entry::getValue)
            .filter(line -> line.length() > 0)
            .collect(Collectors.joining(System.lineSeparator()));

        // В зависимости от операционной системы
        // переносы строк можут быть разных видов:
        // LF - For a Unix/Linux/New Mac-based OS
        // CRLF - on a Windows-based OS
        return String.join(System.lineSeparator(), "{", result, "}");
    }

}
