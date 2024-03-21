package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.frontend.UI;

public class DeleteCommand extends Command {
    private int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public void execute(Dashboard dashboard) {
        if (indexToDelete >= dashboard.getExpenseManager().getNumberOfExpensesTracked()) {
            UI.print("Delete index exceeds number of expenses currently tracked");
            return;
        }
        dashboard.getExpenseManager().delete(indexToDelete);
    }
}
