package budgetbuddy.transaction.type;

import budgetbuddy.account.Account;
import budgetbuddy.categories.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account();
    }

    @Test
    public void testTransactionConstructor() {
        Transaction transaction = new Income("Groceries", 50.0f, "14-03-2024",
                new Account());
        assertEquals("Groceries", transaction.getDescription());
        assertEquals(50.0f, transaction.getAmount(), 0.001);
        LocalDate date = LocalDate.parse("14-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        assertEquals(date, transaction.getDate());
    }

    @Test
    public void testGetDescription() {
        Transaction transaction = new Income("Groceries", 50.0f, "14-03-2024",
                account);
        assertEquals("Groceries", transaction.getDescription());
    }

    @Test
    public void testGetAmount() {
        Transaction transaction = new Income("Groceries", 50.0f, "14-03-2024",
                account);
        assertEquals(50.0f, transaction.getAmount(), 0.001);
    }

    @Test
    public void testGetCategory() {
        Transaction transaction = new Income("Groceries", 50.0f, "14-03-2024",
                account);
        transaction.setCategory(Category.fromNumber(1));
        assertEquals("Dining", transaction.getCategory().getCategoryName());
    }

    @Test
    public void testGetDate() {
        Transaction transaction = new Income("Groceries", 50.0f, "14-03-2024",
                account);
        LocalDate date = LocalDate.parse("14-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        assertEquals(date, transaction.getDate());
    }

    @Test
    public void testToString() {
        Transaction transaction = new Income("Groceries", 50.0f, "14-03-2024",
                account);
        transaction.setCategory(Category.fromNumber(1));
        String expected = " Transaction Type: Income |  Description: Groceries |  Date: 2024-03-14 |  Amount: 50.0 | " +
                " Category: Dining";
        assertEquals(expected, transaction.toString());
    }
}
