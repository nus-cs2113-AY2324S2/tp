package longah.handler;

import longah.commands.Command;
import longah.exception.LongAhException;

public class InputHandler {
    /**
     * Parses the user input and returns the corresponding command.
     * 
     * @param userInput The user input.
     * @return The corresponding command.
     */
    public static Command parseInput(String userInput) throws LongAhException {
        String[] commandExpressionSplit = userInput.split(" ", 2);
        String commandString = commandExpressionSplit[0].toLowerCase();
        String taskExpression = commandExpressionSplit[1];
        return CommandParser.parseCommand(commandString, taskExpression);
    }
}
