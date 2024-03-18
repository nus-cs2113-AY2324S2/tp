package meditracker.parser;

import meditracker.command.Command;
import meditracker.command.ModifyCommand;
import meditracker.command.TakeCommand;
import meditracker.command.UntakeCommand;
import meditracker.exception.ArgumentNotFoundException;
import meditracker.exception.MediTrackerException;
import meditracker.command.AddCommand;
import meditracker.command.ExitCommand;

/**
 * The Parser class parses user input commands into Command objects.
 */
public class Parser {

    /**
     * Parses a full command string into a Command object.
     * @param fullCommand The full command string entered by the user.
     * @return A Command object corresponding to the parsed command.
     * @throws MediTrackerException If an error occurs during parsing.
     */
    public static Command parse(String fullCommand) throws MediTrackerException, ArgumentNotFoundException {
        String[] commands = fullCommand.split(" ", 2);
        String command = commands[0];
        String arguments = (commands.length == 2) ? commands[1] : "";

        switch (command) {
        case "exit":
            return new ExitCommand();
        case "add":
            return new AddCommand(arguments);
        case "modify":
            return new ModifyCommand(arguments);
        case "take":
            return new TakeCommand(arguments);
        case "untake":
            return new UntakeCommand(arguments);
        default:
            throw new MediTrackerException();
        }
    }
}
