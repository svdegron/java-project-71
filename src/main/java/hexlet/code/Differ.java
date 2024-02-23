package hexlet.code;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
//??? import java.nio.file.Paths;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {
        System.out.println("String");
        System.out.println("filepath1 | " + filepath1);
        System.out.println("filepath2 | " + filepath2);
        System.out.println("to path");
        Path path1 = Paths.get(filepath1);
        Path path2 = Paths.get(filepath2);
        System.out.println("path1 | " + path1);
        System.out.println("path2 | " + path2);
        System.out.println("exists 1 | " + Files.exists(path1));
        System.out.println("exists 1 | " + Files.exists(path2));
        var contentFile1 = Files.readString(path1);
        var contentFile2 = Files.readString(path2);

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
