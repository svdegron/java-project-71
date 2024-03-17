package hexlet.code;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> getMap(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new IOException("Check that the file \"" + path + "\" exists and can be accessed");
        }

        var content = Files.readString(path);

        var objectMapper = new YAMLMapper();

        var contentMap = objectMapper.readValue(content, Map.class);

        return convertObjects(contentMap);
    }

    public static Map<String, Object> convertObjects(Map<String, Object> items) {
        var convertItems = new HashMap<String, Object>();

        for (String key : items.keySet()) {
            var entryValue = items.get(key);

            if (entryValue != null) {
                String cls = entryValue.getClass().toString();

                if (cls.indexOf("ArrayList") > 0 || cls.indexOf("LinkedHashMap") > 0) {
                    var str = entryValue.toString();
                    convertItems.put(key, str);
                } else {
                    convertItems.put(key, entryValue);
                }
            } else {
                convertItems.put(key, "null");
            }
        }

        return convertItems;
    }
}
