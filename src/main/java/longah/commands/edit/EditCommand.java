package longah.commands.edit;

import longah.commands.Command;
import longah.node.Group;
import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;

public class EditCommand extends Command {
    private String subCommand;

    /**
     * Constructor for EditCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     * @throws LongAhException If the command string is invalid.
     */
    public EditCommand(String commandString, String taskExpression) throws LongAhException {
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
     * Executes the edit command.
     * Depending on the subCommand, it will execute the edit member or edit transaction command.
     * 
     * @param group The group to execute the command on.
     * @throws LongAhException If the subCommand is invalid.
     */
    public void execute(Group group) throws LongAhException {
        String fullCommandString = this.commandString + " " + this.subCommand;
        switch (this.subCommand) {
        case "member":
            EditMemberCommand editMemberCommand =
                    new EditMemberCommand(fullCommandString, this.taskExpression);
            editMemberCommand.execute(group);
            break;
        case "transaction":
            EditTransactionCommand editTransactionCommand =
                    new EditTransactionCommand(fullCommandString, this.taskExpression);
            editTransactionCommand.execute(group);
            break;
        default:
            throw new LongAhException(ExceptionMessage.INVALID_EDIT_COMMAND);
        }
    }
}
