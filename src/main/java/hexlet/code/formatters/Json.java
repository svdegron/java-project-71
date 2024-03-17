package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static java.util.Arrays.stream;

public class Json {

    public static String toJson(LinkedHashMap<String, List<Object>> map) throws JsonProcessingException {
        System.out.println(map);

        var hm = new HashMap<String, HashMap<String, Object>>();

        for (var entries : map.entrySet()) {
            var ar = entries.getValue();
            var key = entries.getKey();
            var item = (String) ar.get(0);
            var tempHM = new HashMap<String, Object>();

            Object content;

            if (ar.get(1).equals("null")) {
                content = null;
            } else if (ar.get(1).toString().charAt(0) == '{') {
                content = ar.get(1).toString().replace("=", ":");
            } else if (ar.get(1).toString().charAt(0) == '[') {
                var arr = ar.get(1).toString().split("[^a-zA-Z0-9]+");
                content = stream(arr)
                    .filter(el -> !"".equals(el))
                    .toArray();
            } else {
                content = ar.get(1);
            }

            if (hm.containsKey(item)) {
                tempHM = hm.get(item);
                tempHM.put(key, content);
            } else {
                tempHM.put(key, content);
            }

            hm.put(item, tempHM);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(hm);
    }
}
