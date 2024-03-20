package longah.commands;

import longah.node.Group;
import longah.exception.LongAhException;

public abstract class Command {
    protected String commandString;
    protected String taskExpression;

    /**
     * Constructor for Command.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public Command(String commandString, String taskExpression) {
        this.commandString = commandString;
        this.taskExpression = taskExpression;
    }

    /**
     * Executes the command.
     * 
     * @throws LongAhException If an error occurs during the execution of the command.
     */
    public abstract void execute(Group group) throws LongAhException;

    /**
     * Returns whether the current command is an exit comamnd or not.
     * 
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the command string.
     * 
     * @return The command string.
     */
    public String getCommandString() {
        return commandString;
    }


    /**
     * Returns the task expression.
     * 
     * @return The task expression.
     */
    public String getTaskExpression() {
        return taskExpression;
    }
}
