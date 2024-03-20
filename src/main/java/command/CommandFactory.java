package command;

import exception.CommandInputException;
import ui.Parser;

public class CommandFactory {
    public static Command create(String userInput) throws CommandInputException {
        CommandType commandType = Parser.parseCommand(userInput);
        switch (commandType) {
        case WORK:
            return new WorkCommand();
        case REST:
            return new RestCommand();
        default:
            return new ExitCommand();
        }
    }
}
