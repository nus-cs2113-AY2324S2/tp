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
        //TODO implement proper expense summarising
        //expenseManager.summariseExpense();
        UI.print("placeholder for expense summarising");
    }
}
