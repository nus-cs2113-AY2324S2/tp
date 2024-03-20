package longah.commands.list;

import longah.commands.Command;
import longah.node.Group;
import longah.exception.LongAhException;

public class ListDebtCommand extends Command {
    /**
     * Constructor for ListDebtCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public ListDebtCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the list debt command.
     * 
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        System.out.print(group.listDebts());
    }
}
