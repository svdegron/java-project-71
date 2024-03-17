package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static hexlet.code.Formatter.ACTION;
import static hexlet.code.Formatter.ADD;
import static hexlet.code.Formatter.EDIT;
import static hexlet.code.Formatter.EQUAL;
import static hexlet.code.Formatter.FIRST_VALUE;
import static hexlet.code.Formatter.SECOND_VALUE;

public class Plain {

    private static String convertValue(String value) {
        if (value.charAt(0) == '{' || value.charAt(0) == '[') {
            return "[complex value]";
        }

        return value;
    }

    public static String toPlain(Map<String, List<String>> map) {
        String result = map.keySet().stream()
            .sorted()
            .filter(key -> {
                var list = map.get(key);
                var action = list.get(ACTION);

                return !action.equals(EQUAL);
            })
            .map(key -> {
                var list = map.get(key);
                var action = list.get(ACTION);
                var firstValue = list.get(FIRST_VALUE);
                String secondValue = null;

                firstValue = convertValue((String) firstValue);

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

        return result;
    }
}
