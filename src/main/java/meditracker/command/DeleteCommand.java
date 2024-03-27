package meditracker.command;

import meditracker.argument.ArgumentList;
import meditracker.argument.ArgumentName;
import meditracker.argument.ListIndexArgument;
import meditracker.exception.ArgumentNotFoundException;
import meditracker.exception.DuplicateArgumentFoundException;
import meditracker.medication.MedicationManager;
import meditracker.ui.Ui;

import java.util.Map;

/**
 * The DeleteCommand class represents a command to delete an existing medication.
 * It extends the Command class.
 */
public class DeleteCommand extends Command {
    public final ArgumentList argumentList = new ArgumentList(
            new ListIndexArgument(false)
    );
    private final Map<ArgumentName, String> parsedArguments;

    /**
     * Constructs a DeleteCommand object with the specified arguments.
     *
     * @param arguments The arguments containing medication information to be parsed.
     * @throws ArgumentNotFoundException Argument flag specified not found
     * @throws DuplicateArgumentFoundException Duplicate argument flag found
     */
    public DeleteCommand(String arguments)
            throws ArgumentNotFoundException, DuplicateArgumentFoundException {
        parsedArguments = argumentList.parse(arguments);
    }

    /**
     * Executes the delete command.
     * This method deletes an existing Medication object using the provided information in the medication list.
     * It also displays a message confirming the deletion of the medication.
     *
     * @param medicationManager      The MedicationList object representing the list of medications.
     */
    @Override
    public void execute(MedicationManager medicationManager) {
        String listIndexString = parsedArguments.get(ArgumentName.LIST_INDEX);
        int listIndex = Integer.parseInt(listIndexString);
        medicationManager.removeMedication(listIndex);

        // TODO: remove medication from DailyMedicationManager as well.

        Ui.showDeleteCommandMessage();
    }
}
