package commands.reflectcommands;

import commands.Command;
import exceptions.ReflectException;
import reflection.ReflectionManager;

/**
 * Represents a command to display the help menu for reflection commands.
 */
public class ReflectionHelpCommand implements Command {
    private ReflectionManager reflectionManager;

    /**
     * Constructs a ReflectionHelpCommand with the provided ReflectionManager.
     *
     * @param reflectionManager The ReflectionManager to be used.
     * @param reflectionCommandArgs The string representing the reflection command arguments.
     * @throws ReflectException if the command arguments is not empty.
     */
    public ReflectionHelpCommand(ReflectionManager reflectionManager, String reflectionCommandArgs)
            throws ReflectException {

        if (!reflectionCommandArgs.isBlank()) {
            throw new ReflectException("Additional parameters for 'reflect help' command are not allowed.");
        }

        this.reflectionManager = reflectionManager;
    }

    /**
     * Executes the command to print the help menu.
     */
    @Override
    public void execute() {
        reflectionManager.printHelpMenu();
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return false, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
