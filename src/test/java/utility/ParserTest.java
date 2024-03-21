package utility;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    /**
     * Tests the behaviour of the parseDate function with a correctly formatted string.
     */
    @Test
    void parseDate_correctDateInput_returnDate() {
        LocalDate result = Parser.parseDate("08-03-2024");
        LocalDate expected = LocalDate.of(2024, 3, 8);
        assertEquals(expected, result);
    }

    /**
     * Tests the behaviour of the parseDate function with an incorrectly formatted string.
     */
    @Test
    void parseDate_incorrectDateInput_throwException () {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));
        String invalidDate = "2024-03-08";
        Parser.parseDate(invalidDate);
        String errorMessage = outputStream.toString().trim();
        assertTrue(errorMessage.contains("Error parsing date"));
        System.setErr(System.err);
    }

}
