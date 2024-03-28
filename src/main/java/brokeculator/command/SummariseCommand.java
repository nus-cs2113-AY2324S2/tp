package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.frontend.UI;

import java.time.LocalDateTime;

public class SummariseCommand extends Command {
    private String name;
    private LocalDateTime date;
    private String category;
    private int beginIndex;
    private int endIndex;

    public SummariseCommand(String name, LocalDateTime date, String category, int beginIndex, int endIndex) {
        this.name = name;
        this.date = date;
        this.category = category;
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void execute(Dashboard dashboard) {
        if (beginIndex >= dashboard.getExpenseManager().getNumberOfExpensesTracked()) {
            UI.print("Start index exceeds number of expenses currently tracked");
            return;
        }
        double summary = dashboard.getExpenseManager().summariseExpenses(name, date, category, beginIndex, endIndex);
        UI.prettyPrint("The total is $" + summary);
    }
}
