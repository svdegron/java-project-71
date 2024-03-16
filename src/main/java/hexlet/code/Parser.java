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

        for (Object key : contentMap.keySet()) {
            var getKey = contentMap.get(key);

            if (contentMap.get(key) != null) {
                String cls = contentMap.get(key).getClass().toString();

                if (cls.indexOf("ArrayList") > 0 || cls.indexOf("LinkedHashMap") > 0) {
                    var str = contentMap.get(key).toString();
                    contentMap.replace(key, str);
                }
            } else {
                contentMap.replace(key, "null");
            }
        }

        return contentMap;
    }
}
