package seedu.bookbuddy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
        String expectedOutput = "Hello! We are BookBuddy!" + System.lineSeparator() +
                "How can I help you today?" + System.lineSeparator();
        String actualOutput = outContent.toString();

        // Normalize the line endings in the actual output to match the system's line separator
        String normalizedActualOutput = actualOutput.replace(System.lineSeparator(), "\n");

        // Normalize the expected output line endings to \n for comparison
        String normalizedExpectedOutput = expectedOutput.replace("\n", System.lineSeparator());

        // Convert to byte arrays to compare
        byte[] expectedBytes = normalizedExpectedOutput.getBytes();
        byte[] actualBytes = normalizedActualOutput.getBytes();

        assertArrayEquals(expectedBytes, actualBytes);
    }



    @Test
    public void testPrintExitMessage() {
        BookBuddy.printExitMessage();
        String expectedOutput = "Thank you for using BookBuddy! Hope to see you again!\n";
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

}
