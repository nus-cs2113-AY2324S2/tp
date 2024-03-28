package longah.handler;

import longah.commands.ClearCommand;
import longah.commands.Command;
import longah.commands.ExitCommand;
import longah.commands.HelpCommand;
import longah.commands.PINCommand;
import longah.commands.SettleCommand;
import longah.commands.add.AddCommand;
import longah.commands.delete.DeleteCommand;
import longah.commands.edit.EditCommand;
import longah.commands.find.FindCommand;
import longah.commands.list.ListCommand;
import longah.exception.ExceptionMessage;
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
        String taskExpression = commandExpressionSplit.length > 1 ? commandExpressionSplit[1] : "";
        return parseCommand(commandString, taskExpression);
    }

    /**
     * Parses the command string and returns the corresponding command.
     *
     * @param commandString The command string.
     * @param taskExpression The task expression.
     * @return The corresponding command of type {@link Command}.
     */
    public static Command parseCommand(String commandString, String taskExpression)
            throws LongAhException {
        switch (commandString) {
        case "add":
            return new AddCommand(commandString, taskExpression);
        case "list":
            return new ListCommand(commandString, taskExpression);
        case "find":
            return new FindCommand(commandString, taskExpression);
        case "delete":
            return new DeleteCommand(commandString, taskExpression);
        case "clear":
            return new ClearCommand(commandString, taskExpression);
        case "settleup":
            return new SettleCommand(commandString, taskExpression);
        case "edit":
            return new EditCommand(commandString, taskExpression);
        case "exit":
            return new ExitCommand(commandString, taskExpression);
        case "pin":
            return new PINCommand(commandString, taskExpression);
        case "help":
            return new HelpCommand(commandString, taskExpression);
            
        default:
            throw new LongAhException(ExceptionMessage.INVALID_COMMAND);
        }
    }
}
