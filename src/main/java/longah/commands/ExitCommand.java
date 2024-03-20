package longah.commands;

import longah.node.Group;
import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;

public class ExitCommand extends Command {
    /**
     * Constructor for ExitCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public ExitCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the exit command.
     * 
     * @param group The group to execute the command on.
     * @throws LongAhException If unexpected additional parameters are found.
     */
    public void execute(Group group) throws LongAhException {
        if (!this.taskExpression.equals("")) {
            throw new LongAhException(ExceptionMessage.INVALID_EXIT_COMMAND);
        }
    }

    /**
     * Returns whether the current command is an exit comamnd or not.
     * 
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return true;
    }
}
