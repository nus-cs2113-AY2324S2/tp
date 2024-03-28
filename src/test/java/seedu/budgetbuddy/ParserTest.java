package seedu.budgetbuddy;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.MenuCommand;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;


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

    @Test @Disabled
    public void testHandleMenuCommandWithoutIndex() {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();
        SavingList savings = new SavingList();
        RecurringExpensesList expensesList = new RecurringExpensesList();

        SplitExpenseList splitExpenseList = new SplitExpenseList();

        Command emptyMenuCommand = parser.parseCommand(expenses, savings, splitExpenseList, expensesList, "menu");

        assertInstanceOf(MenuCommand.class, emptyMenuCommand);
        assertEquals(0,((MenuCommand)emptyMenuCommand).getIndex());
    }

    @Test
    public void testHandleMenuCommandWithValidIndex() {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();
        SavingList savings = new SavingList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        
        Command validMenuCommand = parser.parseCommand(expenses, savings,
                splitExpenseList, expensesList,"menu 1");

        assertInstanceOf(MenuCommand.class, validMenuCommand);
        assertEquals(1,((MenuCommand)validMenuCommand).getIndex());
    }

    @Test
    public void testInvalidMenuCommand() {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();
        SavingList savings = new SavingList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();

        Command invalidMenuCommand = parser.parseCommand(expenses, savings, splitExpenseList,
                expensesList,"aaa");

        assertNull(invalidMenuCommand);
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
