package seedu.budgetbuddy.command;

import seedu.budgetbuddy.SplitExpenseList;

public class ListSplitExpenseCommand extends Command{
    private SplitExpenseList splitexpenses;

    public ListSplitExpenseCommand(SplitExpenseList splitexpenses) {
        this.splitexpenses = splitexpenses;
    }

    @Override
    public void execute() {
        splitexpenses.listSplitExpenses();
    }
}
