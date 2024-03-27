package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static hexlet.code.formatters.PairState.ADD;
import static hexlet.code.formatters.PairState.DELETE;
import static hexlet.code.formatters.PairState.EDIT;
import static hexlet.code.formatters.PairState.EXIST;

public class DifferMap {
    public static Map<String, List<Object>> toDifferMap(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Map<String, List<Object>> results = new LinkedHashMap<>();

        for (var key : firstMap.keySet()) {
            var val1 = firstMap.get(key);

            if (secondMap.containsKey(key)) {
                var val2 = secondMap.get(key);

                if ((val1 != null && val1.equals(val2)) || (val1 == null && val2 == null)) {
                    results.put(key, addResult(EXIST, val1));
                } else {
                    results.put(key, addResult(EDIT, val1, val2));
                }

                secondMap.remove(key);
            } else {
                results.put(key, addResult(DELETE, val1));
            }
        }

        for (var key : secondMap.keySet()) {
            results.put(key, addResult(ADD, secondMap.get(key)));
        }

        return results;
    }

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
}
