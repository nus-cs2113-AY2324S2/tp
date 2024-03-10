package seedu.command;

import seedu.data.SavingList;

public class AddSavingCommand extends Command{
    private SavingList savings;
    private final String category;
    private final String amount;

    public AddSavingCommand(SavingList savings, String category, String amount) {
        this.category = category;
        this.amount = amount;
        this.savings = savings;
    }

    @Override
    public void execute() {
        savings.addSaving(this.category,this.amount);
        System.out.println("Savings Added to:" + category + " of $" + amount);
    }
}

