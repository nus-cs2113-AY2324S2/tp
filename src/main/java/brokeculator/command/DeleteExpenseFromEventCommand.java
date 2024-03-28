package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.event.Event;
import brokeculator.expense.Expense;
import brokeculator.frontend.UI;

public class DeleteExpenseFromEventCommand extends Command {

    private int expenseIdx;

    public DeleteExpenseFromEventCommand(int expenseIdx) {
        this.expenseIdx = expenseIdx;
    }

    @Override
    public void execute(Dashboard dashboard) {
        boolean isValidExpenseIdx = dashboard.getExpenseManager().isExpenseIndexValid(expenseIdx);
        if (!isValidExpenseIdx) {
            UI.prettyPrint("Indexes provided are invalid");
            return;
        }
        Expense expense = dashboard.getExpenseManager().getExpense(expenseIdx);
        Event owningEvent = expense.getOwningEvent();

        if (owningEvent == null) {
            UI.prettyPrint("Expense does not belong to any event");
            return;
        }

        owningEvent.removeExpense(expense);
        expense.removeOwningEvent();

        UI.prettyPrint("Expense removed from event successfully");
    }
}
