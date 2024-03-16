package brokeculator.command;

import seedu.expense.ExpenseManager;

public class ListCommand extends Command {
    private int amountToList;
    private ExpenseManager expenseManager;

    public ListCommand(int amountToList, ExpenseManager expenseManager) {
        this.amountToList = amountToList;
        this.expenseManager = expenseManager;
    }

    @Override
    public void execute() {
        expenseManager.list(amountToList);
    }
}
