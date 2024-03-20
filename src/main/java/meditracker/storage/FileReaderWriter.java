package meditracker.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.logging.Logger;
import java.util.List;

import meditracker.exception.FileReadWriteException;
import meditracker.logging.MediLogger;
import meditracker.medication.MedicationManager;

//@@author annoy-o-mus
/**
 * A static class to handle the reading and writing to the filesystem.
 * Currently, only supports the default path and file.
 */
public class FileReaderWriter {
    private static Logger logger = MediLogger.getMediLogger();

    private static String jsonDataFileName = "MediTrackerData.json";
    private static String jsonDataFolderName = "data";
    private static String dailyMedicationFileName = "today.txt"; // To be changed in v2.0
    private static String dailyMedicationFolderName = "data/dailymed";

    private static String getJsonDataFolderName() {
        return jsonDataFolderName;
    }

    private static String getDailyMedicationFolderName() {
        return dailyMedicationFolderName;
    }

    private static String getFullJsonDataFilePath() {
        return jsonDataFolderName + "/" + jsonDataFileName;
    }

    private static String getFullDailyMedFilePath() {
        return dailyMedicationFolderName + "/" + dailyMedicationFileName;
    }

    /**
     * Creates new directories to allow for writing of MediTracker data to the save file.
     * Currently, only implements the default paths and files.
     *
     * @param folderName The diretory, including its parents, to initialise.
     * @throws FileReadWriteException When there is any issue creating the directories.
     */
    private static void initialiseDirectory(String folderName) throws FileReadWriteException {
        File directory = null;

        // Solution adapted from: https://stackoverflow.com/a/3634879
        try {
            directory = new File(folderName);
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
        initialiseDirectory(getJsonDataFolderName());
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
     *
     * @param medicationManager The instance of MedicationManager.
     */
    public static void loadMediTrackerData(MedicationManager medicationManager) {
        Path mediTrackerJsonPath = null;

        try {
            // https://stackoverflow.com/a/20838298
            mediTrackerJsonPath = FileSystems.getDefault().getPath(getFullJsonDataFilePath());
        } catch (InvalidPathException e) {
            logger.warning("Unable to find the file " + jsonDataFileName + " to read from. "
                    + "Program will run with no data loaded.");
        }

        if (mediTrackerJsonPath != null) {
            JsonImporter.processMediTrackerJsonFile(mediTrackerJsonPath, medicationManager);
        }
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

    /**
     * Loads the daily medication information from a fixed file data/dailymed/today.txt.
     * The loading functionality will be improved in v2.0.
     *
     * @param dailyMedData A list of type String for the daily medication data.
     * @throws FileReadWriteException if there is an issue creating the file.
     */
    public static void saveDailyMedicationData(List<String> dailyMedData) throws FileReadWriteException {
        initialiseDirectory(getDailyMedicationFolderName());

        // This part is similar to `createJsonSaveFile()`
        File fileToWrite = new File(getFullDailyMedFilePath());
        try {
            fileToWrite.delete();
            fileToWrite.createNewFile();
        } catch (IOException e) {
            throw new FileReadWriteException("IO Error: Unable to write to JSON File");
        } catch (SecurityException e) {
            throw new FileReadWriteException("Unable to create save JSON file. Please make sure that "
                    + "the file has the appropriate permissions for MediTracker to write to.");
        }

        //@@author annoy-o-mus-reused
        // Reused from https://stackoverflow.com/a/6548204
        // with minor modifications
        try {
            FileWriter f = new FileWriter(fileToWrite);
            for (String stringData : dailyMedData) {
                f.write(stringData + System.lineSeparator());
            }
            f.flush();
            f.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the daily medication information to a fixed file data/dailymed/today.txt.
     * The file creation is the same as `createJsonSaveFile` (to be abstracted).
     * The saving functionality will be improved in v2.0.
     *
     * @return A list of string with the daily medication data. null if the file could not be loaded.
     */
    public static List<String> loadDailyMedicationData() {
        try {
            Path dailyMedTextFile = FileSystems.getDefault().getPath(getFullDailyMedFilePath());
            return Files.readAllLines(dailyMedTextFile);
        } catch (IOException e) {
            logger.warning(e.getMessage());
            return null;
        }
    }
}
