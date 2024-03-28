package longah.commands.find;

import longah.commands.Command;
import longah.node.Group;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

public class FindCommand extends Command {
    private String subCommand;

    /**
     * Constructor for FindCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     * @throws LongAhException If the find command is invalid.
     */
    public FindCommand(String commandString, String taskExpression) throws LongAhException {
        super(commandString, taskExpression);
        String[] subCommandTaskExpSplit = this.taskExpression.split(" ", 2);
        this.subCommand = subCommandTaskExpSplit[0].toLowerCase();
        if (subCommandTaskExpSplit.length > 1) {
            this.taskExpression = subCommandTaskExpSplit[1];
        } else {
            throw new LongAhException(ExceptionMessage.INVALID_FIND_COMMAND);
        }
    }

    public void execute(Group group) throws LongAhException {
        String fullCommandString = this.commandString + " " + this.subCommand;
        switch (this.subCommand) {
        case "transactions":
            FindTransactionCommand findTransactionCommand =
                    new FindTransactionCommand(fullCommandString, this.taskExpression);
            findTransactionCommand.execute(group);
            break;
        case "lender":
            FindLenderCommand findLenderCommand =
                    new FindLenderCommand(fullCommandString, this.taskExpression);
            findLenderCommand.execute(group);
            break;
        case "borrower":
            FindBorrowerCommand findBorrowerCommand =
                    new FindBorrowerCommand(fullCommandString, this.taskExpression);
            findBorrowerCommand.execute(group);
            break;
        case "debts":
            FindDebtCommand findDebtCommand =
                    new FindDebtCommand(fullCommandString, this.taskExpression);
            findDebtCommand.execute(group);
            break;
        default:
            throw new LongAhException(ExceptionMessage.INVALID_FIND_COMMAND);
        }
    }
}
