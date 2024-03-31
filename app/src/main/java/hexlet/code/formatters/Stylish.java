package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stylish {
    private static final int DIFFER = 2;
    private static final int MATCH = DIFFER + 2;

    public static String toStylish(Map<String, List<Object>> map) {
        // В зависимости от операционной системы
        // переносы строк могут быть разных видов:
        // LF - For a Unix/Linux/New Mac-based OS
        // CRLF - on a Windows-based OS
        if (map == null) {
            return "{}";
        }

        return map.keySet().stream()
            .sorted()
            .map(key -> {
                var list = map.get(key);
                return getString(key, list);
            })
            .collect(Collectors.joining(System.lineSeparator(), "{" + System.lineSeparator(),
                System.lineSeparator() + "}"));
    }

    public static String editString(String key, Object firstValue, Object secondValue) {
        return " ".repeat(DIFFER) + "- " + key + ": " + firstValue + System.lineSeparator()
            + " ".repeat(DIFFER) + "+ " + key + ": " + secondValue;
    }

    private static String getString(String key, List<Object> list) {
        var action = list.get(0).toString();
        var firstValue = list.get(1);

//        System.out.println(action); // exist
//        System.out.println(action.getClass().getSimpleName()); // String

        String temp = switch (action) {
            case "exist" -> " ".repeat(MATCH) + key + ": " + firstValue;
            case "delete" -> " ".repeat(DIFFER) + "- " + key + ": " + firstValue;
            case "add" -> " ".repeat(DIFFER) + "+ " + key + ": " + firstValue;
            case "edit" -> editString(key, firstValue, list.get(2));
            default -> "";
        };

        return temp;
    }
}

/*
попробовать отсюда
https://github.com/hexlet-boilerplates/java-package/blob/main/.github/workflows/main.yml
name: Java CI

on:
  - push
  - pull_request

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '20'
          distribution: 'temurin'
      - uses: gradle/gradle-build-action@v2
        with:
          gradle-version: 8.3
      - run: ./gradlew checkstyleMain
      - run: ./gradlew test
      # - name: Publish code coverage
      #   uses: paambaati/codeclimate-action@v5.0.0
      #   env:
      #     CC_TEST_REPORTER_ID: ${{secrets.CC_TEST_REPORTER_ID}}
      #     JACOCO_SOURCE_PATH: src/main/java
      #   with:
      #     coverageCommand: make report
      #     coverageLocations: ${{github.workspace}}/build/reports/jacoco/test/jacocoTestReport.xml:jacoco
*/
