package meditracker.storage;

import meditracker.medication.Medication;
import meditracker.medication.MedicationManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//@@author annoy-o-mus
/**
 * A Class that converts data to JSON and write to the target file.
 */
class JsonExporter {
    /**
     * Converts the information inside the Medication object into a JSON Object.
     *
     * @param medInfo The Medication object.
     * @return The JSON object representing the Medication object.
     */
    static JSONObject convertMedicationInfoToJson(Medication medInfo) {
        JSONObject medObject = new JSONObject();

        medObject.put("name", medInfo.getName());
        medObject.put("quantity", medInfo.getQuantity());
        medObject.put("dosage", medInfo.getDosage());
        medObject.put("expiryDate", medInfo.getExpiryDate());
        medObject.put("intakeFrequency", medInfo.getIntakeFreq());
        medObject.put("remarks", medInfo.getRemarks());

        return medObject;
    }

    /**
     * Save all the medication information into the JSON file.
     *
     * @param medManager The instance of the MedicationManager
     * @param fileToWrite The File object containing the abstract pathname of the JSON file to write to.
     */
    static void saveMedicationDataToJson(MedicationManager medManager, File fileToWrite) {
        // Solution on how to write to a JSON file is adapted from:
        // https://stackoverflow.com/a/62947435
        // and https://javadoc.io/doc/org.json/json/latest/org/json/JSONObject.html
        // and https://javadoc.io/doc/org.json/json/latest/org/json/JSONArray.html

        JSONObject root = new JSONObject();
        JSONArray medicationList = new JSONArray();

        int numberOfMedication = medManager.getTotalMedications();
        // Start with 1 since the `getMedication` method will be converting from 1-based to 0-based
        for (int i = 1; i <= numberOfMedication; i++) {
            Medication medicationInfo = medManager.getMedication(i);
            medicationList.put(convertMedicationInfoToJson(medicationInfo));
        }
        root.put("medicationList", medicationList);

        try {
            FileWriter f = new FileWriter(fileToWrite);
            f.write(root.toString());
            f.flush();
            f.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
