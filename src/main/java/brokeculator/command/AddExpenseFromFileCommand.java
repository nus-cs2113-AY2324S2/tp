package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.expense.Expense;
import brokeculator.frontend.UI;

public class AddExpenseFromFileCommand extends Command{
    private final String fileString;

    public AddExpenseFromFileCommand(String fileString) {
        this.fileString = fileString;
    }

    @Override
    public void execute(Dashboard dashboard) {
        try {
            Expense expense = Expense.getExpenseFromFile(this.fileString);
            dashboard.getExpenseManager().add(expense);
        } catch (Exception e) {
            UI.println("Expense cannot be added");
        }
    }
}
