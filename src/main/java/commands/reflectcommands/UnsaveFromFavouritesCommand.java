
package commands.reflectcommands;

import commands.Command;
import exceptions.ReflectException;
import reflection.ReflectionManager;

/**
 * A command implementation for unsaving a reflection item from favorites.
 */
public class UnsaveFromFavouritesCommand implements Command {
    private ReflectionManager reflectionManager;
    private int reflectionId;

    /**
     * Constructs a UnsaveFromFavouritesCommand.
     *
     * @param reflectionManager     The ReflectionManager instance to be used for saving the reflection item.
     * @param reflectionCommandArgs The ID of the reflection item to be saved.
     * @throws ReflectException if the provided reflection ID is invalid.
     */
    public UnsaveFromFavouritesCommand(ReflectionManager reflectionManager, String reflectionCommandArgs)
            throws ReflectException {

        this.reflectionManager = reflectionManager;
        try {
            reflectionId = Integer.parseInt(reflectionCommandArgs);
        } catch (NumberFormatException e) {
            throw new ReflectException("Key in valid favourite reflection ID. Only numbers are allowed.");
        }
    }

    /**
     * Executes the command to save the reflection item to favorites.
     *
     * @throws ReflectException if an error occurs during the saving process.
     */
    @Override
    public void execute() throws ReflectException {
        reflectionManager.unsaveReflectionQuestion(reflectionId);
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
