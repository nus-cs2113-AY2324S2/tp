package seedu.budgetbuddy.command;

import seedu.budgetbuddy.SavingList;
public class EditSavingCommand extends Command{

    private SavingList savings;
    private String category;
    private int index;
    private double amount;

    public EditSavingCommand(SavingList savings, String category, int index, double amount) {
        this.savings = savings;
        this.category = category;
        this.index = index;
        this.amount = amount;
    }

    @Override
    public void execute() {
        savings.editSaving(category, index, amount);
    }
}
