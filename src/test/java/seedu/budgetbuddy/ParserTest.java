package seedu.budgetbuddy;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.MenuCommand;
import seedu.budgetbuddy.exception.InvalidCommandException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
public class ParserTest {

    @Test
    public void handleFindExpensesCommand_invalidMaxAndMinValues_fail() throws InvalidCommandException {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();

        String input = "find expense d/Bruno Mars morethan/400 lessthan/300";
        Command command = parser.handleFindExpensesCommand(input, expenses);
        assertNull(command);

    }

    @Test
    public void handleFindExpensesCommand_maxAndMinValuesAsLetters_fail() throws InvalidCommandException {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();

        String input = "find expense d/Bruno Mars morethan/hello lessthan/hello";
        Command command = parser.handleFindExpensesCommand(input, expenses);
        assertNull(command);

    }
    @Test
    public void testHandleMenuCommandWithoutIndex() {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();
        SavingList savings = new SavingList();
        Command emptyMenuCommand = parser.parseCommand(expenses, savings, "menu");

        assertInstanceOf(MenuCommand.class, emptyMenuCommand);
        assertEquals(0,((MenuCommand)emptyMenuCommand).getIndex());
    }

    @Test
    public void testHandleMenuCommandWithValidIndex() {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();
        SavingList savings = new SavingList();
        Command validMenuCommand = parser.parseCommand(expenses, savings, "menu 2");

        assertInstanceOf(MenuCommand.class, validMenuCommand);
        assertEquals(2, ((MenuCommand)validMenuCommand).getIndex());
    }

    @Test
    public void testInvalidMenuCommand() {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();
        SavingList savings = new SavingList();
        Command invalidMenuCommand = parser.parseCommand(expenses, savings, "menu invalidNumber");

        assertNull(invalidMenuCommand);
    }

    @Test
    public void testInvalidCommand() {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();
        SavingList savings = new SavingList();
        Command invalidCommand = parser.parseCommand(expenses, savings, "notACommand");

        assertNull(invalidCommand);
    }
}
