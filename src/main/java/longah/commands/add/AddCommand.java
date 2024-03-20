package longah.commands.add;

import longah.commands.Command;
import longah.node.Group;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

public class AddCommand extends Command {
    private String subCommand;

    /**
     * Constructor for AddCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public AddCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
        String[] subCommandTaskExpSplit = this.taskExpression.split(" ", 2);
        this.subCommand = subCommandTaskExpSplit[0].toLowerCase();
        this.taskExpression = subCommandTaskExpSplit[1];
    }

    /**
     * Executes the add command.
     * Depending on the subCommand, it will execute the add member or add transaction command.
     * 
     * @param group The group to execute the command on.
     * @throws LongAhException If the subCommand is invalid.
     */
    public void execute(Group group) throws LongAhException {
        String fullCommandString = this.commandString + " " + this.subCommand;
        switch (this.subCommand) {
        case "member":
            AddMemberCommand addMemberCommand =
                    new AddMemberCommand(fullCommandString, this.taskExpression);
            addMemberCommand.execute(group);
            break;
        case "transaction":
            AddTransactionCommand addTransactionCommand =
                    new AddTransactionCommand(fullCommandString, this.taskExpression);
            addTransactionCommand.execute(group);
            break;
        default:
            throw new LongAhException(ExceptionMessage.INVALID_ADD_COMMAND);
        }
    }
}
