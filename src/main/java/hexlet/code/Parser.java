package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map getMap(String content) throws JsonProcessingException {
        var objectMapper = new YAMLMapper();
        return objectMapper.readValue(content, Map.class);
    }
}
