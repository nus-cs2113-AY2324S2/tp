package command;

import exception.CommandInputException;
import ui.CommandType;
import ui.Parser;

public class CommandFactory {
    public static Command create(String userInput) throws CommandInputException {
        CommandType commandType = Parser.parseCommand(userInput);
        switch (commandType) {
        case WORK:
            return new WorkCommand();

        case REST:
            return new RestCommand();

        case EXERCISE:
            return new ExerciseCommand();

        case STATUS:
            return new CheckStatusCommand();

        case HELP:
            return new HelpCommand();

        default:
            return new ExitCommand();
        }
    }
}
