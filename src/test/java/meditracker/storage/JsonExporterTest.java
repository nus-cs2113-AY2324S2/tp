package meditracker.storage;

import meditracker.exception.FileReadWriteException;
import meditracker.medication.Medication;
import meditracker.medication.MedicationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

//@@author annoy-o-mus

/**
 * A class to test the JSON export functionality.
 */
public class JsonExporterTest {
    private static MedicationManager medManager = new MedicationManager();
    private static File exportedJsonFile = null;

    /**
     * Pre-populate the medication manager with some medications that we need to simulate data exporting. They can be
     * potentially errornous (i.e. empty field where they are not supposed to be).
     */
    @BeforeAll
    public static void initiateMedicationManager() {
        Medication med1 = new Medication(
                "Test Valid Medication 1",
                "69",
                "1",
                "23/11/24",
                "2",
                "No Remarks"
        );

        Medication med2 = new Medication(
                "Test Valid Medication 2",
                "10000",
                "10.0",
                "01/01/25",
                "4",
                ""
        );

        Medication med3 = new Medication(
                "Invalid Medication 3",
                "",
                "",
                "",
                "",
                ""
        );

        Medication med4 = new Medication(
                "Invalid Medication 4",
                "999",
                "3.1425926535",
                "",
                "",
                "null"
        );

        medManager.addMedication(med1);
        medManager.addMedication(med2);
        medManager.addMedication(med3);
        medManager.addMedication(med4);
    }

    @BeforeEach
    public void setUpWriteFile() {

        try {
            exportedJsonFile = FileReaderWriter.createJsonSaveFile();
        } catch (FileReadWriteException e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void cleanup() {
        if (exportedJsonFile != null && exportedJsonFile.exists()) {
            exportedJsonFile.delete();
        }
    }

    @Test
    public void placeHolder() {
        JsonExporter.saveMedicationDataToJson(medManager, exportedJsonFile);
    }
}
