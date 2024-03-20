package longah.handler;

import longah.commands.ClearCommand;
import longah.commands.Command;
import longah.commands.ExitCommand;
import longah.commands.SettleCommand;
import longah.commands.add.AddCommand;
import longah.commands.delete.DeleteCommand;
import longah.commands.find.FindCommand;
import longah.commands.list.ListCommand;
import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;

public class CommandParser {
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
        case "exit":
            return new ExitCommand(commandString, taskExpression);

        case "edit":
            // Fallthrough
        case "help":
            throw new LongAhException(ExceptionMessage.COMMAND_NOT_IMPLEMENTED);
        default:
            throw new LongAhException(ExceptionMessage.INVALID_COMMAND);
        }
    }
}
