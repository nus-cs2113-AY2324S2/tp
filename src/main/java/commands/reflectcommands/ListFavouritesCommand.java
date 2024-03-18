
package commands.reflectcommands;

import commands.Command;
import reflection.ReflectionManager;

/**
 * A command implementation for listing favorite reflection items.
 */
public class ListFavouritesCommand implements Command {
    private ReflectionManager reflectionManager;

    /**
     * Constructs a ListFavouritesCommand.
     *
     * @param reflectionManager The ReflectionManager instance to be used for listing favorite items.
     */
    public ListFavouritesCommand(ReflectionManager reflectionManager) {
        this.reflectionManager = reflectionManager;
    }

    /**
     * Executes the command to print the list of favorite reflection items.
     */
    @Override
    public void execute() {
        reflectionManager.printFavourites();
    }

    /**
     * Determines if this command represents an exit action.
     *
     * @return Always returns false, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
