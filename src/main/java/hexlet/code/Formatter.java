package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Format;

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

        String result = null;
        switch (Format.valueOfLabel(format)) {
            case PLAIN:
                result = toPlain(map);
                break;
            case JSON:
                // продумать обработку исключения
                try {
                    result = toJson(map);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                break;
            case STYLISH:
                result = toStylish(map);
                break;
            default:
                result = null;
                break;
        }

        return result;
    }
}
