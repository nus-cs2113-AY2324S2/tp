package longah.commands.delete;

import longah.commands.Command;
import longah.exception.LongAhException;
import longah.node.Group;
import longah.util.TransactionList;

public class DeleteTransactionCommand extends Command {
    /**
     * Constructor for DeleteTransactionCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public DeleteTransactionCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }
    
    /**
     * Executes the delete transaction command.
     * 
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        TransactionList transactions = group.getTransactionList();
        transactions.remove(taskExpression);
    }
}
