package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.frontend.UI;

public class SummariseCommand extends Command {

    public SummariseCommand() {}

    @Override
    public void execute(Dashboard dashboard) {
        //TODO implement proper expense summarising
        double summary = dashboard.getExpenseManager().summariseExpenses();
        UI.print("Total expenses: " + summary);
    }
}
