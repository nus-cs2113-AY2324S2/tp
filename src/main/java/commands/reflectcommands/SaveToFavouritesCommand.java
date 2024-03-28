package commands.reflectcommands;

import commands.Command;
import exceptions.ReflectException;
import reflection.ReflectionManager;

/**
 * A command implementation for saving a reflection item to favorites.
 */
public class SaveToFavouritesCommand implements Command {
    private ReflectionManager reflectionManager;
    private int reflectionId;

    /**
     * Constructs a SaveToFavouritesCommand.
     *
     * @param reflectionManager     The ReflectionManager instance to be used for saving the reflection item.
     * @param reflectionCommandArgs The ID of the reflection item to be saved.
     * @throws ReflectException if the provided reflection ID is invalid.
     */
    public SaveToFavouritesCommand(ReflectionManager reflectionManager, String reflectionCommandArgs)
            throws ReflectException {

        this.reflectionManager = reflectionManager;
        try {
            reflectionId = Integer.parseInt(reflectionCommandArgs);
        } catch (NumberFormatException e) {
            throw new ReflectException("Key in valid favourite reflection ID, between 1 and 5");
        }
    }

    /**
     * Executes the command to save the reflection item to favorites.
     *
     * @throws ReflectException if an error occurs during the saving process.
     */
    @Override
    public void execute() throws ReflectException {
        reflectionManager.saveReflectionQuestion(reflectionId);
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
