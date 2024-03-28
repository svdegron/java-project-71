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

    public static String editString(String key, Object firstValue, Object secondValue) {
        return " ".repeat(DIFFER) + "- " + key + ": " + firstValue + System.lineSeparator()
            + " ".repeat(DIFFER) + "+ " + key + ": " + secondValue;
    }

    private static String getString(String key, List<Object> list) {
        var action = list.get(0).toString();
        var firstValue = list.get(1);

//        System.out.println(action);
//        System.out.println(action.getClass().getSimpleName());

        return switch (action) {
            case "exist" -> (" ".repeat(MATCH) + key + ": " + firstValue);
            case "delete" -> (" ".repeat(DIFFER) + "- " + key + ": " + firstValue);
            case "add" -> (" ".repeat(DIFFER) + "+ " + key + ": " + firstValue);
            case "edit" -> editString(key, firstValue, list.get(2));
            default -> throw new RuntimeException("Unknown option action");
        };
    }
}
