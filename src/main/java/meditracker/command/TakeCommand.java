package meditracker.command;

import meditracker.DailyMedicationManager;
import meditracker.argument.ArgumentList;
import meditracker.argument.ArgumentName;
import meditracker.argument.ListIndexArgument;
import meditracker.exception.ArgumentNotFoundException;
import meditracker.medication.MedicationManager;
import meditracker.ui.Ui;

import java.util.Map;

public class TakeCommand extends Command {
    public final ArgumentList argumentList = new ArgumentList(
            new ListIndexArgument(false)
    );
    private final Map<ArgumentName, String> parsedArguments;

    /**
     * Constructs a TakeCommand object with the specified arguments.
     * @param arguments The arguments containing information to be parsed.
     */
    public TakeCommand(String arguments) throws ArgumentNotFoundException {
        parsedArguments = argumentList.parse(arguments);
    }

    /**
     * Executes the take command.
     * This method marks an existing Medication object using the provided list index.
     * It also displays a message confirming the modification of the daily medication status.
     *
     * @param medicationManager      The MedicationManager object representing the list of medications.
     * @param dailyMedicationManager The DailyMedicationManager object representing the list of daily medications.
     * @param ui                     The Ui object used to interact with the user interface.
     */
    @Override
    public void execute(MedicationManager medicationManager,
                        DailyMedicationManager dailyMedicationManager,
                        Ui ui) {
        String listIndexString = parsedArguments.get(ArgumentName.LIST_INDEX);
        int listIndex = Integer.parseInt(listIndexString);
        dailyMedicationManager.takeDailyMedication(listIndex);
        ui.showTakeCommandMessage();
    }
}
