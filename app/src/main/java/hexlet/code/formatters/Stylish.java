package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stylish {
    private static final int DIFFER = 2;
    private static final int MATCH = DIFFER + 2;

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
            case EXIST -> " ".repeat(MATCH) + key + ": " + firstValue;
            case DELETE -> " ".repeat(DIFFER) + "- " + key + ": " + firstValue;
            case ADD -> " ".repeat(DIFFER) + "+ " + key + ": " + firstValue;
            case EDIT -> {
                yield " ".repeat(DIFFER) + "- " + key + ": " + firstValue + System.lineSeparator()
                    + " ".repeat(DIFFER) + "+ " + key + ": " + list.get(2);
            }
            default -> null;
//        switch (action) {
//            case EXIST:
//                return " ".repeat(MATCH) + key + ": " + firstValue;
//            case DELETE:
//                return " ".repeat(DIFFER) + "- " + key + ": " + firstValue;
//            case ADD:
//                return " ".repeat(DIFFER) + "+ " + key + ": " + firstValue;
//            case EDIT:
//                return " ".repeat(DIFFER) + "- " + key + ": " + firstValue + System.lineSeparator()
//                    + " ".repeat(DIFFER) + "+ " + key + ": " + list.get(2);
//            default:
//                return null;
        };
    }
}
