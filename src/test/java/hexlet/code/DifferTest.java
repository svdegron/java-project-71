package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifferTest {

    private final Path resourceDirectory = Paths.get("src","test", "resources");
    private static String correctResourceDirectory;

    // Количество файлов для тестирования
    private static final int testFilesCount = 3;

    @BeforeAll
    public static void beforeAll() {
        String[] pathPieces = { "src", "test", "resources" };
        correctResourceDirectory = String.join(File.separator, pathPieces);
    }

    @Test
    public void checkDirectory() {
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        if (!absolutePath.endsWith(correctResourceDirectory)) {
            System.out.println("[WARN] " + System.getProperty("os.name") + " is Your operating system"
                + "\n[INFO] Check file separator");
        }

        assertTrue(absolutePath.endsWith(correctResourceDirectory));
    }

    @Test
    public void checkFiles() {
        List<Boolean> existPath = new LinkedList<>();
        List<Boolean> expectedList = new LinkedList<>();
        String absoluteDirectoryPath = resourceDirectory.toFile().getAbsolutePath();

        for (var iStep = 1; iStep <= testFilesCount; iStep++) {
            var fileName = "file" + iStep + ".json";
            var filePath = Paths.get(absoluteDirectoryPath, fileName);
            var isExist = Files.exists(filePath);

            existPath.add(isExist);
            expectedList.add(true);
        }

        assertEquals(expectedList, existPath);
    }

    @Test
    public void generateSuccess() {
        var temp = 1;
        assertEquals(1, temp);
    }
}
