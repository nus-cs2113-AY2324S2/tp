package meditracker.command;

import meditracker.DailyMedicationManager;
import meditracker.medication.MedicationManager;

public abstract class Command {
    private MedicationManager medicationManager;

    /**
     * Executes the command
     *
     * @param medicationManager      The MedicationList object representing the list of medications.
     * @param dailyMedicationManager The DailyMedicationManager object representing the list of daily medications.
     */
    public abstract void execute(MedicationManager medicationManager,
                                 DailyMedicationManager dailyMedicationManager);

    /**
     * Returns the boolean to exit the program.
     *
     * @return False which continues program.
     */
    public boolean isExit() {
        return false;
    }
}
