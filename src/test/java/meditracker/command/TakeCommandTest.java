package meditracker.command;

import meditracker.DailyMedication;
import meditracker.DailyMedicationManager;
import meditracker.exception.ArgumentNotFoundException;
import meditracker.medication.MedicationManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TakeCommandTest {
    @Test
    void execute_inOrderArgument_expectDailyMedicationTaken() throws ArgumentNotFoundException {
        MedicationManager medicationManager = new MedicationManager();
        DailyMedication dailyMedication = new DailyMedication("Medication_A");
        int size = DailyMedicationManager.getTotalDailyMedication();
        DailyMedicationManager.addDailyMedication(dailyMedication);

        String inputString = String.format("take -l %d", size + 1);
        TakeCommand command = new TakeCommand(inputString);
        command.execute(null);

        assertTrue(dailyMedication.isTaken());
    }
}
