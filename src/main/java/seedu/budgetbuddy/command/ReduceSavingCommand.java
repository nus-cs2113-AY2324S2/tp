package seedu.budgetbuddy.command;

import seedu.budgetbuddy.SavingList;

public class ReduceSavingCommand extends Command{

    SavingList savings;
    private int index;
    private double amount;

    public ReduceSavingCommand(SavingList savings, int index, double amount) {
        this.savings = savings;
        this.index = index;
        this.amount = amount;
    }

    @Override
    public void execute() {
        savings.reduceSavings(index, amount);
    }

}
