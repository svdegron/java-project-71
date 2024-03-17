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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifferTest {

    private static final Path RESOURCE_DIRECTORY = Paths.get("src", "test", "resources");

    // Количество файлов для тестирования
    private static final int JSON_FILES_COUNT = 4;

    private static String correctResourceDirectory;
    private static String absoluteDirectoryPath;

    @BeforeAll
    public static void beforeAll() {
        String[] pathPieces = {"src", "test", "resources"};
        correctResourceDirectory = String.join(File.separator, pathPieces);

        absoluteDirectoryPath = RESOURCE_DIRECTORY.toFile().getAbsolutePath();
    }

    @Test
    public void checkResourceDirectory() {
        // Здесь разница в один символ ("\" или "/" сложно сразу заметить), поэтому добавил сообщение
        if (!absoluteDirectoryPath.endsWith(correctResourceDirectory)) {
            System.out.println("[WARN] " + System.getProperty("os.name") + " is Your operating system"
                + "\n[INFO] Check file separator");
        }

        assertTrue(absoluteDirectoryPath.endsWith(correctResourceDirectory));
    }

    @Test
    public void checkJsonFiles() {
        Map<Path, Boolean> existPath = new HashMap<>();
        Map<Path, Boolean> expected = new HashMap<>();

        for (var iStep = 1; iStep <= JSON_FILES_COUNT; iStep++) {
            var fileName = "file" + iStep + ".json";
            var absoluteFilePath = Paths.get(absoluteDirectoryPath, fileName);
            var isExist = Files.exists(absoluteFilePath);

            existPath.put(absoluteFilePath, isExist);
            expected.put(absoluteFilePath, true);
        }

        // специально переделал на Map
        // чтобы отображались пути к файлам в Expected/Actual

        assertEquals(expected, existPath);
    }

    @Test
    public void checkYamlFiles() {
        Map<Path, Boolean> existPath = new HashMap<>();
        Map<Path, Boolean> expected = new HashMap<>();

        for (var iStep = 1; iStep <= JSON_FILES_COUNT; iStep++) {
            var fileName = "file" + iStep + ".yml";
            var absoluteFilePath = Paths.get(absoluteDirectoryPath, fileName);
            var isExist = Files.exists(absoluteFilePath);

            existPath.put(absoluteFilePath, isExist);
            expected.put(absoluteFilePath, true);
        }

        assertEquals(expected, existPath);
    }

    @Test
    public void generateSuccess() {
        // Проверить количество файлов (забыл, удалили, не вложили…)
        assertTrue(JSON_FILES_COUNT == 4);

        var absolutePathResult1 = Paths.get(absoluteDirectoryPath, "diff-stylish-1-to-2.txt");
        var absolutePathResult2 = Paths.get(absoluteDirectoryPath, "diff-stylish-2-to-1.txt");

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

        var absoluteFilePath1 = Paths.get(absoluteDirectoryPath, fileName1);
        var absoluteFilePath2 = Paths.get(absoluteDirectoryPath, fileName2);

        assertTrue(Files.exists(absoluteFilePath1));
        assertTrue(Files.exists(absoluteFilePath2));

        String actualDiffDirect;
        String actualDiffReverse;

        try {
            var filepath1 = absoluteFilePath1.toString();
            var filepath2 = absoluteFilePath2.toString();

            actualDiffDirect = Differ.generate(filepath1, filepath2, "stylish");
            actualDiffReverse = Differ.generate(filepath2, filepath1, "stylish");
        } catch (IOException e) {
            actualDiffDirect = e.getMessage();
            actualDiffReverse = e.getMessage();
        }

        // 1 сравниваем со 2
        assertEquals(expectedDiffDirect, actualDiffDirect);
        // в обратном порядке "+" и "-" меняются местами
        assertEquals(expectedDiffReverse, actualDiffReverse);
    }

    @Test
    public void generateFileMissing() {
        var fileName1 = "this-second-File-is-Not-Exist.json";
        var fileName2 = "this-First-File-is-Not-Exist.json";

        var absoluteFilePath1 = Paths.get(absoluteDirectoryPath, fileName1);
        var absoluteFilePath2 = Paths.get(absoluteDirectoryPath, fileName2);

        // Наличие файлов с предполагаемыми результатами метода
        assertFalse(Files.exists(absoluteFilePath1));
        assertFalse(Files.exists(absoluteFilePath2));

        String expectedDiffDirect = null;
        String expectedDiffReverse = null;

        String actualDiffDirect;
        String actualDiffReverse;

        var filepath1 = absoluteFilePath1.toString();
        var filepath2 = absoluteFilePath2.toString();

        try {
            actualDiffDirect = Differ.generate(filepath1, filepath2, "stylish");
        } catch (IOException e) {
            actualDiffDirect = e.getMessage();
            expectedDiffDirect = e.getMessage();
        }

        try {
            actualDiffReverse = Differ.generate(filepath2, filepath1, "stylish");
        } catch (IOException e) {
            actualDiffReverse = e.getMessage();
            expectedDiffReverse = e.getMessage();
        }

        // по аналогии с тестом, который проверяет корректный результат
        assertEquals(expectedDiffDirect, actualDiffDirect);
        assertEquals(expectedDiffReverse, actualDiffReverse);
    }
}
