package transactions;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {
    @Test
    public void testTransactionConstructor() {
        Transaction transaction = new Transaction("Groceries", 50.0f, "Food", "14-03-2024");
        assertEquals("Groceries", transaction.getDescription());
        assertEquals(50.0f, transaction.getAmount(), 0.001);
        assertEquals("Food", transaction.getCategory());
        LocalDate date = LocalDate.parse("14-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        assertEquals(date, transaction.getDate());
    }

    @Test
    public void testGetDescription() {
        Transaction transaction = new Transaction("Groceries", 50.0f, "Food", "14-03-2024");
        assertEquals("Groceries", transaction.getDescription());
    }

    @Test
    public void testGetAmount() {
        Transaction transaction = new Transaction("Groceries", 50.0f, "Food", "14-03-2024");
        assertEquals(50.0f, transaction.getAmount(), 0.001);
    }

    @Test
    public void testGetCategory() {
        Transaction transaction = new Transaction("Groceries", 50.0f, "Food", "14-03-2024");
        assertEquals("Food", transaction.getCategory());
    }

    @Test
    public void testGetDate() {
        Transaction transaction = new Transaction("Groceries", 50.0f, "Food", "14-03-2024");
        LocalDate date = LocalDate.parse("14-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        assertEquals(date, transaction.getDate());
    }

    @Test
    public void testToString() {
        Transaction transaction = new Transaction("Groceries", 50.0f, "Food", "14-03-2024");
        String expected = " Description: Groceries |  Date: 2024-03-14 |  Amount: 50.0 |  Category: Food";
        assertEquals(expected, transaction.toString());
    }
}
