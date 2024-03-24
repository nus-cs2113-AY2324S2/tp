package seedu.budgetbuddy.command;

import seedu.budgetbuddy.SplitExpenseList;

public class SplitExpenseCommand extends Command{
    private final String numberOfPeople;
    private final String amount;
    private final String description;

    public SplitExpenseCommand(SplitExpenseList splitexpenses, String numberOfPeople, String amount, String description) {
        this.numberOfPeople = numberOfPeople;
        this.amount = amount;
        this.description = description;
    }

    public String getNumberOfPeople() {
        return numberOfPeople;
    }

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
    
    public void execute() {
        System.out.println("Expense Split: " + numberOfPeople + " people, $" + amount + " each, description: " + description);
    }
}
