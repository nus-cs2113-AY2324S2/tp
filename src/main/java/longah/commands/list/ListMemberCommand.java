package longah.commands.list;

import longah.commands.Command;
import longah.node.Group;
import longah.util.MemberList;
import longah.exception.LongAhException;

public class ListMemberCommand extends Command {
    /**
     * Constructor for ListMemberCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public ListMemberCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the list member command.
     * 
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        MemberList members = group.getMemberList();
        System.out.print(members.listMembers());
    }
}
