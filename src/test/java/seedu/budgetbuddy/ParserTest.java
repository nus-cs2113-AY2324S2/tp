package seedu.budgetbuddy;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.ListExpenseCommand;
import seedu.budgetbuddy.command.ListSavingsCommand;
import seedu.budgetbuddy.command.MenuCommand;
import seedu.budgetbuddy.exception.BudgetBuddyException;
import seedu.budgetbuddy.command.RecurringExpenseCommand;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        RecurringExpensesList expensesList = new RecurringExpensesList();
        Command emptyMenuCommand = parser.parseCommand(expensesList,expenses, savings, "menu");

        assertInstanceOf(MenuCommand.class, emptyMenuCommand);
        assertEquals(0,((MenuCommand)emptyMenuCommand).getIndex());
    }

    @Test
    public void testHandleMenuCommandWithValidIndex() {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();
        SavingList savings = new SavingList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        Command validMenuCommand = parser.parseCommand(expensesList,expenses, savings, "menu 2");

        assertInstanceOf(MenuCommand.class, validMenuCommand);
        assertEquals(2, ((MenuCommand)validMenuCommand).getIndex());
    }

    @Test
    public void testInvalidMenuCommand() {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();
        SavingList savings = new SavingList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        Command invalidMenuCommand = parser.parseCommand(expensesList, expenses, savings, "menu invalidNumber");

        assertNull(invalidMenuCommand);
    }

    @Test
    public void testInvalidCommand() {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();
        SavingList savings = new SavingList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        Command invalidCommand = parser.parseCommand(expensesList, expenses, savings, "notACommand");

        assertNull(invalidCommand);
    }

    @Test
    public void handleListCommand_listExpenses_success() throws BudgetBuddyException {
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
    public void handleListCommand_listExpensesWithCategory_success() throws BudgetBuddyException {
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
    public void handleListCommand_listExpensesWithCategory_invalidCategory() throws BudgetBuddyException {
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
    public void handleListCommand_listSavings_success() throws BudgetBuddyException {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        savingList.addSaving("Salary", "1150");
        savingList.addSaving("Investments", "300");

        String input = "list savings";

        Command command = parser.handleListCommand(input, expenseList, savingList);

        assertEquals(ListSavingsCommand.class, command.getClass());
    }

    @Test 
    public void handleListCommand_listSavingsWithCategory_success() throws BudgetBuddyException {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        savingList.addSaving("Salary", "1150");
        savingList.addSaving("Investments", "300");

        String input = "list savings salary";

        Command command = parser.handleListCommand(input, expenseList, savingList);

        assertEquals(ListSavingsCommand.class, command.getClass());
    }

    @Test 
    public void handleListCommand_listSavingsWithCategory_invalidCategory() throws BudgetBuddyException {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        savingList.addSaving("Salary", "1150");
        savingList.addSaving("Investments", "300");

        String input = "list savings qweqwe";

        Command command = parser.handleListCommand(input, expenseList, savingList);
        assertNull(command);
    }

    @Test
    public void handleRecCommand_newListCommandWithValidInput_createsRecurringExpenseCommand() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        String input = "rec newlist Entertainment";

        Command command = parser.handleRecCommand(input,expensesList, expenseList );

        assertNotNull(command);
        assertInstanceOf(RecurringExpenseCommand.class, command);
    }

    @Test
    public void handleRecCommand_newListCommandWithInvalidInput_returnsNull() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        String input = "rec newlist";

        Command command = parser.handleRecCommand(input,expensesList, expenseList );

        assertNull(command);
    }

    @Test
    public void handleRecCommand_removeListCommandWithValidInput_createsRecurringExpenseCommand() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        expensesList.addNewRecurringList("Entertainment");
        String input = "rec removelist 1";

        Command command = parser.handleRecCommand(input,expensesList, expenseList );

        assertNotNull(command);
        assertInstanceOf(RecurringExpenseCommand.class, command);
    }

    @Test
    public void handleRecCommand_removeListCommandWithInvalidInput_returnsNull() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        String input = "rec removelist string";

        Command command = parser.handleRecCommand(input,expensesList, expenseList );

        assertNull(command);
    }

    @Test
    public void handleRecCommand_removeListCommandWithEmptyInput_returnsNull() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        String input = "rec removelist";

        Command command = parser.handleRecCommand(input,expensesList, expenseList );

        assertNull(command);
    }

    @Test
    public void handleRecCommand_newExpenseCommandWithValidInput_createsRecurringExpenseCommand() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        expensesList.addNewRecurringList("Entertainment");
        String input = "rec newexpense to/1 c/Entertainment a/100 d/Movies";

        Command command = parser.handleRecCommand(input,expensesList, expenseList);

        assertNotNull(command);
        assertInstanceOf(RecurringExpenseCommand.class, command);
    }

    @Test
    public void handleRecCommand_newExpenseCommandWithInvalidAmount_returnsNull() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        expensesList.addNewRecurringList("Entertainment");
        String input = "rec newexpense to/1 c/Entertainment a/sdsdfsdf d/Movies";

        Command command = parser.handleRecCommand(input, expensesList, expenseList);

        assertNull(command);
    }

    @Test
    public void handleRecCommand_addRecCommandWithValidInput_createsRecurringExpenseCommand() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        expensesList.addNewRecurringList("Entertainment");
        String input = "rec addrec 1";

        Command command = parser.handleRecCommand(input,expensesList, expenseList);

        assertNotNull(command);
        assertInstanceOf(RecurringExpenseCommand.class, command);
    }

    @Test
    public void handleRecCommand_addRecCommandWithInvalidInput_returnsNull() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        String input = "rec addrec sdefwre";

        Command command = parser.handleRecCommand(input,expensesList, expenseList);

        assertNull(command);
    }

    @Test
    public void handleRecCommand_viewExpensesCommandWithValidInput_createsRecurringExpenseCommand() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        expensesList.addNewRecurringList("Entertainment");
        String input = "rec viewexpenses 1";

        Command command = parser.handleRecCommand(input,expensesList, expenseList);

        assertNotNull(command);
        assertInstanceOf(RecurringExpenseCommand.class, command);
    }

    @Test
    public void handleRecCommand_viewExpensesCommandWithInvalidInput_returnsNull() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        expensesList.addNewRecurringList("Entertainment");
        String input = "rec viewexpenses fdgder";

        Command command = parser.handleRecCommand(input, expensesList, expenseList);

        assertNull(command);
    }
}
