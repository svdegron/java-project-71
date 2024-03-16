package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private static final Path RESOURCE_DIRECTORY = Paths.get("src", "test", "resources");
    @Test
    public void getMapFail() {
        var fileName1 = "this-File-is-Not-Exist.json";
        var absoluteDirectoryPath = RESOURCE_DIRECTORY.toFile().getAbsolutePath();
        var absoluteFilePath = Paths.get(absoluteDirectoryPath, fileName1);

        String actualMessage = null;
        String expectedMessage = "Check that the file \"" + absoluteFilePath.toString()
            + "\" exists and can be accessed";

        try {
            Parser.getMap(absoluteFilePath);
        } catch (IOException e) {
            actualMessage = e.getMessage();
        }

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void getMapSuccess() {
        var fileName1 = "file2.json";
        var absoluteDirectoryPath = RESOURCE_DIRECTORY.toFile().getAbsolutePath();
        var absoluteFilePath = Paths.get(absoluteDirectoryPath, fileName1);

        String actualMessage = null;
        String expectedMessage = null;

        Map<String, Object> actualMap = null;
        Map<String, Object> expectedMap = new HashMap<String, Object>();

        expectedMap.put("timeout", 20);
        expectedMap.put("verbose", true);
        expectedMap.put("host", "hexlet.io");

        try {
            actualMap = Parser.getMap(absoluteFilePath);
        } catch (IOException e) {
            actualMessage = e.getMessage();
        }

        assertEquals(expectedMessage, actualMessage);
        assertEquals(expectedMap, actualMap);
    }
}
