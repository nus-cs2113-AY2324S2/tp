package seedu.budgetbuddy.commandcreator;

import seedu.budgetbuddy.SavingList;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.EditSavingCommand;

public class EditSavingsCommandCreator extends CommandCreator {
    private SavingList savings;
    private String input;
    public EditSavingsCommandCreator (String input, SavingList savings) {
        this.input = input;
        this.savings = savings;
    }
    public Command handleEditSavingCommand(SavingList savings, String input) {
        String[] parts = input.split(" ");
        String category = null;
        int index = -1;
        double amount = -1;

        for (String part : parts) {
            if (part.startsWith("c/")) {
                category = part.substring(2);
            } else if (part.startsWith("i/")) {
                try {
                    index = Integer.parseInt(part.substring(2));
                } catch (NumberFormatException e) {
                    // Handle invalid index format
                    System.out.println("Invalid index");
                    return null;
                }
            } else if (part.startsWith("a/")) {
                try {
                    amount = Double.parseDouble(part.substring(2));
                } catch (NumberFormatException e) {
                    // Handle invalid amount format
                    System.out.println("Invalid amount. Amount should be a numerical value");
                    return null;
                }
            }
        }

        // Validate required fields
        if (category != null && index != -1 && amount != -1) {
            return new EditSavingCommand(savings, category, index, amount);
        } else {
            // Handle incomplete command
            return null;
        }
    }

    @Override
    public Command createCommand() {
        return handleEditSavingCommand(savings, input);
    }

}
