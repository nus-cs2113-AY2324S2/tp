package longah.commands.add;

import longah.commands.Command;
import longah.node.Group;
import longah.util.MemberList;
import longah.exception.LongAhException;

public class AddMemberCommand extends Command {
    /**
     * Constructor for AddMemberCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public AddMemberCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the add member command.
     * 
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        MemberList members = group.getMemberList();
        members.addMember(taskExpression);
        group.saveMembersData();
    }
}
