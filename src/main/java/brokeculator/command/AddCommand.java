package brokeculator.command;

import brokeculator.expense.Expense;
import brokeculator.expense.ExpenseManager;

public class AddCommand extends Command{
    private Expense expenseToAdd;

    public AddCommand(Expense expenseToAdd) {
        this.expenseToAdd = expenseToAdd;
    }

    @Override
    public void execute(ExpenseManager expenseManager) {
        try {
            expenseManager.add(expenseToAdd);
        } catch (Exception e) {
            // TODO: fix feedback
            System.out.println("Expense cannot be added");
        }
    }
}
