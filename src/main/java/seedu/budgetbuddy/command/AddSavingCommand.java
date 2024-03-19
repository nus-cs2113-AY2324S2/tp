package seedu.budgetbuddy.command;

import seedu.budgetbuddy.SavingList;
import seedu.budgetbuddy.exception.BudgetBuddyException;

import java.util.logging.Logger;
import java.util.logging.Level;

public class AddSavingCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(AddSavingCommand.class.getName());

    private SavingList savings;
    private final String category;
    private final String amount;

    public AddSavingCommand(SavingList savings, String category, String amount) {
        this.category = category;
        this.amount = amount;
        this.savings = savings;
    }

    @Override
    public void execute(){
        try {
            LOGGER.log(Level.INFO, "Adding savings to category: {0} with amount: ${1}", new Object[]{category, amount});

            savings.addSaving(this.category, this.amount);

            LOGGER.log(Level.INFO, "Savings added to: {0} of ${1}", new Object[]{category, amount});
            System.out.println("Savings Added to:" + category + " of $" + amount);
        } catch (BudgetBuddyException e) {
            System.out.println(e.getMessage());
            LOGGER.log(Level.SEVERE, "Exception while adding savings", e);
        }
    }
}
