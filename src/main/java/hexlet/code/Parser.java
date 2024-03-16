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
            var entryValue = contentMap.get(key);

            if (entryValue != null) {
                String cls = entryValue.getClass().toString();

                if (cls.indexOf("ArrayList") > 0 || cls.indexOf("LinkedHashMap") > 0) {
                    var str = entryValue.toString();
                    contentMap.replace(key, str);
                }
            } else {
                contentMap.replace(key, "null");
            }
        }

        return contentMap;
    }
}
