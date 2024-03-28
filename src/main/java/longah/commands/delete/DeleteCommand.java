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
     * @throws LongAhException If the command string is invalid.
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

    /**
     * Executes the delete command.
     * Depending on the subCommand, it will execute the delete member or delete transaction command.
     * 
     * @param group The group to execute the command on.
     * @throws LongAhException If the subCommand is invalid.
     */
    public void execute(Group group) throws LongAhException {
        String fullCommandString = this.commandString + " " + this.subCommand;
        switch (this.subCommand) {
        case "member":
            DeleteMemberCommand deleteMemberCommand =
                    new DeleteMemberCommand(fullCommandString, this.taskExpression);
            deleteMemberCommand.execute(group);
            break;
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
