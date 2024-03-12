package seedu.budgetbuddy.command;

import seedu.budgetbuddy.ExpenseList;
import seedu.budgetbuddy.SavingList;

public class EditExpenseCommand extends Command{
    private ExpenseList expenses;
    private String category;
    private int index;
    private double amount;
    private String description;

    public EditExpenseCommand(ExpenseList expenses, String category, int index,
                              double amount, String description) {
        this.expenses = expenses;
        this.category = category;
        this.index = index;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public void execute() {
        expenses.editExpense(category, index, amount, description);
    }
}
