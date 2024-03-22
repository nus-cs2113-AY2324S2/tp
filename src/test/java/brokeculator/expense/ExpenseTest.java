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
        assertEquals("food", testExpense.getCategory());
    }

    @Test
    void testGetStringRepresentation() {
        assertEquals("--expense--Lunch $12.50 (today) [FOOD]", testExpense.getStringRepresentation());
    }

    @Test
    void testGetExpenseFromFile() {
        String fileString = "Lunch $12.50 (today) [FOOD]";
        try {
            Expense expenseFromFile = Expense.getExpenseFromFile(fileString);
            assertEquals("Lunch", expenseFromFile.getDescription());
            assertEquals(12.50, expenseFromFile.getAmount());
            assertEquals("today", expenseFromFile.getDate());
            assertEquals("FOOD", expenseFromFile.getCategory());
        } catch (Exception e) {
            fail();
        }
    }
}
