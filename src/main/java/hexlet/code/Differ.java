package hexlet.code;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {
        var fileMap1 = Parser.getMap(Paths.get(filepath1));
        var fileMap2 = Parser.getMap(Paths.get(filepath2));

        var results = new LinkedList<String>();

        Set<String> allKeys = new TreeSet<>(fileMap1.keySet());
        allKeys.addAll(fileMap2.keySet());

        for (String key : allKeys) {
            if (fileMap1.containsKey(key) && fileMap1.containsKey(key)) {
                if (fileMap1.get(key).equals(fileMap2.get(key))) {
                    results.add("    " + key + ": " + fileMap1.get(key));
                } else {
                    if (fileMap1.get(key) != null) {
                        results.add("  - " + key + ": " + fileMap1.get(key));
                    }

                    if (fileMap2.get(key) != null) {
                        results.add("  + " + key + ": " + fileMap2.get(key));
                    }
                }
            } else {
                if (fileMap1.get(key) != null) {
                    results.add("  - " + key + ": " + fileMap1.get(key));
                }

                if (fileMap2.get(key) != null) {
                    results.add("  + " + key + ": " + fileMap2.get(key));
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
