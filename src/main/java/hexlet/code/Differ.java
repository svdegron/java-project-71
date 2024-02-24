package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {
        Path path1 = Paths.get(filepath1);
        Path path2 = Paths.get(filepath2);

        if (!Files.exists(path1)) {
            throw new IOException("Check that the file \"" + path1 + "\" exists and can be accessed");
        }

        if (!Files.exists(path2)) {
            throw new IOException("Check that the file \"" + path2 + "\" exists and can be accessed");
        }

        var content1 = Files.readString(path1);
        var content2 = Files.readString(path2);

        var objectMapper = new ObjectMapper();
        var json1 = objectMapper.readValue(content1, new TypeReference<Map<String, Object>>(){});
        var json2 = objectMapper.readValue(content2, new TypeReference<Map<String, Object>>(){});

        var results = new LinkedList<String>();

        Set<String> allKeys = new TreeSet<>(json1.keySet());
        allKeys.addAll(json2.keySet());

        for (String key : allKeys) {
            if (json1.containsKey(key) && json1.containsKey(key)) {
                if (json1.get(key).equals(json2.get(key))) {
                    results.add("    " + key + " " + json1.get(key));
                } else {
                    if (json1.get(key) != null) {
                        results.add("  - " + key + " " + json1.get(key));
                    }

                    if (json2.get(key) != null) {
                        results.add("  + " + key + " " + json2.get(key));
                    }
                }
            } else {
                if (json1.get(key) != null) {
                    results.add("  - " + key + " " + json1.get(key));
                }

                if (json2.get(key) != null) {
                    results.add("  + " + key + " " + json2.get(key));
                }
            }
        }

        if (results.isEmpty()) {
            results.add("Files are empty");
        } else {
            results.addFirst("{");
            results.addLast("}");
        }

        return String.join("\n", results);
    }
}
