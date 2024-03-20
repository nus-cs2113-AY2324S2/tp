package longah.commands.list;

import longah.commands.Command;
import longah.node.Group;
import longah.util.TransactionList;
import longah.exception.LongAhException;

public class ListTransactionCommand extends Command {
    /**
     * Constructor for ListTransactionCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public ListTransactionCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the list transaction command.
     * 
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        TransactionList transactions = group.getTransactionList();
        System.out.print(transactions.listTransactions());
    }
}
