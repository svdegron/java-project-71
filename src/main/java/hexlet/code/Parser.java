package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> getMap(String content) throws JsonProcessingException {
        ObjectMapper mapper = new YAMLMapper();
        @SuppressWarnings("unchecked")
        Map<String, Object> result = mapper.readValue(content, Map.class);

        return result;
    }
}
