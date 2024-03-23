package storage;

import ui.Ui;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Storage {

    public static ArrayList<String> loadDataFromFile(String filePath) {
        ArrayList<String> data = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String nextLineOfData = scanner.nextLine();

                data.add(nextLineOfData);
            }
        } catch (FileNotFoundException e) {
            createFolder(filePath);
        }

        return data;
    }

    public static void createFolder(String filePath) {
        try {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (IOException e) {
            Ui.printMessageWithSepNewLine(e.getMessage());
        }
    }

    public static <T> void saveTasksToFile(String filePath, ArrayList<T> data) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (T item : data) {
                fw.write(item.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            Ui.printMessageWithSepNewLine(e.getMessage());
        }
    }

    public static boolean isFileCreated (String filePath) {
        File f = new File(filePath);
        return f.exists() && !f.isDirectory();
    }

}



