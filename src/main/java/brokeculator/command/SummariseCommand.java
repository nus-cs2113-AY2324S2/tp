package brokeculator.command;

import brokeculator.expense.ExpenseManager;
import brokeculator.frontend.UI;

public class SummariseCommand extends Command {
    private ExpenseManager expenseManager;

    public SummariseCommand(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
    }

    @Override
    public void execute() {
        expenseManager.summariseExpense();
    }
}
