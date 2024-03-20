package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents an add command where a new task is added to the existing list of task.
 * String <code>FILENAME</code> represents the designated relative file path for the file.
 * File <code>dukeData</code> represents the File object created to be updated.
 */
public class Storage {
    private static final String FILENAME = "./StockMasterData.txt";
  
    private static File stockMaster;


    /**
     * Write contents to the file.
     *
     * @param filePath  File path where the file is located.
     * @param textToAdd The line of text to write to the file.
     * @param ifAppend  Indicate if append the text at the end of the file (true)
     *                  or overwrite the file (false).
     * @throws IOException If file is not found at the indicated file path.
     */
    public static void writeToFile(String filePath, String textToAdd, boolean ifAppend) throws IOException {
        FileWriter writer = new FileWriter(filePath, ifAppend);
        writer.write(textToAdd);
        writer.close();
    }

    public static void updateFile(String inputText, boolean ifAppend) {
        try {
            writeToFile(FILENAME, inputText, ifAppend);
        } catch (IOException e) {
            System.out.println("IOExceptions occurred");
        }
    }

    /**
     * Returns the private file directory of storage.
     */
    public static String getFileDirectory() {
        return FILENAME;
    }

    /**
     * Set the private File dukeData.
     */
    public static File setFile() {
        return new File(FILENAME);
    }

    /**
     * Read lines from the file and identify tasks written inside.
     * Add the identified tasks into a list of existing tasks.
     *
     */
    public static void readFromFile(File fileName) {
        try {
            Scanner scanner = new Scanner(fileName);
            while (scanner.hasNext()) {
                String lineSkipped = scanner.nextLine();
            }
        } catch(FileNotFoundException e){
            System.out.println("File does not exist.");
        }
    }

    public static void main (String[]args){
        stockMaster = setFile();
        try {
            writeToFile(stockMaster.getPath(), "", true);
        } catch (IOException e) {
            System.out.println("File does not exist.");
        }
    }
}

