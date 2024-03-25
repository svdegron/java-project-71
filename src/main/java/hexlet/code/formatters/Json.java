package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.formatters.PairState.EDIT;

public class Json {

    public static String toJson(Map<String, List<Object>> map) throws JsonProcessingException {
        Map<String, TreeMap<String, Object>> result = new TreeMap<>();

        for (var entries : map.entrySet()) {
            var dataset = entries.getValue();
            var key = entries.getKey();
            var state = dataset.get(0).toString();
            var pairs = new TreeMap<String, Object>();

            Object value = dataset.get(1);

            if (result.containsKey(state)) {
                pairs = result.get(state);
            }

            if (state.equals(EDIT.toString())) {
                pairs.put(key + "_new", dataset.get(2));
                pairs.put(key + "_old", value);
            } else {
                pairs.put(key, value);
            }

            result.put(state, pairs);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
    }
}
