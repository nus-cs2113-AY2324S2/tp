package commands.reflectcommands;

import commands.Command;
import exceptions.ReflectException;
import reflection.ReflectionManager;

public class SaveToFavouritesCommand implements Command {
    private ReflectionManager reflectionManager;
    private int reflectionId;
    public SaveToFavouritesCommand(ReflectionManager reflectionManager, String reflectionCommandArgs)
            throws ReflectException {

        this.reflectionManager = reflectionManager;
        try {
            reflectionId = Integer.parseInt(reflectionCommandArgs);
        } catch (NumberFormatException e) {
            throw new ReflectException("Key in valid favourite reflection ID, between 1 and 5");
        }
    }
    @Override
    public void execute() throws ReflectException {
        reflectionManager.saveReflectionQuestion(reflectionId);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
