package meditracker.command;

import meditracker.DailyMedication;
import meditracker.DailyMedicationManager;
import meditracker.exception.ArgumentNotFoundException;
import meditracker.medication.MedicationManager;
import meditracker.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TakeCommandTest {
    @Test
    void execute_inOrderArgument_expectDailyMedicationTaken() throws ArgumentNotFoundException {
        MedicationManager medicationManager = new MedicationManager();
        DailyMedicationManager dailyMedicationManager = new DailyMedicationManager(medicationManager);
        DailyMedication dailyMedication = new DailyMedication("Medication_A");
        dailyMedicationManager.addDailyMedication(dailyMedication);

        String inputString = "take -l 1";
        TakeCommand command = new TakeCommand(inputString);
        Ui ui = new Ui();
        command.execute(null, dailyMedicationManager, ui);

        assertTrue(dailyMedication.isTaken());
    }
}
