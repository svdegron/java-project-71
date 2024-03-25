package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

import static hexlet.code.formatters.DifferMap.toDifferMap;
import static hexlet.code.formatters.Json.toJson;
import static hexlet.code.formatters.Plain.toPlain;
import static hexlet.code.formatters.Stylish.toStylish;

public class Formatter {
    public static final int ACTION = 0;
    public static final int FIRST_VALUE = 1;
    public static final int SECOND_VALUE = 2;

    public static Map<String, List<Object>> getDifferMap(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        return toDifferMap(firstMap, secondMap);
    }

    public static String getResult(Map<String, List<Object>> map, String format) {
        String result = switch (format) {
            case "plain" -> toPlain(map);
            case "json" -> {
                // продумать обработку исключения
                try {
                    yield toJson(map);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            case "stylish" -> toStylish(map);
            default -> "";
        };

        return result;
    }
}
