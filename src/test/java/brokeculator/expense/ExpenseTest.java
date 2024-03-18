package brokeculator.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

import brokeculator.expense.Expense;
import org.junit.jupiter.api.Test;

public class ExpenseTest {
    Expense testExpense = new Expense("Lunch", 12.50, "today", "food");

    @Test
    void testGetDescription() {
        assertEquals("Lunch", testExpense.getDescription());
    }

    @Test
    void testGetAmount() {
        assertEquals(12.50, testExpense.getAmount());
    }

    @Test
    void testGetDate() {
        assertEquals("today", testExpense.getDate());
    }

    @Test
    void testGetCategory() {
        assertEquals("food", testExpense.getCategory());
    }
}
