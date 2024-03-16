package brokeculator.command;

import seedu.expense.Expense;
import seedu.expense.ExpenseManager;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private int indexToDelete;
    private ExpenseManager expenseManager;

    public DeleteCommand(int indexToDelete, ExpenseManager expenseManager) {
        this.indexToDelete = indexToDelete;
        this.expenseManager = expenseManager;
    }

    @Override
    public void execute() {
        expenseManager.delete(indexToDelete);
    }
}
