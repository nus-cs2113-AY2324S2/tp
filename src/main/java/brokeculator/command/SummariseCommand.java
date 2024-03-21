package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.frontend.UI;

public class SummariseCommand extends Command {
    private int beginIndex;
    private int endIndex;

    public SummariseCommand(int beginIndex, int endIndex) {
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void execute(Dashboard dashboard) {
        if (beginIndex >= dashboard.getExpenseManager().getNumberOfExpensesTracked()) {
            UI.print("Start index exceeds number of expenses currently tracked");
            return;
        }
        double summary = dashboard.getExpenseManager().summariseExpenses(beginIndex, endIndex);
        UI.prettyPrint("The total is $" + summary);
    }
}
