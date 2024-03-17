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

        for (var entries : items.entrySet()) {
            var entryValue = entries.getValue();

            if (entryValue != null) {
                String simpleName = entryValue.getClass().getSimpleName();

                if ("ArrayList".equals(simpleName) || "LinkedHashMap".equals(simpleName)) {
                    var str = entryValue.toString();
                    convertItems.put(entries.getKey(), str);
                } else {
                    convertItems.put(entries.getKey(), entryValue);
                }
            } else {
                convertItems.put(entries.getKey(), "null");
            }
        }

        return convertItems;
    }
}
