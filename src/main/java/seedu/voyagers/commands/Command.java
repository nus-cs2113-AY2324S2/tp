package seedu.voyagers.commands;

import seedu.voyagers.utils.Storage;
import seedu.voyagers.classes.TripList;
import seedu.voyagers.utils.Ui;


// Code taken from https://github.com/azamanis/ip

/**
 * Represents a command to be executed.
 */
public abstract class Command {

    protected String[] args;

    /**
     * Constructor for Command.
     * @param args the arguments for the command
     */
    public Command(String[] args){
        this.args = args;
    }

    public Command(){

    }

    /**
     * Executes the command.
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     */
    public abstract void execute(TripList tasks, Ui ui, Storage storage);

    /**
     * Returns true iff the command is an exit command.
     */
    public boolean isExit(){
        return false;
    }

    public String[] getArgs() {
        return args;
    }
}
