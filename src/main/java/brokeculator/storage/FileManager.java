package brokeculator.storage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import brokeculator.frontend.UI;

import java.io.FileWriter;
//@@author STeng618
public class FileManager {
    private static final String DEFAULT_DATA_FILE_PATH = "./data/data.txt";
    private static final String DEFAULT_CATEGORY_FILE_PATH = "./data/category.txt";
    private File dataFile;
    private File categoryFile;
    private Scanner scanner = null;
    private boolean hasNoFileErrors;

    public FileManager(String dataFilePath, String categoryFilePath) {
        this.dataFile = new File(dataFilePath);
        this.categoryFile = new File(categoryFilePath);
    }
    public FileManager() {
        this(FileManager.DEFAULT_DATA_FILE_PATH, FileManager.DEFAULT_CATEGORY_FILE_PATH);
    }

    private boolean openFile(File file) {
        try {
            if (!file.exists()) {
                createDataFile(file);
            }
            assert file.exists();
            this.scanner = new Scanner(file);
            this.hasNoFileErrors = true;
            printDataSavedMessage(file);
        } catch (Exception e) {
            this.scanner = null;
            this.hasNoFileErrors = false;
            printDataLossWarning();
        }
        return this.hasNoFileErrors;
    }
    public boolean openExpenseFile() {
        return openFile(this.dataFile);
    }
    public boolean openCategoryFile() {
        return openFile(this.categoryFile);
    }
    public void saveExpenses(String data) {
        save(data, this.dataFile);
    }
    public void saveCategories(String data) {
        save(data, this.categoryFile);
    }

    private void printDataLossWarning() {
        UI.println("Errors! Your data will not be saved");
    }
    private void printDataSavedMessage(File file) {
        UI.println("Data file: " + file + " successfully created!");
    }


    private void createDataFile(File file) throws Exception {
        boolean hasDataDirectory = file.getParentFile().exists();
        boolean isDataDirectoryReady = hasDataDirectory || file.getParentFile().mkdirs();
        if (!isDataDirectoryReady) {
            throw new Exception();
        }
        assert file.getParentFile().exists();
        boolean isDataFileCreated = file.createNewFile();
        if (!isDataFileCreated) {
            throw new Exception();
        }
        assert file.exists();
    }

    public boolean hasNextLine() {
        return this.scanner.hasNext();
    }

    public String readNextLine() {
        return this.scanner.nextLine();
    }

    private void save(String data, File file) {
        try {
            if (!this.hasNoFileErrors) {
                printDataLossWarning();
                return;
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            printDataLossWarning();
        }
    }
}
