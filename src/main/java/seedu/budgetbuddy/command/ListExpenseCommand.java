package seedu.budgetbuddy.command;

import seedu.budgetbuddy.ExpenseList;

public class ListExpenseCommand extends Command {
    private ExpenseList expenses;
    private String filterCategory;
    public ListExpenseCommand(ExpenseList expenses) {
        this.expenses = expenses;
    }

    public ListExpenseCommand(ExpenseList expenses, String filterCategory) {
        this.expenses = expenses;
        this.filterCategory = filterCategory;
    }

    @Override
    public void execute() {
        expenses.listExpenses(filterCategory);
    }
}
