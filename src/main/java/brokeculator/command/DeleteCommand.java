package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.event.Event;
import brokeculator.expense.Expense;
import brokeculator.frontend.UI;

public class DeleteCommand extends Command {
    private int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public void execute(Dashboard dashboard) {
        boolean isValidExpenseIndex = dashboard.getExpenseManager().isExpenseIndexValid(indexToDelete);
        if (!isValidExpenseIndex) {
            UI.prettyPrint("Invalid expense index provided");
            return;
        }
        Expense expense = dashboard.getExpenseManager().getExpense(indexToDelete);
        Event owningEvent = expense.getOwningEvent();
        if (owningEvent != null) {
            owningEvent.removeExpense(expense);
        }
        dashboard.getExpenseManager().delete(indexToDelete);
        UI.prettyPrint("Deleted expense at index " + indexToDelete);
    }
}
