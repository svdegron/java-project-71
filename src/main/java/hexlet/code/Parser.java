package hexlet.code;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static hexlet.code.Formatter.convertObjects;

public class Parser {
    public static Map<String, Object> getMap(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new IOException("Check that the file \"" + path + "\" exists and can be accessed");
        }

        var content = Files.readString(path);

        var objectMapper = new YAMLMapper();

        var contentMap = objectMapper.readValue(content, Map.class);

        Map resultMap;

        switch (App.format) {
            case "stylish": resultMap = convertObjects(contentMap);
            default: resultMap = contentMap;
        }

        return resultMap;
    }
}
