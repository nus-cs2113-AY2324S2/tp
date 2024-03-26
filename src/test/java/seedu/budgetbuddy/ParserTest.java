package seedu.budgetbuddy;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.command.ListSavingsCommand;
import seedu.budgetbuddy.command.ChangeCurrencyCommand;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.ListExpenseCommand;
import seedu.budgetbuddy.command.MenuCommand;
import seedu.budgetbuddy.exception.BudgetBuddyException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


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
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        Command emptyMenuCommand = parser.parseCommand(expenses, savings, splitExpenseList, "");

        assertInstanceOf(MenuCommand.class, emptyMenuCommand);
        assertEquals(0,((MenuCommand)emptyMenuCommand).getIndex());
    }

    @Test
    public void testHandleMenuCommandWithValidIndex() {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();
        SavingList savings = new SavingList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        
        Command validMenuCommand = parser.parseCommand(expenses, savings, splitExpenseList, "menu 1");
        
        assertInstanceOf(MenuCommand.class, validMenuCommand);
        assertEquals(1,((MenuCommand)validMenuCommand).getIndex());
    }

    @Test
    public void testInvalidMenuCommand() {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();
        SavingList savings = new SavingList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        Command invalidMenuCommand = parser.parseCommand(expenses, savings, splitExpenseList, "a");

        assertNull(invalidMenuCommand);
    }

    @Test 
    public void testInvalidCommand() {
        Parser parser = new Parser();
        ExpenseList expenses = new ExpenseList();
        SavingList savings = new SavingList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        Command invalidCommand = parser.parseCommand(expenses, savings, splitExpenseList, "add expense");

        assertNull(invalidCommand);
    }

    @Test
    public void handleListCommand_listExpenses_success() throws BudgetBuddyException {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        expenseList.addExpense("Transport", "50", "Bus Fare");
        expenseList.addExpense("Housing", "3000", "BTO");

        String input = "list expenses";

        Command command = parser.handleListCommand(input, expenseList, savingList, splitExpenseList);

        assertEquals(ListExpenseCommand.class, command.getClass());
    }

    @Test
    public void handleListCommand_listExpensesWithCategory_success() throws BudgetBuddyException {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        expenseList.addExpense("Transport", "50", "Bus Fare");
        expenseList.addExpense("Housing", "3000", "BTO");

        String input = "list expenses housing";

        Command command = parser.handleListCommand(input, expenseList, savingList, splitExpenseList);

        assertEquals(ListExpenseCommand.class, command.getClass());
    }

    @Test
    public void handleListCommand_listExpensesWithCategory_invalidCategory() throws BudgetBuddyException {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        expenseList.addExpense("Transport", "50", "Bus Fare");
        expenseList.addExpense("Housing", "3000", "BTO");

        String input = "list expenses qweqwe";

        Command command = parser.handleListCommand(input, expenseList, savingList, splitExpenseList);
        assertNull(command);
    }

    @Test
    public void handleListCommand_listSavings_success() throws BudgetBuddyException {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        savingList.addSaving("Salary", "1150");
        savingList.addSaving("Investments", "300");

        String input = "list savings";

        Command command = parser.handleListCommand(input, expenseList, savingList, splitExpenseList);

        assertEquals(ListSavingsCommand.class, command.getClass());
    }

    @Test 
    public void handleListCommand_listSavingsWithCategory_success() throws BudgetBuddyException {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        savingList.addSaving("Salary", "1150");
        savingList.addSaving("Investments", "300");

        String input = "list savings salary";

        Command command = parser.handleListCommand(input, expenseList, savingList, splitExpenseList);

        assertEquals(ListSavingsCommand.class, command.getClass());
    }

    @Test 
    public void handleListCommand_listSavingsWithCategory_invalidCategory() throws BudgetBuddyException {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        savingList.addSaving("Salary", "1150");
        savingList.addSaving("Investments", "300");

        String input = "list savings qweqwe";

        Command command = parser.handleListCommand(input, expenseList, savingList, splitExpenseList);
        assertNull(command);
    }

    @Test
    public void handleChangeCurrencyCommand_changeCurrencyToUSD_success() throws BudgetBuddyException {
        Parser parser = new Parser();
        SavingList savingList = new SavingList();
        ExpenseList expenseList = new ExpenseList();
        CurrencyConverter currencyConverter = new CurrencyConverter();

        savingList.addSaving("Salary", "1000");

        String input = "change currency USD";
        Command command = parser.handleChangeCurrencyCommand(input, savingList, expenseList, currencyConverter);

        assertEquals(ChangeCurrencyCommand.class, command.getClass());
    }

    @Test
    public void handleChangeCurrencyCommand_changeCurrency_invalidCurrencyCode() throws BudgetBuddyException {
        Parser parser = new Parser();
        SavingList savingList = new SavingList();
        ExpenseList expenseList = new ExpenseList();
        CurrencyConverter currencyConverter = new CurrencyConverter();

        savingList.addSaving("Salary", "1000");

        String input = "change currency abc";
        Command command = parser.handleChangeCurrencyCommand(input, savingList, expenseList, currencyConverter);

        assertNull(command);
    }

    @Test
    public void handleChangeCurrencyCommand_changeCurrency_invalidCommandFormat() throws BudgetBuddyException {
        Parser parser = new Parser();
        SavingList savingList = new SavingList();
        ExpenseList expenseList = new ExpenseList();
        CurrencyConverter currencyConverter = new CurrencyConverter();

        savingList.addSaving("Salary", "1000");

        String input = "change currency abc asd";
        Command command = parser.handleChangeCurrencyCommand(input, savingList, expenseList, currencyConverter);

        assertNull(command);
    }
}
