package hexlet.code.formatters;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static hexlet.code.Formatter.ACTION;
import static hexlet.code.Formatter.FIRST_VALUE;
import static hexlet.code.Formatter.SECOND_VALUE;
import static hexlet.code.formatters.PairState.ADD;
import static hexlet.code.formatters.PairState.EDIT;
import static hexlet.code.formatters.PairState.EXIST;

public class Plain {

    private static Object convertValue(Object value) {
        var simpleName = value.getClass().getSimpleName();

        if ("String".equals(simpleName) && !"null".equals(value)) {
            if (value.toString().charAt(0) == '{' || value.toString().charAt(0) == '[') {
                return "[complex value]";
            } else {
                return "'" + value + "'";
            }
        }

        return value;
    }

    public static String toPlain(LinkedHashMap<String, List<Object>> map) {
        return map.keySet().stream()
            .sorted()
            .filter(key -> {
                var list = map.get(key);
                var action = list.get(ACTION);

                return !action.equals(EXIST);
            })
            .map(key -> {
                var list = map.get(key);
                var action = list.get(ACTION);
                var firstValue = list.get(FIRST_VALUE);
                Object secondValue = null;

                firstValue = convertValue(firstValue);

                if (list.size() > SECOND_VALUE) {
                    secondValue = convertValue(list.get(SECOND_VALUE));
                }

                if (action.equals(ADD)) {
                    return "Property '" + key + "' was added with value: " + firstValue;
                } else if (action.equals(EDIT)) {
                    return "Property '" + key + "' was updated. From " + firstValue + " to " + secondValue;
                } else {
                    return  "Property '" + key + "' was removed";
                }
            })
            .collect(Collectors.joining(System.lineSeparator()));
    }
}
