package ActiveEdge;

import ActiveEdge.Task.GoalTask;
import ActiveEdge.Task.LogMeals;
import ActiveEdge.Task.Task;
import ActiveEdge.Task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;

public class Storage {
    public static void ensureDirectoryExists(String filePath) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    public static void createFile(String filePath) {
        ensureDirectoryExists(filePath);
        try {
            FileWriter file = new FileWriter(filePath);
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred while creating file: " + e.getMessage());
        }
    }

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
                    LogMeals newTask = new LogMeals(mealName, Integer.parseInt(items[len - 2]),  Integer.parseInt(items[len - 1]));
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
