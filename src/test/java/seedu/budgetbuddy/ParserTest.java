package seedu.budgetbuddy;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.command.Command;

import static org.junit.jupiter.api.Assertions.assertNull;



public class ParserTest {

    @Test
    public void handleFindExpensesCommand_invalidMaxAndMinValues_fail() {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();

        String input = "find expenses d/Bruno Mars morethan/400 lessthan/300";
        Command command = parser.handleFindExpensesCommand(input, expenses);
        assertNull(command);

    }

    @Test
    public void handleFindExpensesCommand_maxAndMinValuesAsLetters_fail() {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();

        String input = "find expenses d/Bruno Mars morethan/hello lessthan/hello";
        Command command = parser.handleFindExpensesCommand(input, expenses);
        assertNull(command);

    }

    @Test 
    public void testInvalidCommand() {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();
        SavingList savings = new SavingList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        Command invalidCommand = parser.parseCommand(expenses, savings, splitExpenseList,
                expensesList, "NotaCommand");

        assertNull(invalidCommand);
    }

}
