package longah.commands.find;

import longah.commands.Command;
import longah.node.Group;
import longah.exception.LongAhException;

public class FindDebtCommand extends Command {
    /**
     * Constructor for FindDebtCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public FindDebtCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the find debt command.
     * 
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        System.out.println(group.listIndivDebt(taskExpression));
    }
}
