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

    public static final Path FILE_PATH = Path.of("./save/tasks.txt");

    /**
     * Creates directory and tasks.txt if it does not exist
     *
     * @param path File Path of tests.txt file
     * @throws IOException If an I/O exception occurs during file handling
     */
    public static void createNewFile(Path path) throws IOException {
        if (!Files.isDirectory(path.getParent())) {
            //  System.out.println("Directory not found, creating new one");
            Files.createDirectories(path.getParent());
        }
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    /**
     * Reads tasks in hashmap and writes it in formatted form to tests.txt
     *
     * @param tasks Hashmap of tasks
     * @param path File Path of tests.txt file
     */
    public static void saveTasksToFile(Map<LocalDate, List<String>> tasks, Path path) {
        try (FileWriter writer = new FileWriter(path.toFile())) {
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
     * @param path File Path of tests.txt file
     * @return tasks hashmap of tasks read from test.txt
     */
    public static Map<LocalDate, List<String>> loadTasksFromFile(Path path) {
        Map<LocalDate, List<String>> tasks = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
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
