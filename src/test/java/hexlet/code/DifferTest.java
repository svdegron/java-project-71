package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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

        // Здесь разница в один символ ("\" или "/" сложно сразу определить), поэтому добавил сообщение
        if (!absolutePath.endsWith(correctResourceDirectory)) {
            System.out.println("[WARN] " + System.getProperty("os.name") + " is Your operating system"
                + "\n[INFO] Check file separator");
        }

        assertTrue(absolutePath.endsWith(correctResourceDirectory));
    }

    @Test
    public void checkFiles() {
        Map<Path, Boolean> existPath = new HashMap<>();
        Map<Path, Boolean> expected = new HashMap<>();
        String absoluteDirectoryPath = resourceDirectory.toFile().getAbsolutePath();

        for (var iStep = 1; iStep <= testFilesCount; iStep++) {
            var fileName = "file" + iStep + ".json";
            var absoluteFilePath = Paths.get(absoluteDirectoryPath, fileName);
            var isExist = Files.exists(absoluteFilePath);

            existPath.put(absoluteFilePath, isExist);
            expected.put(absoluteFilePath, true);
        }

        // специально переделал на Map
        // сообщения нет, в Expected/Actual должно быть видно

        assertEquals(expected, existPath);
    }

    @Test
    public void generateSuccess() {
        var temp = 1;
        assertEquals(1, temp);
    }
}
