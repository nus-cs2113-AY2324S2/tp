package seedu.budgetbuddy;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.commandcreator.CommandCreator;
import seedu.budgetbuddy.commandcreator.FindExpensesCommandCreator;

import static org.junit.jupiter.api.Assertions.assertNull;
public class FindExpensesCommandCreatorTest {
    @Test
    public void handleFindExpensesCommand_invalidMaxAndMinValues_fail() {
        ExpenseList expenses = new ExpenseList();

        String input = "find expenses d/Bruno Mars morethan/400 lessthan/300";
        CommandCreator commandCreator = new FindExpensesCommandCreator(input, expenses);
        Command command = commandCreator.createCommand();
        assertNull(command);

    }

    @Test
    public void handleFindExpensesCommand_maxAndMinValuesAsLetters_fail() {
        ExpenseList expenses = new ExpenseList();

        String input = "find expenses d/Bruno Mars morethan/hello lessthan/hello";
        CommandCreator commandCreator = new FindExpensesCommandCreator(input, expenses);
        Command command = commandCreator.createCommand();
        assertNull(command);

    }
}
