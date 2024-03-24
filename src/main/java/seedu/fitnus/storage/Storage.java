package seedu.fitnus.storage;

import seedu.fitnus.user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    public String textContent;
    public String folderPath;
    public String filePath;
    private User user;

    public Storage(String folderPath, String filePath) {
        this.textContent = "";
        this.folderPath = folderPath;
        this.filePath = filePath;
    }

    /**
     * Append the contents to a single string that will be saved
     * @param content String to be appended
     */
    public void appendTextContent(String content) {
        textContent += (content + '\n');
    }

    /**
     * Load the saved file and convert it into list of text formatted commands
     * @return List of text formatted commands
     * @throws FileNotFoundException If the file does not exist
     */
    public ArrayList<String> readFile() throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<>();
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            input.add(s.nextLine());
        }
        s.close();
        return input;
    }

    /**
     * Creates new folder and file if both did not exist
     */
    public void createFile() {
        try {
            File dataFolder = new File(folderPath);
            if (!dataFolder.exists()) {
                System.out.println("Folder not found, attempting to create folder.");
                if (dataFolder.mkdirs()) {
                    System.out.println("Data folder has been created successfully.");
                } else {
                    System.err.println("Failed to create data folder.");
                }
            }
            File textFile = new File(filePath);
            if (textFile.createNewFile()) {
                System.out.println("File has been created.");
            }
        } catch (IOException err) {
            System.out.println("Failed to create file.");
        }
    }

    /**
     * Write the given string to the text file
     * @param textToAdd String to be written
     * @throws IOException If there is any error encountered
     */
    public void writeFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
