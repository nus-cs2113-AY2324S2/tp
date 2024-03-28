package longah.commands;

import longah.exception.LongAhException;
import longah.handler.PINHandler;
import longah.node.Group;
import longah.exception.ExceptionMessage;


public class PINCommand extends Command {
    /**
     * Constructor for PINCommand.
     *
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public PINCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    @Override
    public void execute(Group group) throws LongAhException {
        execute();
    }

    /**
     * Executes the reset command.
     *
     * @throws LongAhException If unexpected additional parameters are found.
     */
    public void execute() throws LongAhException {
        switch (this.taskExpression) {
        case "reset":
            PINHandler.resetPin();
            break;
        case "enable":
            PINHandler.enablePin();
            break;
        case "disable":
            PINHandler.disablePin();
            break;
        default:
            throw new LongAhException(ExceptionMessage.INVALID_PIN_COMMAND);
        }
    }
}
