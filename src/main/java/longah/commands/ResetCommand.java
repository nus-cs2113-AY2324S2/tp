package longah.commands;

import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;
import longah.handler.PINHandler;
import longah.node.Group;

import java.util.Objects;

public class ResetCommand extends Command {
    /**
     * Constructor for ResetCommand.
     *
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public ResetCommand(String commandString, String taskExpression) {
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
        if (!Objects.equals(this.taskExpression, "password")) {
            throw new LongAhException(ExceptionMessage.INVALID_RESET_COMMAND);
        }
        PINHandler pinHandler = new PINHandler();
        pinHandler.resetPin();
    }
}
