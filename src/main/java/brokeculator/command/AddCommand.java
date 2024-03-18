package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.expense.Expense;

public class AddCommand extends Command{
    private Expense expenseToAdd;

    public AddCommand(Expense expenseToAdd) {
        this.expenseToAdd = expenseToAdd;
    }

    @Override
    public void execute(Dashboard dashboard) {
        try {
            dashboard.getExpenseManager().add(expenseToAdd);
        } catch (Exception e) {
            // TODO: fix feedback
            System.out.println("Expense cannot be added");
        }
    }
}
