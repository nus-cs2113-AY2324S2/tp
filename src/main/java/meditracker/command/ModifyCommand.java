package meditracker.command;

import meditracker.DailyMedicationManager;
import meditracker.argument.Argument;
import meditracker.argument.ArgumentList;
import meditracker.argument.ArgumentName;
import meditracker.argument.DosageArgument;
import meditracker.argument.ExpirationDateArgument;
import meditracker.argument.IntakeFrequencyArgument;
import meditracker.argument.ListIndexArgument;
import meditracker.argument.NameArgument;
import meditracker.argument.QuantityArgument;
import meditracker.argument.RemarksArgument;
import meditracker.exception.ArgumentNotFoundException;
import meditracker.medication.Medication;
import meditracker.medication.MedicationManager;
import meditracker.ui.Ui;

import java.util.Map;

/**
 * The ModifyCommand class represents a command to modify an existing medication.
 * It extends the Command class.
 */
public class ModifyCommand extends Command {
    public final ArgumentList argumentList = new ArgumentList(
            new ListIndexArgument(false),
            new NameArgument(true),
            new QuantityArgument(true),
            new DosageArgument(true),
            new ExpirationDateArgument(true),
            new IntakeFrequencyArgument(true),
            new RemarksArgument(true)
    );
    private final Map<ArgumentName, String> parsedArguments;

    /**
     * Constructs a ModifyCommand object with the specified arguments.
     * @param arguments The arguments containing medication information to be parsed.
     */
    public ModifyCommand(String arguments) throws ArgumentNotFoundException {
        parsedArguments = argumentList.parse(arguments);
    }

    /**
     * Executes the modify command.
     * This method modifies an existing Medication object using the provided information in the medication list.
     * It also displays a message confirming the modification of the medication.
     *
     * @param medicationManager      The MedicationList object representing the list of medications.
     * @param dailyMedicationManager The DailyMedicationManager object representing the list of daily medications.
     * @param ui                     The Ui object used to interact with the user interface.
     */
    @Override
    public void execute(MedicationManager medicationManager,
                        DailyMedicationManager dailyMedicationManager,
                        Ui ui) {
        String listIndexString = parsedArguments.get(ArgumentName.LIST_INDEX);
        int listIndex = Integer.parseInt(listIndexString);

        Medication medication = medicationManager.getMedication(listIndex);
        for (Argument argument: argumentList.getArguments()) {
            ArgumentName argumentName = argument.getName();
            String argumentValue = parsedArguments.get(argumentName);

            switch (argumentName) {
            case DOSAGE:
                medication.setDosage(argumentValue);
                break;
            case EXPIRATION_DATE:
                medication.setExpiryDate(argumentValue);
                break;
            case INTAKE_FREQUENCY:
                medication.setIntakeFreq(argumentValue);
                break;
            case LIST_INDEX:
                continue;
            case NAME:
                medication.setName(argumentValue);
                break;
            case QUANTITY:
                medication.setQuantity(argumentValue);
                break;
            case REMARKS:
                medication.setRemarks(argumentValue);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + argumentName);
            }
        }

        ui.showModifyCommandMessage();
    }
}
