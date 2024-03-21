package budgetbuddy.parser;
import budgetbuddy.account.Account;
import budgetbuddy.exception.EmptyArgumentException;
import budgetbuddy.exception.InvalidTransactionTypeException;
import org.junit.jupiter.api.Test;
import budgetbuddy.transaction.type.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParseTransaction() throws InvalidTransactionTypeException, EmptyArgumentException {
        Parser parser = new Parser();
        Account account = new Account();
        Transaction transaction = parser.parseTransaction("add /t/Income /n/Shopping /$/50 /d/14-03-2024 " +
                "/c/Personal", account);
        assertEquals("Shopping", transaction.getDescription());
        assertEquals(50.0f, transaction.getAmount(), 0.001);
        assertEquals(LocalDate.parse("14-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), transaction.getDate());
        assertEquals("Personal", transaction.getCategory());
    }
}
