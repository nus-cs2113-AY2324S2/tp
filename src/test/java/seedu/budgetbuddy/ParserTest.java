package seedu.budgetbuddy;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.ListExpenseCommand;
import seedu.budgetbuddy.command.ListSavingsCommand;
import seedu.budgetbuddy.command.MenuCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
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

    @Test
    public void handleListCommand_listExpenses_success() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        expenseList.addExpense("Transport", "50", "Bus Fare");
        expenseList.addExpense("Housing", "3000", "BTO");

        String input = "list expenses";

        Command command = parser.handleListCommand(input, expenseList, savingList);

        assertEquals(ListExpenseCommand.class, command.getClass());
    }

    @Test
    public void handleListCommand_listExpensesWithCategory_success() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        expenseList.addExpense("Transport", "50", "Bus Fare");
        expenseList.addExpense("Housing", "3000", "BTO");

        String input = "list expenses housing";

        Command command = parser.handleListCommand(input, expenseList, savingList);

        assertEquals(ListExpenseCommand.class, command.getClass());
    }

    @Test
    public void handleListCommand_listExpensesWithCategory_invalidCategory() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        expenseList.addExpense("Transport", "50", "Bus Fare");
        expenseList.addExpense("Housing", "3000", "BTO");

        String input = "list expenses qweqwe";

        Command command = parser.handleListCommand(input, expenseList, savingList);
        assertNull(command);
    }

    @Test
    public void handleListCommand_listSavings_success() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        savingList.addSaving("Salary", "1150");
        savingList.addSaving("Investment", "300");

        String input = "list savings";

        Command command = parser.handleListCommand(input, expenseList, savingList);

        assertEquals(ListSavingsCommand.class, command.getClass());
    }

    @Test
    public void handleListCommand_listSavingsWithCategory_success() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        savingList.addSaving("Salary", "1150");
        savingList.addSaving("Investment", "300");

        String input = "list savings salary";

        Command command = parser.handleListCommand(input, expenseList, savingList);

        assertEquals(ListSavingsCommand.class, command.getClass());
    }

    @Test
    public void handleListCommand_listSavingsWithCategory_invalidCategory() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        savingList.addSaving("Salary", "1150");
        savingList.addSaving("Investment", "300");

        String input = "list savings qweqwe";

        Command command = parser.handleListCommand(input, expenseList, savingList);
        assertNull(command);
    }
}
