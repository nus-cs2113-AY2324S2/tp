package brokeculator.command;

import brokeculator.dashboard.Dashboard;

public class DeleteCommand extends Command {
    private int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public void execute(Dashboard dashboard) {
        //TODO implement proper expense deleting
        dashboard.getExpenseManager().delete(indexToDelete);
    }
}
