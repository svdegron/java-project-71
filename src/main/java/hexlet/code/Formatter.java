package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.LinkedHashMap;
import java.util.List;

import static hexlet.code.formatters.Json.toJson;
import static hexlet.code.formatters.Plain.toPlain;
import static hexlet.code.formatters.Stylish.toStylish;

public class Formatter {
    public static final int ACTION = 0;
    public static final int FIRST_VALUE = 1;
    public static final int SECOND_VALUE = 2;

    public static String getResult(LinkedHashMap<String, List<Object>> map, String format) {
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
