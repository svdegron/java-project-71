package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static hexlet.code.Formatter.ACTION;
import static hexlet.code.Formatter.FIRST_VALUE;
import static hexlet.code.Formatter.SECOND_VALUE;

public class Stylish {

    private static final int DIFFER = 2;
    private static final int MATCH = DIFFER + 2;

    public static String toStylish(Map<String, List<Object>> map) {
        // В зависимости от операционной системы
        // переносы строк могут быть разных видов:
        // LF - For a Unix/Linux/New Mac-based OS
        // CRLF - on a Windows-based OS
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
        var action = (PairState) list.get(ACTION);
        var firstValue = list.get(FIRST_VALUE);

        String result = switch (action) {
            case EXIST -> {
                yield " ".repeat(MATCH) + key + ": " + firstValue;
            }
            case DELETE -> {
                yield " ".repeat(DIFFER) + "- " + key + ": " + firstValue;
            }
            case ADD -> {
                yield " ".repeat(DIFFER) + "+ " + key + ": " + firstValue;
            }
            case EDIT -> {
                yield " ".repeat(DIFFER) + "- " + key + ": " + firstValue + System.lineSeparator()
                    + " ".repeat(DIFFER) + "+ " + key + ": " + list.get(SECOND_VALUE);
            }
        };

        return result;
    }
}
