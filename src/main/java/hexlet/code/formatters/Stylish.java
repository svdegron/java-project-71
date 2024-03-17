package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static hexlet.code.Formatter.ACTION;
import static hexlet.code.Formatter.ADD;
import static hexlet.code.Formatter.DELETE;
import static hexlet.code.Formatter.EDIT;
import static hexlet.code.Formatter.FIRST_VALUE;
import static hexlet.code.Formatter.SECOND_VALUE;

public class Stylish {

    private static final int DIFFER_COUNT = 2;
    private static final int MATCH_COUNT = DIFFER_COUNT + 2;

    public static String toStylish(Map<String, List<String>> map) {
        // В зависимости от операционной системы
        // переносы строк можут быть разных видов:
        // LF - For a Unix/Linux/New Mac-based OS
        // CRLF - on a Windows-based OS

        String result = map.keySet().stream()
            .sorted()
            .map(key -> {
                var list = map.get(key);
                var action = list.get(ACTION);
                var firstValue = list.get(FIRST_VALUE);

                switch (action) {
                    case DELETE -> {
                        return  " ".repeat(DIFFER_COUNT) + "- " + key + ": " + firstValue;
                    }
                    case ADD -> {
                        return  " ".repeat(DIFFER_COUNT) + "+ " + key + ": " + firstValue;
                    }
                    case EDIT -> {
                        return  " ".repeat(DIFFER_COUNT) + "- " + key + ": " + firstValue + System.lineSeparator()
                            + " ".repeat(DIFFER_COUNT) + "+ " + key + ": " + list.get(SECOND_VALUE);
                    }
                    default -> {
                        return " ".repeat(MATCH_COUNT) + key + ": " + firstValue;
                    }
                }
            })
            .collect(Collectors.joining(System.lineSeparator(), "{" + System.lineSeparator(),
                System.lineSeparator() + "}"));

        return result;
    }
}
