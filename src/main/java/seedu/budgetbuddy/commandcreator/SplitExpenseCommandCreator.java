package seedu.budgetbuddy.commandcreator;

import seedu.budgetbuddy.SplitExpenseList;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.SplitExpenseCommand;
import seedu.budgetbuddy.exception.BudgetBuddyException;

public class SplitExpenseCommandCreator extends CommandCreator{

    private SplitExpenseList splitexpenses;
    private String input;

    public SplitExpenseCommandCreator(SplitExpenseList splitexpenses, String input) {
        this.splitexpenses = splitexpenses;
        this.input = input;
    }

    public Command handleSplitExpenseCommand(SplitExpenseList splitexpenses, String input) {
        if (input == null || !input.contains("a/") || !input.contains("n/") || !input.contains("d/")) {
            System.out.println("Invalid command format.");
            return null;
        }

        // Extract details directly using the prefixes
        String amount = extractDetail(input, "a/");
        String numberOfPeople = extractDetail(input, "n/");
        String description = extractDetail(input, "d/");

        // Validation for each part
        if (amount.isEmpty() || numberOfPeople.isEmpty() || description.isEmpty()) {
            System.out.println("Missing details.");
            return null;
        }

        try {
            double amountValue = Double.parseDouble(amount);
            if (amountValue <= 0) {
                throw new BudgetBuddyException(amount + " is not a valid amount.");
            }
        } catch (NumberFormatException | BudgetBuddyException e) {
            System.out.println("Invalid amount format.");
            return null;
        }

        try {
            int numberValue = Integer.parseInt(numberOfPeople);
            if (numberValue <= 0) {
                throw new BudgetBuddyException(numberOfPeople + " is not a valid number.");
            }
        } catch (NumberFormatException | BudgetBuddyException e) {
            System.out.println("Invalid number format.");
            return null;
        }

        return new SplitExpenseCommand(splitexpenses, amount, numberOfPeople, description);
    }

    private String extractDetail(String input, String prefix) {
        try {
            int startIndex = input.indexOf(prefix) + prefix.length();
            int endIndex = input.indexOf(" ", startIndex);
            endIndex = endIndex == -1 ? input.length() : endIndex; // Handle last detail case
            return input.substring(startIndex, endIndex);
        } catch (Exception e) {
            return ""; // Return empty string if any error occurs
        }
    }

    @Override
    public Command createCommand() {
        return handleSplitExpenseCommand(splitexpenses, input);
    }
}
