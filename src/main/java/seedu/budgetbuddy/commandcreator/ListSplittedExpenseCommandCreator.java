package seedu.budgetbuddy.commandcreator;

import seedu.budgetbuddy.SplitExpenseList;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.ListSplitExpenseCommand;

public class ListSplittedExpenseCommandCreator extends CommandCreator{

    private String input;
    private SplitExpenseList splitexpenseList;

    public ListSplittedExpenseCommandCreator(String input, SplitExpenseList splitexpenseList) {
        this.input = input;
        this.splitexpenseList = splitexpenseList;
    }

    public Command handleSplitExpenseListCommand(String input, SplitExpenseList splitexpenseList) {
        assert input != null : "Input should not be null";
        assert !input.isEmpty() : "Input should not be empty";

        String[] parts = input.split(" ");
        assert parts.length >= 1 : "At least one part should be present in the input";

        String action = parts[0];
        assert !action.isEmpty() : "Action should not be empty";

        switch (action) {
        case "list":
            if (parts.length == 2) {
                String listType = parts[1];
                assert !listType.isEmpty() : "List type should not be empty";

                if (listType.equalsIgnoreCase("expenses")) {
                    return new ListSplitExpenseCommand(splitexpenseList);
                }
            } else if (parts.length == 3 && parts[1].equalsIgnoreCase("splitted")
                    && parts[2].equalsIgnoreCase("expenses")) {
                return new ListSplitExpenseCommand(splitexpenseList);
            }
            break;
        default:
            return null;
        }

        return null;
    }

    @Override
    public Command createCommand() {
        return handleSplitExpenseListCommand(input, splitexpenseList);
    }
}
