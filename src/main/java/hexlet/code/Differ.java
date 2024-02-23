package hexlet.code;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
//??? import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Differ {

    public static String generate(Path filepath1, Path filepath2) throws IOException {
        System.out.println("filepath1 | " + filepath1);
        System.out.println("filepath2 | " + filepath2);
        System.out.println("exists | " + Files.exists(filepath1));
        var contentFile1 = Files.readString(filepath1);
        var contentFile2 = Files.readString(filepath2);

        Map<String, String> json1 = new HashMap<>();
        Map<String, String> json2 = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        json1 = objectMapper.readValue(contentFile1, new TypeReference<Map<String,String>>(){});
        System.out.println("json1 | " + json1);
        json2 = objectMapper.readValue(contentFile2, new TypeReference<Map<String,String>>(){});
        System.out.println("json2 | " + json2);

        return "Hello World! (from Differ)";
    }
}
