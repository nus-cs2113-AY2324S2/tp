package meditracker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DailyMedicationManagerTest {
    @Test
    public void addDailyMedication_genericDailyMedication_dailyMedicationAdded() {
        DailyMedicationManager.clearDailyMedication();
        DailyMedication dailyMedication = new DailyMedication("TestMedication");
        DailyMedicationManager.addDailyMedication(dailyMedication);

        DailyMedicationManager.printMedications();

        int actualIndex = 1; // 1-based indexing
        DailyMedication dailyMedicationTest = DailyMedicationManager.getDailyMedication(actualIndex);
        assertEquals(dailyMedication.toString(), dailyMedicationTest.toString());
    }

    @Test
    public void takeDailyMedication_genericDailyMedication_dailyMedicationTaken() {
        DailyMedicationManager.clearDailyMedication();
        DailyMedication dailyMedication = new DailyMedication("TestMedication");
        assertFalse(dailyMedication.isTaken());
        DailyMedicationManager.addDailyMedication(dailyMedication);

        int actualIndex = 1; // 1-based indexing
        DailyMedicationManager.takeDailyMedication(actualIndex);
        DailyMedication dailyMedicationTest = DailyMedicationManager.getDailyMedication(actualIndex);
        assertTrue(dailyMedicationTest.isTaken());
    }

    @Test
    public void untakeDailyMedication_genericDailyMedication_dailyMedicationNotTaken() {
        DailyMedicationManager.clearDailyMedication();
        DailyMedication dailyMedication = new DailyMedication("TestMedication");
        dailyMedication.take();
        assertTrue(dailyMedication.isTaken());
        DailyMedicationManager.addDailyMedication(dailyMedication);

        int actualIndex = 1; // 1-based indexing
        DailyMedicationManager.untakeDailyMedication(actualIndex);
        DailyMedication dailyMedicationTest = DailyMedicationManager.getDailyMedication(actualIndex);
        assertFalse(dailyMedicationTest.isTaken());
    }
}
