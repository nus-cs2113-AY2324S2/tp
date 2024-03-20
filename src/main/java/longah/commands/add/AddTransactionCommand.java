package longah.commands.add;

import longah.commands.Command;
import longah.node.Group;
import longah.util.MemberList;
import longah.util.TransactionList;
import longah.exception.LongAhException;

public class AddTransactionCommand extends Command {
    /**
     * Constructor for AddTransactionCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public AddTransactionCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the add transaction command.
     * 
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        MemberList members = group.getMemberList();
        TransactionList transactions = group.getTransactionList();
        transactions.addTransaction(taskExpression, members);
        group.updateTransactionSolution();
        group.saveAllData();
    }
}
