package longah.commands.find;

import longah.commands.Command;
import longah.node.Group;
import longah.util.TransactionList;
import longah.exception.LongAhException;
import longah.handler.UI;

public class FindTransactionCommand extends Command {
    /**
     * Constructor for FindTransactionCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public FindTransactionCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the find transaction command.
     * 
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        TransactionList transactions = group.getTransactionList();
        UI.showMessage(transactions.findTransactions(taskExpression));
    }
}
