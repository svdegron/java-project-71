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
                var first_value = list.get(FIRST_VALUE);
                String second_value = null;

                first_value = convertValue((String) first_value);

                if (list.size() > SECOND_VALUE) {
                    second_value = convertValue(list.get(SECOND_VALUE));
                }

                switch (action) {
                    case ADD -> {
                        return "Property '" + key + "' was added with value: " + first_value;
                    }
                    case EDIT -> {
                        return "Property '" + key + "' was updated. From " + first_value + " to " + second_value;
                    }
                    default -> {
                        return  "Property '" + key + "' was removed";
                    }
                }
            })
            .collect(Collectors.joining(System.lineSeparator()));

        return result;
    }
}
