package meditracker;

import meditracker.command.AddCommand;
import meditracker.exception.ArgumentNotFoundException;
import org.junit.jupiter.api.Test;
import meditracker.exception.MediTrackerException;
import meditracker.medication.MedicationManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {

    // 3 part format
    // methodBeingTested_conditionToTest_expectedOutcome
    @Test
    void execute_addCommand_expectOneMedication() throws MediTrackerException, ArgumentNotFoundException {
        // setup lines
        String inputString = "add -n Medication_A -q 60 -d 500 -e 01/07/25 -f morning -r cause_dizziness";
        MedicationManager medicationManager = new MedicationManager();
        AddCommand command = new AddCommand(inputString);
        command.execute(medicationManager);

        // actual test
        assertEquals(1, medicationManager.getTotalMedications());
    }
}
