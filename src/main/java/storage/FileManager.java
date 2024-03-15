package storage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class FileManager {
    private static final String DEFAULT_DATA_FILE_PATH = "./data/data.txt";
    private File dataFile;
    private Scanner scanner = null;
    private boolean hasNoFileErrors;

    public FileManager(String dataFilePath) {
        this.dataFile = new File(dataFilePath);
    }
    public FileManager() {
        this(FileManager.DEFAULT_DATA_FILE_PATH);
    }

    public boolean openFile() {
        try {
            if (!this.dataFile.exists()) {
                printCreatingFileMessage();
                createDataFile();
            }
            this.scanner = new Scanner(this.dataFile);
            this.hasNoFileErrors = true;
            printDataSavedMessage();
        } catch (Exception e) {
            this.scanner = null;
            this.hasNoFileErrors = false;
            printDataLossWarning();
        }
        return this.hasNoFileErrors;
    }

    private void printDataLossWarning() {
        System.out.println("Errors! Your data will not be saved");
    }
    private void printCreatingFileMessage() {
        System.out.println("Creating your data bank...");
    }
    private void printDataSavedMessage() {
        System.out.println("Data file successfully created!");
    }

    private void createDataFile() throws Exception {
        boolean hasDataDirectory = this.dataFile.getParentFile().exists();
        boolean isDataDirectoryReady = hasDataDirectory || this.dataFile.getParentFile().mkdirs();
        if (!isDataDirectoryReady) {
            throw new Exception();
        }
        boolean isDataFileCreated = this.dataFile.createNewFile();
        if (!isDataFileCreated) {
            throw new Exception();
        }
    }

    public boolean hasNextLine() {
        return this.scanner.hasNext();
    }

    public String readNextLine() {
        return this.scanner.nextLine();
    }

    public void save(String data) {
        try {
            if (!this.hasNoFileErrors) {
                printDataLossWarning();
                return;
            }
            FileWriter fileWriter = new FileWriter(this.dataFile);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            printDataLossWarning();
        }
    }
}
