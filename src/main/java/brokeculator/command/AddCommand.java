package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.expense.Expense;
import brokeculator.frontend.UI;

public class AddCommand extends Command{
    private Expense expenseToAdd;

    public AddCommand(Expense expenseToAdd) {
        this.expenseToAdd = expenseToAdd;
    }

    @Override
    public void execute(Dashboard dashboard) {
        try {
            dashboard.getExpenseManager().add(expenseToAdd);
            UI.prettyPrint("Added expense: " + expenseToAdd);
        } catch (Exception e) {
            UI.println("There was an error adding the expense. Please try again.");
        }
    }
}
