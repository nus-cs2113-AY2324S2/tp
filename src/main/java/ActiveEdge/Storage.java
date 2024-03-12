package ActiveEdge;

import ActiveEdge.Task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;

import static ActiveEdge.Task.TaskList.tasksList;

public class Storage {
    public static void ensureDirectoryExists(String filePath) {
        File file = new java.io.File(filePath);
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
            System.out.println(" An error occurred while creating file: " + e.getMessage());
        }
    }

    public static void saveLogsToFile(String filePath) {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (int i = 0; i < tasksList.size(); i++) {
                String out = tasksList.get(i).getDescription();
                fw.write(out + "\n");
            }
        } catch (IOException e) {
            System.out.println(" An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    public static void fetchData() {
        String filePath = Paths.get(System.getProperty("user.dir"), "data", "data.txt").toString();
        java.io.File file = new java.io.File(filePath);
        if (!file.exists()) {
            createFile(filePath);
        }
        try{
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String description = s.nextLine();
                Task newTask = new Task(description);
                tasksList.add(newTask);
            }
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }

    }
}
