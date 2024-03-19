package seedu.bookbuddy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class BookBuddyTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testPrintWelcomeMessage() {
        BookBuddy.printWelcomeMessage();
        String expectedOutput = "Hello! We are BookBuddy!\nHow can I help you today?\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testPrintExitMessage() {
        BookBuddy.printExitMessage();
        String expectedOutput = "Thank you for using BookBuddy! Hope to see you again!\n";
        assertEquals(expectedOutput, outContent.toString());
    }

}
