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
        boolean isValidExpenseIndex = dashboard.getExpenseManager().isExpenseIndexValid(indexToDelete);
        if (!isValidExpenseIndex) {
            UI.prettyPrint("Invalid expense index provided");
            return;
        }
        dashboard.getExpenseManager().delete(indexToDelete);
        UI.prettyPrint("Deleted expense at index " + indexToDelete);
    }
}
