package brokeculator.command;

import seedu.expense.Expense;
import seedu.expense.ExpenseManager;

public class AddCommand extends Command{
    private Expense expenseToAdd;
    private ExpenseManager expenseManager;

    public AddCommand(Expense expenseToAdd, ExpenseManager expenseManager) {
        this.expenseToAdd = expenseToAdd;
        this.expenseManager = expenseManager;
    }

    @Override
    public void execute() {
        try {
            expenseManager.add(expenseToAdd);
        } catch (Exception e) {
            // TODO: fix feedback
            System.out.println("Expense cannot be added");
        }
    }
}
