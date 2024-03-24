
package commands.reflectcommands;

import commands.Command;
import exceptions.ReflectException;
import reflection.ReflectionManager;

/**
 * A command implementation for listing favorite reflection items.
 */
public class ListFavouriteReflectionsCommand implements Command {
    private ReflectionManager reflectionManager;

    /**
     * Constructs a ListFavouritesCommand.
     *
     * @param reflectionManager The ReflectionManager instance to be used for listing favorite items.
     * @param reflectionCommandArgs The string representing the reflection command arguments.
     * @throws ReflectException if the command arguments is not empty.
     */
    public ListFavouriteReflectionsCommand(ReflectionManager reflectionManager, String reflectionCommandArgs)
            throws ReflectException {

        if (!reflectionCommandArgs.isBlank()) {
            throw new ReflectException("Additional parameters for 'reflect list' command are not allowed.");
        }

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
