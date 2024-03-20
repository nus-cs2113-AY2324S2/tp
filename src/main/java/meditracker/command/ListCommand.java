package meditracker.command;

import meditracker.argument.ArgumentList;
import meditracker.argument.ArgumentName;
import meditracker.argument.ListTypeArgument;
import meditracker.exception.ArgumentNotFoundException;
import meditracker.medication.MedicationManager;
import meditracker.ui.Ui;

import java.util.Map;

/**
 * The ListCommand class represents a command to list the medications.
 * It extends the Command class.
 */
public class ListCommand extends Command {

    public ArgumentList argumentList = new ArgumentList(
            new ListTypeArgument(false));

    private final Map<ArgumentName, String> parsedArguments;

    public ListCommand(String arguments) throws ArgumentNotFoundException {
        parsedArguments = argumentList.parse(arguments);
    }

    /**
     * Executes the list command
     * @param medicationManager List of medicines.
     * @param ui                Ui object.
     */
    @Override
    public void execute(MedicationManager medicationManager, Ui ui) {
        String listTypeString = parsedArguments.get(ArgumentName.LIST_TYPE);
        
        switch (listTypeString) {
        case "all":
            medicationManager.printAllMedications();
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + listTypeString);
        }
    }
}
