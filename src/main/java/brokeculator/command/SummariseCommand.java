package brokeculator.command;

import seedu.expense.ExpenseManager;

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
