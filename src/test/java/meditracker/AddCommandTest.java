package meditracker;

import meditracker.command.AddCommand;
import org.junit.jupiter.api.Test;
import meditracker.exception.MediTrackerException;
import meditracker.medication.MedicationManager;
import meditracker.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {

    // 3 part format
    // methodBeingTested_conditionToTest_expectedOutcome
    @Test
    void execute_addCommand_expectOneMedication() throws MediTrackerException {
        // setup lines
        String inputString = "add -n Medication_A -q 60_TAB -d 500mg -e 01/07/25 -f morning -r cause_dizziness";
        Ui ui = new Ui();
        MedicationManager medicationManager = new MedicationManager();
        DailyMedicationManager dailyMedicationManager = new DailyMedicationManager(medicationManager);
        AddCommand command = new AddCommand(inputString);
        command.execute(medicationManager, ui, dailyMedicationManager);

        // actual test
        assertEquals(1, medicationManager.getTotalMedications());
    }
}
