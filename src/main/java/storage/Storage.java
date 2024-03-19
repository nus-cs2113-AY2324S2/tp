package storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {

    private static final Path FILE_PATH = Path.of("./save/tasks.txt");

    /**
     * Creates directory and tasks.txt if it does not exist
     *
     * @throws IOException If an I/O exception occurs during file handling
     */
    public static void createNewFile() throws IOException {
        if (!Files.isDirectory(FILE_PATH.getParent())) {
            //  System.out.println("Directory not found, creating new one");
            Files.createDirectories(FILE_PATH.getParent());
        }
        if (!Files.exists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
        }
    }

    /**
     * Reads tasks in hashmap and writes it in formatted form to tests.txt
     *
     * @param tasks
     */
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

    /**
     * Loads tasks from test.txt to hashmap
     *
     * @return tasks hashmap of tasks read from test.txt
     */
    public static Map<LocalDate, List<String>> loadTasksFromFile() {
        Map<LocalDate, List<String>> tasks = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                LocalDate date = LocalDate.parse(parts[0]);
                String task = parts[1];
                tasks.computeIfAbsent(date, k -> new ArrayList<>()).add(task);
            }
        } catch (IOException e) {
            System.out.println("I/O exception occurred during file handling");
        }
        return tasks;
    }

}
