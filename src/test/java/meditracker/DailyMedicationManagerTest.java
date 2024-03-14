package meditracker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DailyMedicationManagerTest {
    @Test
    public void addDailyMedication_genericDailyMedication_dailyMedicationAdded() {
        DailyMedicationManager dailyMedicationManager = new DailyMedicationManager();
        DailyMedication dailyMedication = new DailyMedication("TestMedication");
        dailyMedicationManager.addDailyMedication(dailyMedication);

        int actualIndex = 1; // 1-based indexing
        DailyMedication dailyMedicationTest = dailyMedicationManager.getDailyMedication(actualIndex);
        assertEquals(dailyMedication.toString(), dailyMedicationTest.toString());
    }

    @Test
    public void takeDailyMedication_genericDailyMedication_dailyMedicationTaken() {
        DailyMedicationManager dailyMedicationManager = new DailyMedicationManager();
        DailyMedication dailyMedication = new DailyMedication("TestMedication");
        assertFalse(dailyMedication.isTaken());
        dailyMedicationManager.addDailyMedication(dailyMedication);

        int actualIndex = 1; // 1-based indexing
        dailyMedicationManager.takeDailyMedication(actualIndex);
        DailyMedication dailyMedicationTest = dailyMedicationManager.getDailyMedication(actualIndex);
        assertTrue(dailyMedicationTest.isTaken());
    }

    @Test
    public void untakeDailyMedication_genericDailyMedication_dailyMedicationNotTaken() {
        DailyMedicationManager dailyMedicationManager = new DailyMedicationManager();
        DailyMedication dailyMedication = new DailyMedication("TestMedication");
        dailyMedication.take();
        assertTrue(dailyMedication.isTaken());
        dailyMedicationManager.addDailyMedication(dailyMedication);

        int actualIndex = 1; // 1-based indexing
        dailyMedicationManager.untakeDailyMedication(actualIndex);
        DailyMedication dailyMedicationTest = dailyMedicationManager.getDailyMedication(actualIndex);
        assertFalse(dailyMedicationTest.isTaken());
    }
}
