package seedu.budgetbuddy;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.ListExpenseCommand;
import seedu.budgetbuddy.command.ListSavingsCommand;
import seedu.budgetbuddy.commandcreator.ListCommandCreator;
import seedu.budgetbuddy.exception.BudgetBuddyException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ListCommandCreatorTest {

    @Test
    public void handleListCommand_listExpenses_success() throws BudgetBuddyException {
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        expenseList.addExpense("Transport", "50", "Bus Fare");
        expenseList.addExpense("Housing", "3000", "BTO");

        String input = "list expenses";

        ListCommandCreator listCommandCreator = new ListCommandCreator(splitExpenseList,
                expenseList, savingList, input);

        Command command = listCommandCreator.handleListCommand(input, expenseList, savingList, splitExpenseList);

        assertEquals(ListExpenseCommand.class, command.getClass());
    }

    @Test
    public void handleListCommand_listExpensesWithCategory_success() throws BudgetBuddyException {
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        expenseList.addExpense("Transport", "50", "Bus Fare");
        expenseList.addExpense("Housing", "3000", "BTO");

        String input = "list expenses housing";

        ListCommandCreator listCommandCreator = new ListCommandCreator(splitExpenseList,
                expenseList, savingList, input);

        Command command = listCommandCreator.handleListCommand(input, expenseList, savingList, splitExpenseList);

        assertEquals(ListExpenseCommand.class, command.getClass());
    }

    @Test
    public void handleListCommand_listExpensesWithCategory_invalidCategory() throws BudgetBuddyException {
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        expenseList.addExpense("Transport", "50", "Bus Fare");
        expenseList.addExpense("Housing", "3000", "BTO");

        String input = "list expenses qweqwe";

        ListCommandCreator listCommandCreator = new ListCommandCreator(splitExpenseList,
                expenseList, savingList, input);

        Command command = listCommandCreator.handleListCommand(input, expenseList, savingList, splitExpenseList);

        assertNull(command);
    }

    @Test
    public void handleListCommand_listSavings_success() throws BudgetBuddyException {
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        savingList.addSaving("Salary", "1150");
        savingList.addSaving("Investments", "300");

        String input = "list savings";

        ListCommandCreator listCommandCreator = new ListCommandCreator(splitExpenseList,
                expenseList, savingList, input);

        Command command = listCommandCreator.handleListCommand(input, expenseList, savingList, splitExpenseList);

        assertEquals(ListSavingsCommand.class, command.getClass());
    }

    @Test
    public void handleListCommand_listSavingsWithCategory_success() throws BudgetBuddyException {
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        savingList.addSaving("Salary", "1150");
        savingList.addSaving("Investments", "300");

        String input = "list savings salary";

        ListCommandCreator listCommandCreator = new ListCommandCreator(splitExpenseList,
                expenseList, savingList, input);

        Command command = listCommandCreator.handleListCommand(input, expenseList, savingList, splitExpenseList);

        assertEquals(ListSavingsCommand.class, command.getClass());
    }

    @Test
    public void handleListCommand_listSavingsWithCategory_invalidCategory() throws BudgetBuddyException {
        ExpenseList expenseList = new ExpenseList();
        SavingList savingList = new SavingList();
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        savingList.addSaving("Salary", "1150");
        savingList.addSaving("Investments", "300");

        String input = "list savings qweqwe";

        ListCommandCreator listCommandCreator = new ListCommandCreator(splitExpenseList,
                expenseList, savingList, input);

        Command command = listCommandCreator.handleListCommand(input, expenseList, savingList, splitExpenseList);

        assertNull(command);
    }

}
