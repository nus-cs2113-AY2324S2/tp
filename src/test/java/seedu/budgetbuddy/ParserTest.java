package seedu.budgetbuddy;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.command.Command;

import static org.junit.jupiter.api.Assertions.assertNull;



public class ParserTest {


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
