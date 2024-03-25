package longah.commands.delete;

import longah.commands.Command;
import longah.exception.LongAhException;
import longah.node.Group;
import longah.util.MemberList;
import longah.util.TransactionList;

public class DeleteMemberCommand extends Command {
    /**
     * Constructor for DeleteMemberCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public DeleteMemberCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }
    
    /**
     * Executes the delete transaction command.
     * 
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        TransactionList transactions = group.getTransactionList();
        MemberList members = group.getMemberList();
        transactions.deleteMember(taskExpression, members);
        members.deleteMember(taskExpression);
        group.updateTransactionSolution();
        group.saveAllData();
    }
}
