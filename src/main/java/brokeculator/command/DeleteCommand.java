package brokeculator.command;

import brokeculator.expense.ExpenseManager;

public class DeleteCommand extends Command {
    private int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public void execute(ExpenseManager expenseManager) {
        //TODO implement proper expense deleting
        expenseManager.delete(indexToDelete);
    }
}
