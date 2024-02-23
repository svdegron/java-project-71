package hexlet.code;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
//import java.util.Set.;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {
        var flagLog = false;
        if (flagLog) System.out.println("String");
        if (flagLog) System.out.println("filepath1 | " + filepath1);
        if (flagLog) System.out.println("filepath2 | " + filepath2);
        if (flagLog) System.out.println("to path");
        Path path1 = Paths.get(filepath1);
        Path path2 = Paths.get(filepath2);
        if (flagLog) System.out.println("path1 | " + path1);
        if (flagLog) System.out.println("path2 | " + path2);
        if (flagLog) System.out.println("exists 1 | " + Files.exists(path1));
        if (flagLog) System.out.println("exists 1 | " + Files.exists(path2));
        var contentFile1 = Files.readString(path1);
        var contentFile2 = Files.readString(path2);

//        Map<String, Object> json1 = new HashMap<>();
//        Map<String, Object> json2 = new HashMap<>();
        var objectMapper = new ObjectMapper();
        var json1 = objectMapper.readValue(contentFile1, new TypeReference<Map<String, Object>>(){});
        if (flagLog) System.out.println("json1 | " + json1);
        var json2 = objectMapper.readValue(contentFile2, new TypeReference<Map<String, Object>>(){});
        if (flagLog) System.out.println("json2 | " + json2);

        if (flagLog) System.out.println("keySet1 | " + json1.keySet());
        if (flagLog) System.out.println("keySet2 | " + json2.keySet());

        List<String> existKeys = new LinkedList<>();
//        json1.keySet().stream()
//            .filter(json2::containsKey)
//            .forEach(existKeys::add);
//        System.out.println(">>> existKeys | " + existKeys);
//
//        json1.keySet().stream()
//            .filter(key -> !json2.containsKey(key))
//            .forEach(System.out::println);

        Set<String> allKeys = new TreeSet<>(json1.keySet());
        allKeys.addAll(json2.keySet());
        if (flagLog) System.out.println(allKeys);

        for (String key : allKeys) {
            if (json1.containsKey(key) && json1.containsKey(key)) {
                if (json1.get(key).equals(json2.get(key))) {
                    if (flagLog) System.out.println(key + " " + json1.get(key));
                    existKeys.add(key + " " + json1.get(key));
                } else {
                    if (json1.get(key) != null) {
                        if (flagLog) System.out.println("- " + key + " " + json1.get(key));
                        existKeys.add("- " + key + " " + json1.get(key));
                    }

                    if (json2.get(key) != null) {
                        if (flagLog) System.out.println("+ " + key + " " + json2.get(key));
                        existKeys.add("+ " + key + " " + json2.get(key));
                    }
                }
            } else {
                if (json1.get(key) != null) {
                    if (flagLog) System.out.println("- " + key + " " + json1.get(key));
                    existKeys.add("- " + key + " " + json1.get(key));
                }

                if (json2.get(key) != null) {
                    if (flagLog) System.out.println("+ " + key + " " + json2.get(key));
                    existKeys.add("+ " + key + " " + json2.get(key));
                }
            }
        }

        if (flagLog) System.out.println("---");

        return String.join("\n", existKeys);
    }
}
