package seedu.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ExpenseTest {
    LocalDateTime testDate = LocalDateTime.of(2023, 3, 15, 14, 30);
    Expense testExpense = new Expense("Lunch", 12.50, testDate, "food");

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
        assertEquals(testDate, testExpense.getDate());
    }

    @Test
    void testGetCategory() {
        assertEquals("food", testExpense.getCategory());
    }

    @Test
    void testGetStringRepresentation() {
        String expected = "--expense--Lunch: $12.50 (15 March 2023, 02:30 PM) [FOOD]";
        assertEquals(expected, testExpense.getStringRepresentation());
    }
}
