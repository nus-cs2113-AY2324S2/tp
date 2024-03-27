package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.event.Event;
import brokeculator.expense.Expense;
import brokeculator.frontend.UI;

public class AddExpenseToEventCommand extends Command {

    private final int expenseIdx;
    private final int eventIdx;

    public AddExpenseToEventCommand(int expenseIdx, int eventIdx) {
        this.expenseIdx = expenseIdx;
        this.eventIdx = eventIdx;
    }
    
    @Override
    public void execute(Dashboard dashboard) {
        boolean isValidExpenseIdx = dashboard.getExpenseManager().isExpenseIndexValid(expenseIdx);
        boolean isValidEventIdx = dashboard.getEventManager().isEventIdxValid(eventIdx);
        if (!isValidExpenseIdx || !isValidEventIdx) {
            UI.prettyPrint("Indexes provided are invalid");
            return;
        }
        Event newEvent = dashboard.getEventManager().getEvent(eventIdx);
        Expense expense = dashboard.getExpenseManager().getExpense(expenseIdx);

        assert newEvent != null && expense != null : "Event or Expense is null";

        boolean isExpensedOwnedByEvent = newEvent.containsExpense(expense);
        if (isExpensedOwnedByEvent) {
            UI.prettyPrint("Expense already belongs to the event");
            return;
        }

        Event originalOwningEvent = expense.getOwningEvent();
        if (originalOwningEvent != null) {
            UI.prettyPrint("Expense belonged to another event. Moving it to the new event.");
            originalOwningEvent.removeExpense(expense);
            expense.removeOwningEvent();
        }

        newEvent.addExpense(expense);
        expense.setOwningEvent(newEvent);
        UI.prettyPrint("Expense added to event successfully");
    }
}
