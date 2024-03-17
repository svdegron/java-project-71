package hexlet.code;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Differ {

    private static List<String> addResult(String action, Object value) {
        return addResult(action, value, "");
    }

    private static List<String> addResult(String action, Object value1, Object value2) {
        var items = new ArrayList<String>();
        items.add(action);
        items.add(value1.toString());

        if (!"".equals(value2)) {
            items.add(value2.toString());
        }

        return items;
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        var fileMap1 = Parser.getMap(Paths.get(filepath1));
        var fileMap2 = Parser.getMap(Paths.get(filepath2));

        var results = new LinkedHashMap<String, List<String>>();

        for (var key : fileMap1.keySet()) {
            var entry1 = fileMap1.get(key);

            if (fileMap2.containsKey(key)) {
                var entry2 = fileMap2.get(key);

                if (entry1.equals(entry2)) {
                    results.put(key, addResult(Formatter.EQUAL, entry1));
                } else {
                    results.put(key, addResult(Formatter.EDIT, entry1, entry2));
                }

                fileMap2.remove(key);
            } else {
                results.put(key, addResult(Formatter.DELETE, entry1));
            }
        }

        for (var key : fileMap2.keySet()) {
            results.put(key, addResult(Formatter.ADD, fileMap2.get(key)));
        }

        if (results.isEmpty()) {
            return "Files are empty";
        }

        return Formatter.getResult(results, format);
    }

}
