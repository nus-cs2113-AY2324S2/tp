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

        return new ListSplitExpenseCommand(splitexpenseList);
    }

    @Override
    public Command createCommand() {
        return handleSplitExpenseListCommand(input, splitexpenseList);
    }
}
