package seedu.budgetbuddy.command;

import seedu.budgetbuddy.SplitExpenseList;

public class SettleSplitExpenseCommand extends Command{

    private SplitExpenseList splitexpenses;
    private int index;

    public SettleSplitExpenseCommand(SplitExpenseList splitexpenses, int index) {
        this.splitexpenses = splitexpenses;
        this.index = index;
    }

    @Override
    public void execute() {
        splitexpenses.settleSplitExpenses(index);
        System.out.println("Settled expense: (" + (index+1) + ") ");
    }
}
