package meditracker.command;

import meditracker.exception.ArgumentNotFoundException;
import meditracker.exception.MediTrackerException;

/**
 * The Parser class parses user input commands into Command objects.
 */
public class CommandParser {

    /**
     * Parses a full command string into a Command object.
     *
     * @param fullCommand The full command string entered by the user.
     * @return A Command object corresponding to the parsed command.
     * @throws MediTrackerException      If an error occurs during parsing.
     * @throws ArgumentNotFoundException If a required argument is not found.
     * @throws NullPointerException      If the fullCommand is null.
     */
    public static Command parse(String fullCommand) throws MediTrackerException, ArgumentNotFoundException,
            NullPointerException {
        String[] commands = fullCommand.split(" ", 2);
        String arguments = (commands.length == 2) ? commands[1] : "";
        CommandName commandName = CommandName.valueOfLabel(commands[0]);

        switch (commandName) {
        case EXIT:
            return new ExitCommand();
        case ADD:
            return new AddCommand(arguments);
        case MODIFY:
            return new ModifyCommand(arguments);
        case LIST:
            return new ListCommand(arguments);
        case DELETE:
            return new DeleteCommand(arguments);
        case SEARCH:
            return new SearchCommand(arguments);
        case TAKE:
            return new TakeCommand(arguments);
        case UNTAKE:
            return new UntakeCommand(arguments);
        default:
            String errorContext = "Invalid MediTracker command! Please refer to the user guide. hello";
            throw new MediTrackerException(errorContext);
        }
    }
}
