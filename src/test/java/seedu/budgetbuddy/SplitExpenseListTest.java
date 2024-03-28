package seedu.budgetbuddy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.budgetbuddy.exception.BudgetBuddyException;




public class SplitExpenseListTest {

    @Test
    public void addSplitExpense_addingsplitexpense_success() throws BudgetBuddyException {
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        splitExpenseList.addSplitExpense("12", "12", "Lunch");

        assertEquals(1, splitExpenseList.getSplitExpenses().size());
        assertEquals("12", splitExpenseList.getSplitExpenses().get(0).getNumberOfPeople());
        assertEquals("Lunch", splitExpenseList.getSplitExpenses().get(0).getDescription());
    }

    @Test
    public void addSplitExpense_invalidAmount_exceptionThrown() throws BudgetBuddyException{
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        try {
            splitExpenseList.addSplitExpense("abc", "12", "Lunch");
            fail();
        } catch (BudgetBuddyException e) {
            assertEquals("Invalid amount format. Amount should be a number.", e.getMessage());
        }
    }

    @Test
    public void addSplitExpense_invalidNumberOfPeople_exceptionThrown() throws BudgetBuddyException{
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        try {
            splitExpenseList.addSplitExpense("12", "abc", "Lunch");
            fail();
        } catch (BudgetBuddyException e) {
            assertEquals("Number of people should be a number", e.getMessage());
        }
    }

    @Test @Disabled
    public void addSplitExpense_nullDescription_exceptionThrown() throws BudgetBuddyException{
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        try {
            splitExpenseList.addSplitExpense("12", "12", null);
            fail();
        } catch (BudgetBuddyException e) {
            assertEquals("Description should not be null", e.getMessage());
        }
    }

    @Test
    public void addSplitExpense_negativeAmount_exceptionThrown() throws BudgetBuddyException{ 
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        try {
            splitExpenseList.addSplitExpense("-12", "12", "Lunch");
            fail();
        } catch (BudgetBuddyException e) {
            assertEquals("Expenses should not be negative.", e.getMessage());
        }
    }

    @Test
    public void addSplitExpense_negativeNumberOfPeople_exceptionThrown() throws BudgetBuddyException{
        SplitExpenseList splitExpenseList = new SplitExpenseList();
        try {
            splitExpenseList.addSplitExpense("12", "-12", "Lunch");
            fail();
        } catch (BudgetBuddyException e) {
            assertEquals("Number of people should be a positive number", e.getMessage());
        }
    }
}
