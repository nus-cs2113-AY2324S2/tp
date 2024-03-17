package Storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {

    protected static final Path FILE_PATH = Path.of("./save/tasks.txt");

    /**
     * Creates directory and tasks.txt if it does not exist
     *
     * @throws IOException If an I/O exception occurs during file handling
     */
    public static void createNewFile() throws IOException {
        if (!Files.isDirectory(FILE_PATH.getParent())) {
            System.out.println("Directory not found, creating new one");
            Files.createDirectories(FILE_PATH.getParent());
        }
        if (!Files.exists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
        }
    }
}
