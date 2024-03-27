package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static final Path RESOURCE_DIRECTORY = Paths.get("src", "test", "resources");
    private static final String ABSOLUTE_PATH = RESOURCE_DIRECTORY.toFile().getAbsolutePath();
    private static final String FILE_1 = String.join(File.separator, ABSOLUTE_PATH, "fixtures", "file1.json");
    private static final String FILE_2 = String.join(File.separator, ABSOLUTE_PATH, "fixtures", "file2.json");
    private static final String FILE_3 = String.join(File.separator, ABSOLUTE_PATH, "fixtures", "file1.yml");
    private static final String FILE_4 = String.join(File.separator, ABSOLUTE_PATH, "fixtures", "file2.yml");

    private static final String RESULT_STYLISH = String.join(System.lineSeparator(),
        "{",
        " ".repeat(4) + "chars1: [a, b, c]",
        " ".repeat(2) + "- chars2: [d, e, f]",
        " ".repeat(2) + "+ chars2: false",
        " ".repeat(2) + "- checked: false",
        " ".repeat(2) + "+ checked: true",
        " ".repeat(2) + "- default: null",
        " ".repeat(2) + "+ default: [value1, value2]",
        " ".repeat(2) + "- id: 45",
        " ".repeat(2) + "+ id: null",
        " ".repeat(2) + "- key1: value1",
        " ".repeat(2) + "+ key2: value2",
        " ".repeat(4) + "numbers1: [1, 2, 3, 4]",
        " ".repeat(2) + "- numbers2: [2, 3, 4, 5]",
        " ".repeat(2) + "+ numbers2: [22, 33, 44, 55]",
        " ".repeat(2) + "- numbers3: [3, 4, 5]",
        " ".repeat(2) + "+ numbers4: [4, 5, 6]",
        " ".repeat(2) + "+ obj1: {nestedKey=value, isNested=true}",
        " ".repeat(2) + "- setting1: Some value",
        " ".repeat(2) + "+ setting1: Another value",
        " ".repeat(2) + "- setting2: 200",
        " ".repeat(2) + "+ setting2: 300",
        " ".repeat(2) + "- setting3: true",
        " ".repeat(2) + "+ setting3: none",
        "}"
    );
    private static final String RESULT_PLAIN = String.join(System.lineSeparator(),
        "Property 'chars2' was updated. From [complex value] to false",
        "Property 'checked' was updated. From false to true",
        "Property 'default' was updated. From null to [complex value]",
        "Property 'id' was updated. From 45 to null",
        "Property 'key1' was removed",
        "Property 'key2' was added with value: 'value2'",
        "Property 'numbers2' was updated. From [complex value] to [complex value]",
        "Property 'numbers3' was removed",
        "Property 'numbers4' was added with value: [complex value]",
        "Property 'obj1' was added with value: [complex value]",
        "Property 'setting1' was updated. From 'Some value' to 'Another value'",
        "Property 'setting2' was updated. From 200 to 300",
        "Property 'setting3' was updated. From true to 'none'"
    );
    private static final String RESULT_JSON = String.join(System.lineSeparator(),
        "{",
        " ".repeat(2) + "\"add\" : {",
        " ".repeat(4) + "\"key2\" : \"value2\",",
        " ".repeat(4) + "\"numbers4\" : [ 4, 5, 6 ],",
        " ".repeat(4) + "\"obj1\" : {",
        " ".repeat(6) + "\"nestedKey\" : \"value\",",
        " ".repeat(6) + "\"isNested\" : true",
        " ".repeat(4) + "}",
        " ".repeat(2) + "},",
        " ".repeat(2) + "\"delete\" : {",
        " ".repeat(4) + "\"key1\" : \"value1\",",
        " ".repeat(4) + "\"numbers3\" : [ 3, 4, 5 ]",
        " ".repeat(2) + "},",
        " ".repeat(2) + "\"edit\" : {",
        " ".repeat(4) + "\"chars2_new\" : false,",
        " ".repeat(4) + "\"chars2_old\" : [ \"d\", \"e\", \"f\" ],",
        " ".repeat(4) + "\"checked_new\" : true,",
        " ".repeat(4) + "\"checked_old\" : false,",
        " ".repeat(4) + "\"default_new\" : [ \"value1\", \"value2\" ],",
        " ".repeat(4) + "\"default_old\" : null,",
        " ".repeat(4) + "\"id_new\" : null,",
        " ".repeat(4) + "\"id_old\" : 45,",
        " ".repeat(4) + "\"numbers2_new\" : [ 22, 33, 44, 55 ],",
        " ".repeat(4) + "\"numbers2_old\" : [ 2, 3, 4, 5 ],",
        " ".repeat(4) + "\"setting1_new\" : \"Another value\",",
        " ".repeat(4) + "\"setting1_old\" : \"Some value\",",
        " ".repeat(4) + "\"setting2_new\" : 300,",
        " ".repeat(4) + "\"setting2_old\" : 200,",
        " ".repeat(4) + "\"setting3_new\" : \"none\",",
        " ".repeat(4) + "\"setting3_old\" : true",
        " ".repeat(2) + "},",
        " ".repeat(2) + "\"exist\" : {",
        " ".repeat(4) + "\"chars1\" : [ \"a\", \"b\", \"c\" ],",
        " ".repeat(4) + "\"numbers1\" : [ 1, 2, 3, 4 ]",
        " ".repeat(2) + "}",
        "}"
    );

    @Test
    public void generateJsonToDefault() throws IOException {
        var actual = generate(FILE_1, FILE_2);
        assertEquals(RESULT_STYLISH, actual);
    }

    @Test
    public void generateJsonToStylish() throws IOException {
        var actual = generate(FILE_1, FILE_2, "stylish");
        assertEquals(RESULT_STYLISH, actual);
    }

    @Test
    public void generateJsonToPlain() throws IOException {
        var actual = generate(FILE_1, FILE_2, "plain");
        assertEquals(RESULT_PLAIN, actual);
    }

    @Test
    public void generateJsonToJson() throws IOException {
        var actual = generate(FILE_1, FILE_2, "json");
        assertEquals(RESULT_JSON, actual);
    }

    @Test
    public void generateYamlToDefault() throws IOException {
        var actual = generate(FILE_3, FILE_4);
        assertEquals(RESULT_STYLISH, actual);
    }

    @Test
    public void generateYamlToStylish() throws IOException {
        var actual = generate(FILE_3, FILE_4, "stylish");
        assertEquals(RESULT_STYLISH, actual);
    }

    @Test
    public void generateYamlToPlain() throws IOException {
        var actual = generate(FILE_3, FILE_4, "plain");
        assertEquals(RESULT_PLAIN, actual);
    }

    @Test
    public void generateYamlToJson() throws IOException {
        var actual = generate(FILE_3, FILE_4, "json");
        assertEquals(RESULT_JSON, actual);
    }
}
