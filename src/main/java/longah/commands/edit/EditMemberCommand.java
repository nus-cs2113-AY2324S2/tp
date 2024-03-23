package longah.commands.edit;

import longah.commands.Command;
import longah.exception.LongAhException;
import longah.node.Group;
import longah.util.MemberList;

public class EditMemberCommand extends Command {
    /**
     * Constructor for EditMemberCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public EditMemberCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }
    
    /**
     * Executes the edit member name command.
     * 
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        MemberList members = group.getMemberList();
        members.editMemberName(taskExpression);
        group.updateTransactionSolution();
        group.saveAllData();
    }
}
