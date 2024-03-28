package seedu.duke.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    private String buildExpectedOutput(String... lines) {
        return String.join(System.lineSeparator(), lines) + System.lineSeparator();
    }
    @Test
    void printGreeting() {
        Ui.printGreeting();
        String expectedOutput = buildExpectedOutput(
                "__________________________________________________",
                "Hello! This is your CEG Future Academic Planner!",
                "What can I do for you?"
        );
        assertEquals(expectedOutput , outContent.toString());
    }

    @Test
    void printHyphens() {
        Ui.printHyphens();
        assertEquals("__________________________________________________", outContent.toString().trim());
    }

    @Test
    void printExit() {
        Ui.printExit();
        assertEquals("Bye. Enjoy your studies!", outContent.toString().trim());
    }
}
