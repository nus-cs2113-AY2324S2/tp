package seedu.budgetbuddy.command;

import seedu.budgetbuddy.ExpenseList;

public class DeleteExpenseCommand extends Command{
    private int index;
    private ExpenseList expenses;

    public DeleteExpenseCommand(ExpenseList expenses, int index) {
        this.index = index;
        this.expenses = expenses;
    }

    @Override
    public void execute() {
        expenses.deleteExpense(index);
    }
}
