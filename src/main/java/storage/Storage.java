package storage;

import data.Task;
import data.TaskManagerException;
import data.TaskType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static data.TaskManager.addTask;
import static data.TaskManager.parseTaskType;
import static data.TaskType.DEADLINE;
import static data.TaskType.EVENT;

public class Storage {

    public static final Path FILE_PATH = Path.of("./save/tasks.txt");
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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
            logger.log(Level.INFO, "new directory created");
        }
        if (!Files.exists(path)) {
            Files.createFile(path);
            logger.log(Level.INFO, "new tests.txt file created");
        }
    }

    /**
     * Reads tasks in hashmap and writes it in formatted form to tests.txt.
     *
     * @param tasks Hashmap of tasks.
     * @param path File Path of tests.txt file.
     */
    public static void saveTasksToFile(Map<LocalDate, List<Task>> tasks, Path path) {
        try (FileWriter writer = new FileWriter(path.toFile())) {
            for (Map.Entry<LocalDate, List<Task>> entry : tasks.entrySet()) {
                assert entry != null;
                LocalDate date = entry.getKey();
                assert date != null;
                List<Task> taskList = entry.getValue();
                assert taskList != null;
                for (Task task : taskList) {
                    String taskSaveFormat = task.getSaveFormat();
                    writer.write(date + "|" + taskSaveFormat + System.lineSeparator());
                    String taskDescription = task.getName();
                    logger.log(Level.INFO, "task added: " + taskDescription);
                }
            }
        } catch (IOException e) {
            System.out.println("I/O exception occurred during file handling");
        }
    }

    /**
     * Loads tasks from test.txt to hashmap.
     *
     * @param path File Path of tests.txt file.
     * @return tasks hashmap of tasks read from test.txt.
     */
    public static Map<LocalDate, List<Task>> loadTasksFromFile(Path path) {
        Map<LocalDate, List<Task>> tasks = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!checkFileFormat(line)) {
                    throw new StorageFileException();
                }
                String[] parts = line.split("\\|");
                LocalDate date = LocalDate.parse(parts[0]);
                TaskType taskType = parseTaskType(parts[1]);
                String taskDescription = parts[2];
                String[] dates = {null, null};
                if (taskType == DEADLINE) {
                    dates[0] = parts[3];
                } else if (taskType == EVENT) {
                    dates[0] = parts[3];
                    dates[1] = parts[4];
                }
                addTask(date, taskDescription, taskType, dates);
            }
        } catch (IOException e) {
            System.out.println("I/O exception occurred during file handling");
            logger.log(Level.WARNING, "I/O exception occurred");
        } catch (StorageFileException e) {
            System.out.println("tasks.txt is in wrong format.");
            logger.log(Level.WARNING, "Wrong tasks.txt format");
        } catch (TaskManagerException e) {
            logger.log(Level.WARNING, "Invalid task type for task.");
        }
        logger.log(Level.INFO, "tasks returned");
        return tasks;
    }

    public static boolean checkFileFormat(String line) {
        String regex = "\\d{4}-\\d{2}-\\d{2}\\|.+";
        return line.matches(regex);
    }

}
