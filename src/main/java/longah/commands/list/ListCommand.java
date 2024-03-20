package longah.commands.list;

import longah.commands.Command;
import longah.node.Group;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

public class ListCommand extends Command {
    private String subCommand;

    /**
     * Constructor for ListCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public ListCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
        String[] subCommandTaskExpSplit = this.taskExpression.split(" ", 2);
        this.subCommand = subCommandTaskExpSplit[0].toLowerCase();
        this.taskExpression = subCommandTaskExpSplit.length > 1 ? subCommandTaskExpSplit[1] : "";
    }

    /**
     * Executes the list command.
     * Depending on the subCommand, it will execute the list member
     * or list transaction or list debt command.
     * 
     * @param group The group to execute the command on.
     * @throws LongAhException If the subCommand is invalid.
     */
    public void execute(Group group) throws LongAhException {
        if (!this.taskExpression.equals("")) {
            throw new LongAhException(ExceptionMessage.INVALID_LIST_COMMAND);
        }

        String fullCommandString = this.commandString + " " + this.subCommand;
        switch (this.subCommand) {
        case "members":
            ListMemberCommand listMemberCommand =
                    new ListMemberCommand(fullCommandString, this.taskExpression);
            listMemberCommand.execute(group);
            break;
        case "transactions":
            ListTransactionCommand listTransactionCommand =
                    new ListTransactionCommand(fullCommandString, this.taskExpression);
            listTransactionCommand.execute(group);
            break;
        case "debt":
            ListDebtCommand listDebtCommand =
                    new ListDebtCommand(fullCommandString, this.taskExpression);
            listDebtCommand.execute(group);
            break;
        default:
            throw new LongAhException(ExceptionMessage.INVALID_LIST_COMMAND);
        }
    }
}
