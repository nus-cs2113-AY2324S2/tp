package seedu.budgetbuddy.command;

import seedu.budgetbuddy.ExpenseList;

public class ListExpenseCommand extends Command {
    private ExpenseList expenses;
    private String filterCategory;

    public ListExpenseCommand(ExpenseList expenses) {
        this.expenses = expenses;
        this.filterCategory = null; // Indicates no filter category is provided
    }

    public ListExpenseCommand(ExpenseList expenses, String filterCategory) {
        this.expenses = expenses;
        this.filterCategory = filterCategory;
    }

    @Override
    public void execute() {
        // Load expenses from the file before listing them
        expenses.loadExpensesFromFile();
        // Now, list the expenses with or without a filter category
        expenses.listExpenses(filterCategory);
    }
}
