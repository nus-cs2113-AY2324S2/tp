package meditracker.command;

import meditracker.argument.ArgumentList;
import meditracker.argument.ArgumentName;
import meditracker.argument.ListIndexArgument;
import meditracker.exception.ArgumentNotFoundException;
import meditracker.medication.MedicationManager;
import meditracker.ui.Ui;

import java.util.Map;

public class DeleteCommand extends Command {
    public final ArgumentList argumentList = new ArgumentList(
            new ListIndexArgument(false)
    );
    private final Map<ArgumentName, String> parsedArguments;

    /**
     * Constructs a DeleteCommand object with the specified arguments.
     * @param arguments The arguments containing medication information to be parsed.
     */
    public DeleteCommand(String arguments) throws ArgumentNotFoundException {
        parsedArguments = argumentList.parse(arguments);
    }

    @Override
    public void execute(MedicationManager medicationManager, Ui ui) {
        String listIndexString = parsedArguments.get(ArgumentName.LIST_INDEX);
        int listIndex = Integer.parseInt(listIndexString);
        medicationManager.removeMedication(listIndex);

        ui.showDeleteCommandMessage();
    }
}
