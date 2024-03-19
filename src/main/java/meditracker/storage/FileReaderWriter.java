package meditracker.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import meditracker.exception.FileReadWriteException;
import meditracker.logging.MediLogger;
import meditracker.medication.MedicationManager;

//@@author annoy-o-mus
/**
 * A static class to handle the reading and writing to the filesystem.
 * Currently, only supports the default path and file.
 */
public class FileReaderWriter {
    private static String jsonDataFileName = "MediTrackerData.json";
    private static String jsonDataFolderName = "data";

    private static String getJsonDataFileName() {
        return jsonDataFileName;
    }

    private static String getJsonDataFolderName() {
        return jsonDataFolderName;
    }

    private static String getFullJsonDataFilePath() {
        return jsonDataFolderName + "/" + jsonDataFileName;
    }

    /**
     * Creates new directories to allow for writing of MediTracker data to the save file.
     * Currently, only implements the default path and file.
     *
     * @throws FileReadWriteException When there is any issue creating the directories.
     */
    private static void initialiseDirectory() throws FileReadWriteException {
        File directory = null;

        // Solution adapted from: https://stackoverflow.com/a/3634879
        try {
            directory = new File(getJsonDataFolderName());
        } catch (NullPointerException e) {
            throw new FileReadWriteException("Unable to create folder: Folder name to contain the JSON file is null");
        }

        try {
            directory.mkdirs();
        } catch (SecurityException e) {
            throw new FileReadWriteException("Unable to create directories. Please make sure that the "
                    + "directory has the appropriate permissions for Meditracker to write to.");
        }
    }

    /**
     * Creates a JSON save file and writes.
     * Also attempts to reinitialise the directory in case of a first-time save.
     *
     * @return The File object that corresponds to the write file.
     * @throws FileReadWriteException If the file is unable to be created due to system issues.
     */
    public static File createJsonSaveFile() throws FileReadWriteException {
        String fullFilePath = getFullJsonDataFilePath();
        initialiseDirectory();
        File fileToWrite = new File(fullFilePath);
        try {
            // TODO: Introduce a more robust way (rename, create then delete - Done by another function)
            // TODO: Also to take into account empty file for first run.
            fileToWrite.delete();
            fileToWrite.createNewFile();
            return fileToWrite;
        } catch (IOException e) {
            throw new FileReadWriteException("IO Error: Unable to write to JSON File");
        } catch (SecurityException e) {
            throw new FileReadWriteException("Unable to create save JSON file. Please make sure that "
                    + "the file has the appropriate permissions for MediTracker to write to.");
        }
    }

    /**
     * Reads the JSON file to load and populate the MediTracker.
     * If the file is not found, a warning will be thrown to alert the user, and the program
     * will execute without the saved data (fresh state).
     */
    public static void loadMediTrackerData() {
        String fullFilePath = getFullJsonDataFilePath();
        File fileToRead = new File(fullFilePath);

        try {
            Scanner fileReaderScanner = new Scanner(fileToRead);
        } catch (FileNotFoundException e) {
            Logger logger = MediLogger.getMediLogger();
            logger.warning("Unable to find the file " + fullFilePath + " to read from. "
                    + "Program will run with no data loaded.");
        }
        // To be implemented: Actual reading and loading of the JSON data
    }

    /**
     * Saves the medication information found in `MedicationManager`.
     *
     * @param medManager The instance of the MedicationManager that contains the Medication information.
     */
    public static void saveMediTrackerData(MedicationManager medManager) {
        try {
            File fileToWrite = createJsonSaveFile();
            JsonExporter.saveMedicationDataToJson(medManager, fileToWrite);
            //TODO: Delete the renamed save file by `createJsonSaveFile`
        } catch (FileReadWriteException e) {
            System.out.println(e.getMessage());
        }
    }
}
