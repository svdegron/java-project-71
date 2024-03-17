package hexlet.code;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Differ {

    private static List<Object> addResult(Object action, Object value) {
        return addResult(action, value, "");
    }

    private static List<Object> addResult(Object action, Object value1, Object value2) {
        var items = new ArrayList<>();
        items.add(action);
        items.add(value1);

        if (!"".equals(value2)) {
            items.add(value2);
        }

        return items;
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        var firstMap = Parser.getMap(Paths.get(filepath1));
        var secondMap = Parser.getMap(Paths.get(filepath2));

        var results = new LinkedHashMap<String, List<Object>>();

        for (var key : firstMap.keySet()) {
            var entry1 = firstMap.get(key);

            if (secondMap.containsKey(key)) {
                var entry2 = secondMap.get(key);

                if (entry1.equals(entry2)) {
                    results.put(key, addResult(Formatter.EXIST, entry1));
                } else {
                    results.put(key, addResult(Formatter.EDIT, entry1, entry2));
                }

                secondMap.remove(key);
            } else {
                results.put(key, addResult(Formatter.DELETE, entry1));
            }
        }

        for (var key : secondMap.keySet()) {
            results.put(key, addResult(Formatter.ADD, secondMap.get(key)));
        }

        if (results.isEmpty()) {
            return "Files are empty";
        }

        return Formatter.getResult(results, format);
    }

}
