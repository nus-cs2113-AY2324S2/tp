package brokeculator.command;
import brokeculator.expense.ExpenseManager;
import brokeculator.frontend.UI;

public class ExitCommand extends Command {
    public static final String GOODBYE_STRING = "Goodbye!";
    public void execute(ExpenseManager expenseManager) {
        UI.print(GOODBYE_STRING);
        System.exit(0);
    }
}
