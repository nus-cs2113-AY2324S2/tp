package longah.commands;

import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;
import longah.node.Group;

import longah.handler.UI;

public class HelpCommand extends Command {
    /**
     * Constructor for HelpCommand.
     *
     * @param commandString  The command string.
     * @param taskExpression The task expression.
     */
    public HelpCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }


    /**
     * Executes the help command.
     * @param group The group to execute the command on.
     * @throws LongAhException if unexpected additional parameters are found.
     */
    public void execute(Group group) throws LongAhException {
        if (!this.taskExpression.isEmpty()) {
            throw new LongAhException(ExceptionMessage.INVALID_HELP_COMMAND);
        }
        listAllCommands();
    }

    /**
     * Lists all available commands.
     */
    public static void listAllCommands() {
        UI.showMessage("Here are the full list of commands available:");
        UI.showMessage("1. `add member <NAME>` - Add a new member to the group.");
        UI.showMessage("2. `Add transaction <LENDER> p/<BORROWER1> a/<AMOUNT OWED> " +
                "p/<BORROWER2> a/<AMOUNTED OWED> ...` - Add a new transaction.");
        UI.showMessage("3. `list members` - List all current members in the group.");
        UI.showMessage("4. `list transactions` - List all transactions in the group.");
        UI.showMessage("5. `list debts` - Simplifies and lists all debts in the group.");
        UI.showMessage("6. `delete transaction <TRANSACTION NUMBER>` - Delete a transaction.");
        UI.showMessage("7. `find Borrower <MEMBER NAME>` - Find all transactions where the " +
                "member is a borrower.");
        UI.showMessage("8. `find Lender <MEMBER NAME>` - Find all transactions where the member is a lender.");
        UI.showMessage("9. `find debt <MEMBER NAME>` - Find all debts of the member.");
        UI.showMessage("10. `find transaction <MEMBER NAME>` - Find all transactions the person as the lender.");
        UI.showMessage("11. `edit member <MEMBER NAME> <NEW MEMBER NAME>` - Edit the name of a member.");
        UI.showMessage("12. `edit transaction <TRANSACTION NUMBER> <NEW TRANSACTION>`" +
                " - Edit the details of a transaction.");
        UI.showMessage("13. `settleup <MEMBER NAME>` - Settle all debts of the member.");
        UI.showMessage("14. `clear` - Clear all transaction data in the group.");
        UI.showMessage("15. `reset password` - Reset your own user PIN.");
        UI.showMessage("16. `exit` - Exit the application.");
        UI.showMessage("17. `help` - Display the list of commands.");
    }
}
