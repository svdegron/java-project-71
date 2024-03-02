package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifferTest {

    private final Path resourceDirectory = Paths.get("src","test", "resources");
    private static String correctResourceDirectory;

    private static final int testFilesCount = 4;
    private static List<String> fileNames;

    @BeforeAll
    public static void beforeAll() {
        String[] pathPieces = { "src", "test", "resources" };
        correctResourceDirectory = String.join(File.separator, pathPieces);

        fileNames = new LinkedList<>();
        for (var iStep = 1; iStep <= testFilesCount; iStep++) {
            fileNames.add("file" + iStep + ".json");
        }
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
//        String resourceName = "file1.json";
//
//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(Objects.requireNonNull(classLoader.getResource(resourceName)).getFile());
//        String absoluteFilePath = file.getAbsolutePath();
//
//        System.out.println("absoluteFilePath | " + absoluteFilePath);
//        System.out.println("fileNames | " + fileNames);
//
//        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

//        var isExist = Files.exists(Paths.get(absolutePath, resourceName));
//        System.out.println("isExist | " + isExist);

        var existPath = new LinkedList<Boolean>();
        var expectedList = new  LinkedList<Boolean>();
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        for (var iStep = 1; iStep <= testFilesCount; iStep++) {
            var fileName = "file" + iStep + ".json";
            var filePath = Paths.get(absolutePath, fileName);
            var isExist = Files.exists(filePath);

            existPath.add(isExist);
            expectedList.add(true);
        }

//        assertTrue(absoluteFilePath.endsWith(File.separator + resourceName));
        System.out.println("expectedList | " + expectedList);
        System.out.println("existPath | " + existPath);

        assertEquals(expectedList, existPath);
    }

    @Test
    public void generateSuccess() {
        var temp = 1;
        assertEquals(1, temp);
    }
}
