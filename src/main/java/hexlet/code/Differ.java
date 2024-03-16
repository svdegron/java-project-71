package hexlet.code;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    private static final int DIFFER_COUNT = 2;
    private static final int MATCH_COUNT = DIFFER_COUNT + 2;

    public static String generate(String filepath1, String filepath2) throws IOException {
        var fileMap1 = Parser.getMap(Paths.get(filepath1));
        var fileMap2 = Parser.getMap(Paths.get(filepath2));

        var results = new LinkedList<String>();

        Set<String> allKeys = new TreeSet<>(fileMap1.keySet());
        allKeys.addAll(fileMap2.keySet());

        for (String key : allKeys) {
            var entryValue1 = fileMap1.get(key);
            var entryValue2 = fileMap2.get(key);

            if (fileMap1.containsKey(key) && fileMap1.containsKey(key)) {
                if (entryValue1.equals(entryValue2)) {
                    results.add(" ".repeat(MATCH_COUNT) + key + ": " + entryValue1);
                } else {
                    if (entryValue1 != null) {
                        results.add(" ".repeat(DIFFER_COUNT) + "- " + key + ": " + entryValue1);
                    }

                    if (entryValue2 != null) {
                        results.add(" ".repeat(DIFFER_COUNT) + "+ " + key + ": " + entryValue2);
                    }
                }
            } else {
                if (entryValue1 != null) {
                    results.add(" ".repeat(DIFFER_COUNT) + "- " + key + ": " + entryValue1);
                }

                if (entryValue2 != null) {
                    results.add(" ".repeat(DIFFER_COUNT) + "+ " + key + ": " + entryValue2);
                }
            }
        }

        if (results.isEmpty()) {
            results.add("Files are empty");
        } else {
            results.addFirst("{");
            results.addLast("}");
        }

        // В зависимости от операционной системы
        // переносы строк можут быть разных видов:
        // LF - For a Unix/Linux/New Mac-based OS
        // CRLF - on a Windows-based OS
        return String.join(System.lineSeparator(), results);
    }
}
