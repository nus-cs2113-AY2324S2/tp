package seedu.meditracker.command;

import seedu.meditracker.exception.MediTrackerException;
import seedu.meditracker.medication.Medication;
import seedu.meditracker.medication.MedicationList;
import seedu.meditracker.ui.Ui;

/**
 * The AddCommand class represents a command to add a new medication.
 * It extends the Command class.
 */
public class AddCommand extends Command {

    private static final int MED_NAME = 1;
    private static final int MED_QTY = 3;
    private static final int MED_DOSAGE = 5;
    private static final int MED_EXP = 7;
    private static final int MED_FREQ = 9;
    private static final int MED_RMK = 11;

    private String medicationName;
    private String medicineQuantity;
    private String medicineDosage;
    private String expiryDate;
    private String intakeFreq;
    private String remarks;

    /**
     * Constructs an AddCommand object with the specified arguments.
     * @param arguments The arguments containing medication information to be parsed.
     */
    public AddCommand(String arguments) throws MediTrackerException {
        try {
            String[] medicineInformation = arguments.split(" ");
            medicationName = medicineInformation[MED_NAME];
            medicineQuantity = medicineInformation[MED_QTY];
            medicineDosage = medicineInformation[MED_DOSAGE];
            expiryDate = medicineInformation[MED_EXP];
            intakeFreq = medicineInformation[MED_FREQ];
            remarks = medicineInformation[MED_RMK];
        } catch (Exception e) {
            throw new MediTrackerException();
        }
    }

    /**
     * Executes the add command.
     * This method creates a new Medication object using the provided information and adds it to the medication list.
     * It also displays a message confirming the addition of the medication.
     * @param medicationList The MedicationList object representing the list of medications.
     * @param ui The Ui object used to interact with the user interface.
     */
    @Override
    public void execute(MedicationList medicationList, Ui ui) {
        Medication medication = new Medication(medicationName, medicineQuantity, medicineDosage, expiryDate, intakeFreq,
                remarks);
        medicationList.medications.add(medication);
        ui.showAddCommandMessage();
    }
}
