package seedu.budgetbuddy.commandcreator;

import seedu.budgetbuddy.SavingList;
import seedu.budgetbuddy.command.AddSavingCommand;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.exception.BudgetBuddyException;

public class AddSavingCommandCreator extends CommandCreator{
    private SavingList savings;
    private String input;

    public AddSavingCommandCreator(SavingList savings, String input) {
        this.savings = savings;
        this.input = input;
    }

    public Command handleAddSavingCommand(SavingList savings, String input) {
        if (input == null || !input.contains(" ") || !input.contains("c/") || !input.contains("a/")) {
            System.out.println("Invalid command format.");
            return null;
        }

        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("Saving details are missing.");
            return null;
        }

        String details = parts[1];
        String category = extractDetailsForAdd(details, "c/");
        if (category.isEmpty()) {
            System.out.println("Category is missing.");
            return null;
        }

        String amount = extractDetailsForAdd(details, "a/");
        if (amount.isEmpty()) {
            System.out.println("amount is missing.");
            return null;
        }

        try {
            double amountValue = Double.parseDouble(amount);
            if (amountValue <= 0) {
                throw new BudgetBuddyException(amount + " is not a valid amount.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            return null;
        } catch (BudgetBuddyException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return new AddSavingCommand(savings, category, amount);
    }

    private String extractDetailsForAdd(String details, String prefix) {
        int startIndex = details.indexOf(prefix) + prefix.length();
        int endIndex = details.length();

        String[] nextPrefixes = { "c/", "a/", "d/" };
        for (String nextPrefix : nextPrefixes) {
            if (details.indexOf(nextPrefix, startIndex) != -1 && details.indexOf(nextPrefix, startIndex) < endIndex) {
                endIndex = details.indexOf(nextPrefix, startIndex);
            }
        }
        return details.substring(startIndex, endIndex).trim();
    }

    @Override
    public Command createCommand() {
        return handleAddSavingCommand(savings, input);
    }
}
