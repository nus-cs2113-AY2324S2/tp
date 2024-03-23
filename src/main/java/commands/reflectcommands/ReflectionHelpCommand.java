package commands.reflectcommands;

import commands.Command;
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
     */
    public ReflectionHelpCommand(ReflectionManager reflectionManager) {
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
