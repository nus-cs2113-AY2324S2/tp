package longah.commands;

import longah.exception.LongAhException;
import longah.node.Group;

public class SettleCommand extends Command {
    /**
     * Constructor for SettleCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public SettleCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the settle command.
     * 
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        group.settleUp(this.taskExpression);
    }
}
