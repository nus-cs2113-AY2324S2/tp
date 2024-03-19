package activeedge;

import activeedge.task.GoalTask;
import activeedge.task.LogMeals;
import activeedge.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;

/**
 * The {@code Storage} class handles file operations for the Health Tracker application.
 * It includes methods for ensuring directory existence, creating files, saving logs to files,
 * and fetching data from files.
 */
public class Storage {

    /**
     * Ensures that the directory for a given file path exists.
     * If the directory does not exist, it creates all necessary parent directories.
     *
     * @param filePath The file path for which to ensure directory existence.
     */
    public static void ensureDirectoryExists(String filePath) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    /**
     * Creates a new file at the specified file path.
     * If the file's directory does not exist, it ensures the creation of the directory structure.
     *
     * @param filePath The path of the file to be created.
     */
    public static void createFile(String filePath) {
        ensureDirectoryExists(filePath);
        try {
            FileWriter file = new FileWriter(filePath);
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred while creating file: " + e.getMessage());
        }
    }

    /**
     * Saves the current logs from {@code TaskList} to a file at the given file path.
     * Each task is converted to a string and written to the file, one task per line.
     *
     * @param filePath The path of the file where logs should be saved.
     */
    public static void saveLogsToFile(String filePath) {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (int i = 0; i < TaskList.tasksList.size(); i++) {
                String out = TaskList.tasksList.get(i).toString();
                fw.write(out + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Fetches and loads data from a specified data file into the application's memory.
     * This method attempts to read tasks from the file, parsing each line to recreate
     * the appropriate {@code Task} objects. The tasks are then added to the {@code TaskList}.
     */
    public static void fetchData() {
        String filePath = Paths.get(System.getProperty("user.dir"), "data", "data.txt").toString();
        File file = new File(filePath);
        if (!file.exists()) {
            createFile(filePath);
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String task = scanner.nextLine();
                if(task.startsWith("Meal")){
                    String[] items = task.trim().split(" ");
                    int len = items.length;
                    assert len >= 4;
                    String mealName = "";
                    for (int i = 1; i <= len - 3; i++){
                        mealName = mealName + items[i];
                    }
                    LogMeals newTask = new LogMeals(mealName,
                            Integer.parseInt(items[len - 2]),
                            Integer.parseInt(items[len - 1]));
                    TaskList.tasksList.add(newTask);
                }else if (task.startsWith("Goal")){
                    String[] items = task.trim().split(" ");
                    GoalTask newTask = new GoalTask(items[1], Integer.parseInt(items[2]));
                    TaskList.tasksList.add(newTask);
                } else if (task.startsWith("Water")) {
                    String[] items = task.trim().split(" ");
                    GoalTask newTask = new GoalTask(items[0], Integer.parseInt(items[1]));
                    TaskList.tasksList.add(newTask);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
