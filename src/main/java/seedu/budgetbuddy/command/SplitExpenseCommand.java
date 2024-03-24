package seedu.budgetbuddy.command;

import seedu.budgetbuddy.SplitExpenseList;
import seedu.budgetbuddy.exception.BudgetBuddyException;

public class SplitExpenseCommand extends Command{
    private SplitExpenseList splitexpenses;
    private final String amount;
    private final String numberOfPeople;
    private final String description;

    public SplitExpenseCommand(SplitExpenseList splitexpenses, String amount, String numberOfPeople, String description) {
        this.splitexpenses = splitexpenses;
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
    
   @Override
    public void execute() {
        try {
            splitexpenses.addSplitExpense(this.amount, this.numberOfPeople, this.description);
            System.out.println("SplitExpense Added :" + "$" + amount + "spent by" + numberOfPeople + " description : " + description);
        } catch (BudgetBuddyException e) {
            System.out.println("An error occurred while adding expense.");
        }
    }
}
