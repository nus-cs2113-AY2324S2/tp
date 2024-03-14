package parser;
import org.junit.jupiter.api.Test;
import transactions.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParseTransaction() {
        Parser parser = new Parser();
        Transaction transaction = parser.parseTransaction("add /n/Shopping /$/50 /d/14-03-2024 /c/Personal");
        assertEquals("Shopping", transaction.getDescription());
        assertEquals(50.0f, transaction.getAmount(), 0.001);
        assertEquals(LocalDate.parse("14-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), transaction.getDate());
        assertEquals("Personal", transaction.getCategory());
    }
}