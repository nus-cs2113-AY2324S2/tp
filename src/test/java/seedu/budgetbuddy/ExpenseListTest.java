package seedu.budgetbuddy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ExpenseListTest {

    @Test
    public void calculateTotalExpenses_addingIntegers_success() {
        ExpenseList expenseList = new ExpenseList();
        expenseList.addExpense(new Expense("Transport", 50, "Bus Fare"));
        expenseList.addExpense(new Expense("Food", 30, "Lunch"));

        assertEquals(80, expenseList.calculateTotalExpenses());
    }

    @Test
    public void calculateTotalExpenses_addingNegativeIntegers_exceptionThrown() {
        ExpenseList expenseList = new ExpenseList();
        expenseList.addExpense(new Expense("Transport", -50, "Bus Fare"));
        expenseList.addExpense(new Expense("Food", -30, "Lunch"));

        try {
            assertEquals(0, expenseList.calculateTotalExpenses());
            fail();
        } catch (Exception e) {
            assertEquals("java.lang.Exception: Expenses should not be negative", e.getMessage());
        }
    }
}
