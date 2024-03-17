package brokeculator.command;

import brokeculator.expense.ExpenseManager;
import brokeculator.frontend.UI;

public class DeleteCommand extends Command {
    private int indexToDelete;
    private ExpenseManager expenseManager;

    public DeleteCommand(int indexToDelete, ExpenseManager expenseManager) {
        this.indexToDelete = indexToDelete;
        this.expenseManager = expenseManager;
    }

    @Override
    public void execute() {
        //TODO implement proper expense deleting
        //expenseManager.delete(indexToDelete);
        UI.print("placeholder for expense deleting");
    }
}
