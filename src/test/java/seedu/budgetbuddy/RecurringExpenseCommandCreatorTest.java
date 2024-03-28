package seedu.budgetbuddy;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.RecurringExpenseCommand;
import seedu.budgetbuddy.commandcreator.CommandCreator;
import seedu.budgetbuddy.commandcreator.RecurringExpenseCommandCreator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RecurringExpenseCommandCreatorTest {
    @Test
    public void handleRecCommand_newListCommandWithValidInput_createsRecurringExpenseCommand() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        String input = "rec newlist Entertainment";

        CommandCreator commandCreator = new RecurringExpenseCommandCreator(input, expensesList, expenseList);
        Command command = commandCreator.createCommand();

        assertNotNull(command);
        assertInstanceOf(RecurringExpenseCommand.class, command);
    }

    @Test
    public void handleRecCommand_newListCommandWithInvalidInput_returnsNull() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        String input = "rec newlist";

        CommandCreator commandCreator = new RecurringExpenseCommandCreator(input, expensesList, expenseList);
        Command command = commandCreator.createCommand();

        assertNull(command);
    }

    @Test
    public void handleRecCommand_removeListCommandWithValidInput_createsRecurringExpenseCommand() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        expensesList.addNewRecurringList("Entertainment");
        String input = "rec removelist 1";

        CommandCreator commandCreator = new RecurringExpenseCommandCreator(input, expensesList, expenseList);
        Command command = commandCreator.createCommand();

        assertNotNull(command);
        assertInstanceOf(RecurringExpenseCommand.class, command);
    }

    @Test
    public void handleRecCommand_removeListCommandWithInvalidInput_returnsNull() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        String input = "rec removelist string";

        CommandCreator commandCreator = new RecurringExpenseCommandCreator(input, expensesList, expenseList);
        Command command = commandCreator.createCommand();

        assertNull(command);
    }

    @Test
    public void handleRecCommand_removeListCommandWithEmptyInput_returnsNull() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        String input = "rec removelist";

        CommandCreator commandCreator = new RecurringExpenseCommandCreator(input, expensesList, expenseList);
        Command command = commandCreator.createCommand();

        assertNull(command);
    }

    @Test
    public void handleRecCommand_newExpenseCommandWithValidInput_createsRecurringExpenseCommand() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        expensesList.addNewRecurringList("Entertainment");
        String input = "rec newexpense to/1 c/Entertainment a/100 d/Movies";

        CommandCreator commandCreator = new RecurringExpenseCommandCreator(input, expensesList, expenseList);
        Command command = commandCreator.createCommand();

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

        CommandCreator commandCreator = new RecurringExpenseCommandCreator(input, expensesList, expenseList);
        Command command = commandCreator.createCommand();

        assertNull(command);
    }

    @Test
    public void handleRecCommand_addRecCommandWithValidInput_createsRecurringExpenseCommand() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        expensesList.addNewRecurringList("Entertainment");
        String input = "rec addrec 1";

        CommandCreator commandCreator = new RecurringExpenseCommandCreator(input, expensesList, expenseList);
        Command command = commandCreator.createCommand();

        assertNotNull(command);
        assertInstanceOf(RecurringExpenseCommand.class, command);
    }

    @Test
    public void handleRecCommand_addRecCommandWithInvalidInput_returnsNull() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        String input = "rec addrec sdefwre";

        CommandCreator commandCreator = new RecurringExpenseCommandCreator(input, expensesList, expenseList);
        Command command = commandCreator.createCommand();

        assertNull(command);
    }

    @Test
    public void handleRecCommand_viewExpensesCommandWithValidInput_createsRecurringExpenseCommand() {
        Parser parser = new Parser();
        ExpenseList expenseList = new ExpenseList();
        RecurringExpensesList expensesList = new RecurringExpensesList();
        expensesList.addNewRecurringList("Entertainment");
        String input = "rec viewexpenses 1";

        CommandCreator commandCreator = new RecurringExpenseCommandCreator(input, expensesList, expenseList);
        Command command = commandCreator.createCommand();

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

        CommandCreator commandCreator = new RecurringExpenseCommandCreator(input, expensesList, expenseList);
        Command command = commandCreator.createCommand();

        assertNull(command);
    }
}
