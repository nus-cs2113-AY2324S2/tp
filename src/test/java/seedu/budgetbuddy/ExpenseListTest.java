package seedu.budgetbuddy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ExpenseListTest {

    @Test
    public void calculateTotalExpenses_addingIntegers_success() {
        ExpenseList expenseList = new ExpenseList();
        expenseList.addExpense("Transport", "50", "Bus Fare");
        expenseList.addExpense("Food", "30", "Lunch");

        assertEquals(80, expenseList.calculateTotalExpenses());
    }

    @Test
    public void calculateTotalExpenses_addingNegativeIntegers_exceptionThrown() {
        ExpenseList expenseList = new ExpenseList();
        expenseList.addExpense("Transport", "-50", "Bus Fare");
        expenseList.addExpense("Food", "-30", "Lunch");

        try {
            assertEquals(0, expenseList.calculateTotalExpenses());
            fail();
        } catch (Exception e) {
            assertEquals("java.lang.Exception: Expenses should not be negative", e.getMessage());
        }
    }

    @Test
    public void addExpense_addingExpense_success() {
        ExpenseList expenseList = new ExpenseList();
        expenseList.addExpense("Transport", "50", "Bus Fare");
        assertEquals(1, expenseList.getExpenses().size());
    }

    @Test
    public void addExpense_addingNegativeExpense_exceptionThrown() {
        ExpenseList expenseList = new ExpenseList();
        try {
            expenseList.addExpense("Transport", "-50", "Bus Fare");
            fail();
        } catch (Exception e) {
            assertEquals("java.lang.Exception: Expenses should not be negative", e.getMessage());
        }
    }
}
