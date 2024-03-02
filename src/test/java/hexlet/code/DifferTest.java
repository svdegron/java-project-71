package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifferTest {

    private final Path resourceDirectory = Paths.get("src", "test", "resources");
    private static String correctResourceDirectory;

    // Количество файлов для тестирования
    private static final int JSON_FILES_COUNT = 3;

    @BeforeAll
    public static void beforeAll() {
        String[] pathPieces = {"src", "test", "resources"};
        correctResourceDirectory = String.join(File.separator, pathPieces);
    }

    @Test
    public void checkResourceDirectory() {
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        // Здесь разница в один символ ("\" или "/" сложно сразу заметить), поэтому добавил сообщение
        if (!absolutePath.endsWith(correctResourceDirectory)) {
            System.out.println("[WARN] " + System.getProperty("os.name") + " is Your operating system"
                + "\n[INFO] Check file separator");
        }

        assertTrue(absolutePath.endsWith(correctResourceDirectory));
    }

    @Test
    public void checkTestFiles() {
        Map<Path, Boolean> existPath = new HashMap<>();
        Map<Path, Boolean> expected = new HashMap<>();
        String absoluteDirectoryPath = resourceDirectory.toFile().getAbsolutePath();

        for (var iStep = 1; iStep <= JSON_FILES_COUNT; iStep++) {
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
        // Проверить количество файлов (забыл, удалили, не вложили…)
        assertEquals(3, JSON_FILES_COUNT);

        var resultFileName1 = "diff-1-to-2.txt";
        var resultFileName2 = "diff-2-to-1.txt";

        String absoluteDirectoryPath = resourceDirectory.toFile().getAbsolutePath();

        var absolutePathResult1 = Paths.get(absoluteDirectoryPath, resultFileName1);
        var absolutePathResult2 = Paths.get(absoluteDirectoryPath, resultFileName2);

        // Наличие файлов с предполагаемыми результатами метода
        assertTrue(Files.exists(absolutePathResult1));
        assertTrue(Files.exists(absolutePathResult2));

        String expectedDiffDirect;
        String expectedDiffReverse;

        // Получаем ожидаемые результаты
        try {
            expectedDiffDirect = Files.readString(absolutePathResult1);
            expectedDiffReverse = Files.readString(absolutePathResult2);
        } catch (IOException e) {
            expectedDiffDirect = e.getMessage();
            expectedDiffReverse = e.getMessage();
        }

        // Проверяем работу метода на файлах
        var fileName1 = "file1.json";
        var fileName2 = "file2.json";

        var absoluteFilePath1 = Paths.get(absoluteDirectoryPath, fileName1).toString();
        var absoluteFilePath2 = Paths.get(absoluteDirectoryPath, fileName2).toString();

        String actualDiffDirect;
        String actualDiffReverse;

        try {
            actualDiffDirect = Differ.generate(absoluteFilePath1, absoluteFilePath2);
            actualDiffReverse = Differ.generate(absoluteFilePath2, absoluteFilePath1);
        } catch (IOException e) {
            actualDiffDirect = e.getMessage();
            actualDiffReverse = e.getMessage();
        }

        // 1 сравниваем со 2
        assertEquals(expectedDiffDirect, actualDiffDirect);
        // в обратном порядке "+" и "-" меняются местами
        assertEquals(expectedDiffReverse, actualDiffReverse);
    }
}
