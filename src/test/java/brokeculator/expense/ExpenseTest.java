package brokeculator.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
        assertEquals("FOOD", testExpense.getCategory());
    }

    @Test
    void testGetExpenseFromFile() {
        try {
            String stringRepresentation = testExpense.getStringRepresentation();
            Expense expenseFromFile = Expense.getExpenseFromFile(stringRepresentation);
            assertEquals("Lunch", expenseFromFile.getDescription());
            assertEquals(12.50, expenseFromFile.getAmount());
            assertEquals("today", expenseFromFile.getDate());
            assertEquals("FOOD", expenseFromFile.getCategory());
        } catch (Exception e) {
            fail();
        }
    }
}
