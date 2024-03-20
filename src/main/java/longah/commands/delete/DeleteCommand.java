package longah.commands.delete;

import longah.commands.Command;
import longah.node.Group;
import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;

public class DeleteCommand extends Command {
    private String subCommand;

    /**
     * Constructor for DeleteCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public DeleteCommand(String commandString, String taskExpression) throws LongAhException {
        super(commandString, taskExpression);
        String[] subCommandTaskExpSplit = this.taskExpression.split(" ", 2);
        this.subCommand = subCommandTaskExpSplit[0].toLowerCase();
        if (subCommandTaskExpSplit.length > 1) {
            this.taskExpression = subCommandTaskExpSplit[1];
        } else {
            throw new LongAhException(ExceptionMessage.INVALID_DELETE_COMMAND);
        }
    }

    public void execute(Group group) throws LongAhException {
        String fullCommandString = this.commandString + " " + this.subCommand;
        switch (this.subCommand) {
        case "member":
            throw new LongAhException(ExceptionMessage.COMMAND_NOT_IMPLEMENTED);
        case "transaction":
            DeleteTransactionCommand deleteTransactionCommand =
                    new DeleteTransactionCommand(fullCommandString, this.taskExpression);
            deleteTransactionCommand.execute(group);
            break;
        default:
            throw new LongAhException(ExceptionMessage.INVALID_DELETE_COMMAND);
        }
    }
}
