package seedu.budgetbuddy.commandcreator;

import seedu.budgetbuddy.SplitExpenseList;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.SettleSplitExpenseCommand;

public class SettleSplitExpenseCommandCreator extends CommandCreator{
    private String input;
    private SplitExpenseList splitexpenses;

    public SettleSplitExpenseCommandCreator(String input, SplitExpenseList splitexpenses) {
        this.input = input;
        this.splitexpenses = splitexpenses;
    }

    public Command handleSettleExpenseCommand(String input, SplitExpenseList splitexpenses) {

        assert input != null : "Input should not be null";
        assert !input.isEmpty() : "Input should not be empty";

        String[] parts = input.split("i/", 2);

        if (parts.length < 2) {
            System.out.println("Error: Invalid command format. Expected format: <command> i/<index>");
            return null;
        }

        try {
            int index = Integer.parseInt(parts[1]) - 1;
            // Check if the index is within the bounds of the expense list.
            if (index < 0 || index >= splitexpenses.size()) {
                System.out.println("Error: Index is out of bounds.");
                return null;
            }
            return new SettleSplitExpenseCommand(splitexpenses, index);
        } catch (NumberFormatException e) {
            // Catch the NumberFormatException if the part after "i/" isn't a valid integer.
            System.out.println("Error: Index is not a valid number.");
            return null;
        }
    }

    @Override
    public Command createCommand() {
        return handleSettleExpenseCommand(input, splitexpenses);
    }
}
