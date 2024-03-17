package brokeculator.command;

import brokeculator.expense.ExpenseManager;
import brokeculator.frontend.UI;

public class SummariseCommand extends Command {

    public SummariseCommand() {}

    @Override
    public void execute(ExpenseManager expenseManager) {
        //TODO implement proper expense summarising
        double summary = expenseManager.summariseExpenses();
        UI.print("Total expenses: " + summary);
    }
}
