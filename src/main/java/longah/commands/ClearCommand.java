package longah.commands;

import longah.node.Group;
import longah.util.TransactionList;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

public class ClearCommand extends Command {
    /**
     * Constructor for ClearCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public ClearCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the clear command.
     * 
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        if (!this.taskExpression.equals("")) {
            throw new LongAhException(ExceptionMessage.INVALID_CLEAR_COMMAND);
        }

        TransactionList transactions = group.getTransactionList();
        transactions.clear();
    }
}
