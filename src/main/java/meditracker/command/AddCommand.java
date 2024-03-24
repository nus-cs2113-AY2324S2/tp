package meditracker.command;

import meditracker.DailyMedication;
import meditracker.DailyMedicationManager;
import meditracker.exception.ArgumentNotFoundException;
import meditracker.medication.Medication;
import meditracker.medication.MedicationManager;
import meditracker.ui.Ui;

import meditracker.argument.ArgumentName;
import meditracker.argument.ArgumentList;
import meditracker.argument.RepeatArgument;
import meditracker.argument.DosageAfternoonArgument;
import meditracker.argument.DosageMorningArgument;
import meditracker.argument.DosageEveningArgument;
import meditracker.argument.NameArgument;
import meditracker.argument.QuantityArgument;
import meditracker.argument.DosageArgument;
import meditracker.argument.ExpirationDateArgument;
import meditracker.argument.IntakeFrequencyArgument;
import meditracker.argument.RemarksArgument;

import java.util.Map;

/**
 * The AddCommand class represents a command to add a new medication.
 * It extends the Command class.
 */
public class AddCommand extends Command {

    /**
     * The argumentList contains all the arguments needed for adding a medication.
     */
    public final ArgumentList argumentList = new ArgumentList(
            new NameArgument(false),
            new QuantityArgument(false),
            new DosageArgument(false),
            new DosageMorningArgument(true),
            new DosageAfternoonArgument(true),
            new DosageEveningArgument(true),
            new ExpirationDateArgument(false),
            new IntakeFrequencyArgument(false),
            new RepeatArgument(true),
            new RemarksArgument(true)
    );

    private final Map<ArgumentName, String> parsedArguments;

    private String medicationName;
    private double medicationQuantity;
    private double medicationDosage;
    private double medicationDosageMorning;
    private double medicationDosageAfternoon;
    private double medicationDosageEvening;
    private String expiryDate;
    private String intakeFreq;
    private String remarks;
    private String repeat;

    /**
     * Constructs an AddCommand object with the specified arguments.
     *
     * @param arguments The arguments containing medication information to be parsed.
     * @throws ArgumentNotFoundException if a required argument is not found.
     */
    public AddCommand(String arguments) throws ArgumentNotFoundException {
        parsedArguments = argumentList.parse(arguments);
    }

    /**
     * Executes the add command.
     * This method creates a new Medication object using the provided information and adds it to the medication list.
     * It also displays a message confirming the addition of the medication.
     *
     * @param medicationManager      The MedicationManager object representing the list of medications.
     * @param dailyMedicationManager The DailyMedicationManager object representing the list of daily medications.
     * @throws NullPointerException   if any of the required objects are null.
     * @throws NumberFormatException  if there is an error in parsing numeric values.
     */
    @Override
    public void execute(MedicationManager medicationManager,
                        DailyMedicationManager dailyMedicationManager) throws NullPointerException,
            NumberFormatException {
        setMedicineAttributes();
        Medication medication = new Medication(medicationName, medicationQuantity, medicationDosage,
                medicationDosageMorning, medicationDosageAfternoon, medicationDosageEvening, expiryDate,
                intakeFreq, repeat, remarks);

        DailyMedication dailyMedication = new DailyMedication(medicationName);
        medicationManager.addMedication(medication);
        dailyMedicationManager.addDailyMedication(dailyMedication);
        assertionTest(medicationManager, dailyMedicationManager);
        Ui.showAddCommandMessage();
    }

    /**
     * Sets the medication attributes based on parsed arguments.
     *
     * @throws NumberFormatException if there is an error in parsing numeric values.
     * @throws NullPointerException  if any of the required arguments are null.
     */
    private void setMedicineAttributes() throws NumberFormatException, NullPointerException {
        medicationName = parsedArguments.get(ArgumentName.NAME);
        expiryDate = parsedArguments.get(ArgumentName.EXPIRATION_DATE);
        intakeFreq = parsedArguments.get(ArgumentName.INTAKE_FREQUENCY);
        remarks = parsedArguments.get(ArgumentName.REMARKS);
        repeat = parsedArguments.get(ArgumentName.REPEAT);

        String medicationQuantity = parsedArguments.get(ArgumentName.QUANTITY);
        String medicationDosage = parsedArguments.get(ArgumentName.DOSAGE);
        String medicationDosageMorning = parsedArguments.get(ArgumentName.DOSAGE_MORNING);
        String medicationDosageAfternoon = parsedArguments.get(ArgumentName.DOSAGE_AFTERNOON);
        String medicationDosageEvening = parsedArguments.get(ArgumentName.DOSAGE_EVENING);

        parseStringToValues(medicationQuantity, medicationDosage, medicationDosageMorning, medicationDosageAfternoon,
                medicationDosageEvening);
    }

    /**
     * Performs assertion tests for medication and daily medication managers.
     *
     * @param medicationManager      The MedicationManager object representing the list of medications.
     * @param dailyMedicationManager The DailyMedicationManager object representing the list of daily medications.
     */
    private void assertionTest(MedicationManager medicationManager, DailyMedicationManager dailyMedicationManager) {
        assert medicationManager.getTotalMedications() != 0 : "Total medications in medication " +
                "manager should not be 0!";
        assert dailyMedicationManager.getTotalDailyMedication() != 0 : "Total medications in daily medication " +
                "manager should not be 0!";
    }

    /**
     * Parses string values to double for medication attributes.
     *
     * @param medicationQuantity      The quantity of the medication.
     * @param medicationDosage        The dosage of the medication.
     * @param medicationDosageMorning The morning dosage of the medication.
     * @param medicationDosageAfternoon The afternoon dosage of the medication.
     * @param medicationDosageEvening The evening dosage of the medication.
     * @throws NumberFormatException if there is an error in parsing numeric values.
     * @throws NullPointerException  if any of the required arguments are null.
     */
    private void parseStringToValues(String medicationQuantity, String medicationDosage,
                                     String medicationDosageMorning,
                                     String medicationDosageAfternoon, String medicationDosageEvening)
            throws NumberFormatException, NullPointerException {

        this.medicationQuantity = Double.parseDouble(medicationQuantity);
        this.medicationDosage = Double.parseDouble(medicationDosage);

        if (medicationDosageMorning != null) {
            this.medicationDosageMorning = Double.parseDouble(medicationDosageMorning);
        }
        if (medicationDosageAfternoon != null) {
            this.medicationDosageAfternoon = Double.parseDouble(medicationDosageAfternoon);
        }
        if (medicationDosageEvening != null) {
            this.medicationDosageEvening = Double.parseDouble(medicationDosageEvening);
        }
    }
}
