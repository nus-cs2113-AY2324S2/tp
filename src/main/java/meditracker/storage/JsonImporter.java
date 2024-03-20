package meditracker.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import meditracker.logging.MediLogger;
import meditracker.medication.Medication;
import meditracker.medication.MedicationManager;

/**
 * A class to handle the importing of raw json file data and process them.
 * Populates the various Managers involved in the handling of the program.
 */
public class JsonImporter {
    private static Logger logger = MediLogger.getMediLogger();

    /**
     * Populates the MedicationManager with the Medication information stored inside a JSONArray.
     * If there is corrupted data within the Medication entry, the whole medication entry will not be registered with
     * the MedicationManager.
     *
     * @param medicationList A JSONArray containing a list of Medication information in JSON format.
     * @param medManager The instance of MedicationManager.
     */
    private static void populateMedicationManager (JSONArray medicationList, MedicationManager medManager) {
        int numberOfMedications = medicationList.length();

        for (int i = 0; i < numberOfMedications; i ++ ){
            try {
                JSONObject  medicationInfoJson = medicationList.getJSONObject(i);
                Medication med = new Medication(
                        medicationInfoJson.getString("name"),
                        medicationInfoJson.getString("quantity"),
                        medicationInfoJson.getString("dosage"),
                        medicationInfoJson.getString("expiryDate"),
                        medicationInfoJson.getString("intakeFrequency"),
                        medicationInfoJson.getString("remarks")
                );
                medManager.addMedication(med);
            } catch (JSONException e) {
                logger.warning("Medication Read Error: " + e.getMessage());
                logger.warning("Medication Not added to the Medication Manager.");
            }
        }
    }

    /**
     * Reads from the JSON file and populates the various Managers with various information.
     * If the JSON file could not be found or if the structure is corrupted and could not be read,
     * a warning will be thrown to the user and the program will run as if it is the first time running.
     *
     * @param mediTrackerJsonPath The Path object specifying the path to the MediTracker save data.
     * @param medManager The instance of MedicationManager.
     */
    public static void processMediTrackerJsonFile (Path mediTrackerJsonPath, MedicationManager medManager) {
        // Choice of reader adapted from
        // https://www.stackchief.com/blog/FileReader%20vs%20BufferedReader%20vs%20Scanner%20%7C%20Java
        // and https://stackoverflow.com/a/20838298
        List<String> jsonFileData = null;

        try {
            jsonFileData = Files.readAllLines(mediTrackerJsonPath);
        } catch (IOException e) {
            logger.warning("Unable to read from the JSON save file. Defaulting to empty state.");
            return;
        }

        if (jsonFileData.isEmpty()) {
            logger.warning("Empty JSON file.");
            return;
        }
        if (jsonFileData.size() >1) {
            logger.warning("JSON file should only contain one line. Multiple lines detected "
                    + "in JSON file. Will only take the first line.");
        }
        String jsonStringData = jsonFileData.get(0);

        // Solution on reading and parsing a JSON file adapted from
        // https://stackoverflow.com/q/10926353
        try {
            JSONObject rawJsonData = new JSONObject(jsonStringData);
            JSONArray medicationList = rawJsonData.getJSONArray("medicationList");
            populateMedicationManager(medicationList, medManager);
        } catch (JSONException e) {
            logger.warning("JSON Read Error: " + e.getMessage());
            logger.warning("JSON Save Data not read and processed. Going with Empty state.");
        }
    }
}
