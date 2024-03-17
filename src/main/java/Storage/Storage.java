package Storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    public static void saveTasksToFile(Map<LocalDate, List<String>> tasks) {
        try (FileWriter writer = new FileWriter(FILE_PATH.toFile())) {
            for (Map.Entry<LocalDate, List<String>> entry : tasks.entrySet()) {
                LocalDate date = entry.getKey();
                List<String> taskList = entry.getValue();
                for (String task : taskList) {
                    writer.write(date + "|" + task + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("I/O exception occurred during file handling");
        }
    }

}
