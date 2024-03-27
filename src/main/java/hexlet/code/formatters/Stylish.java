package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import static hexlet.code.Formatter.ACTION;
//import static hexlet.code.Formatter.FIRST_VALUE;
//import static hexlet.code.Formatter.SECOND_VALUE;

public class Stylish {
//    private static final int DIFFER = 2;
//    private static final int MATCH = DIFFER + 2;

    public static String toStylish(Map<String, List<Object>> map) {
        // В зависимости от операционной системы
        // переносы строк могут быть разных видов:
        // LF - For a Unix/Linux/New Mac-based OS
        // CRLF - on a Windows-based OS
        if (map == null) {
            return "{}";
        }

        return map.keySet().stream()
            .sorted()
            .map(key -> {
                var list = map.get(key);
                return getString(key, list);
            })
            .collect(Collectors.joining(System.lineSeparator(), "{" + System.lineSeparator(),
                System.lineSeparator() + "}"));
    }

    private static String getString(String key, List<Object> list) {
        var action = (PairState) list.get(0);
        var firstValue = list.get(1);

        return switch (action) {
            case EXIST -> " ".repeat(4) + key + ": " + firstValue;
            case DELETE -> " ".repeat(2) + "- " + key + ": " + firstValue;
            case ADD -> " ".repeat(2) + "+ " + key + ": " + firstValue;
            case EDIT -> " ".repeat(2) + "- " + key + ": " + firstValue + System.lineSeparator()
                + " ".repeat(2) + "+ " + key + ": " + list.get(2);
            default -> null;
        };
    }
}
