package seedu.duke;

import motivationalquotes.MotivationalQuotes;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MotivationalQuotesTest {

    @Test
    void getQuote() {
        String quote = MotivationalQuotes.getQuote();

        assertNotNull(quote);
        assertFalse(quote.isEmpty());
    }
}
