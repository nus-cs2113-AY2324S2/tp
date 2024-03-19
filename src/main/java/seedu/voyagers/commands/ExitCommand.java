package seedu.voyagers.commands;

import seedu.voyagers.Storage;
import seedu.voyagers.TripList;
import seedu.voyagers.Ui;

public class ExitCommand extends Command{
    public ExitCommand(){
    }

    /**
     * Executes the command to exit the program.
     * Prints the goodbye message.
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     */
    public void execute(TripList tasks, Ui ui, Storage storage){
        ui.showExit();
    }

    @Override
    public boolean isExit(){
        return true;
    }
}
