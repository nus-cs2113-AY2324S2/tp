package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FAPTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpStreams() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String buildExpectedOutput(String... lines) {
        return String.join(System.lineSeparator(), lines) + System.lineSeparator();
    }

    @Test
    public void testInit() {
        String simulatedUserInput = "init n/bob" + System.lineSeparator();
        provideInput(simulatedUserInput);

        FAP.main(new String[]{});

        String output = testOut.toString().replace(System.lineSeparator(), "\n");

        String expectedOutput = buildExpectedOutput(
                "Hello! This is your CEG Future Academic Planner!",
                "What can I do for you?",
                "__________________________________________________",
                "__________________________________________________",
                "Hello bob!",
                "What would you like to do today?",
                "__________________________________________________",
                "An error occurred: No line found"
        ).replace(System.lineSeparator(), "\n");

        assertTrue(output.contains(expectedOutput));
    }

    @Test
    public void invalidInitTest() {
        String simulatedUserInput = "init n/" + System.lineSeparator();
        provideInput(simulatedUserInput);
        FAP.main(new String[]{});
        String output = testOut.toString().replace(System.lineSeparator(), "\n");

        String expectedOutput = buildExpectedOutput(
                "__________________________________________________",
                "Hello! This is your CEG Future Academic Planner!",
                "What can I do for you?",
                "__________________________________________________",
                "__________________________________________________",
                "init command: Error in name",
                "__________________________________________________",
                "An error occurred: No line found"
        ).replace(System.lineSeparator(), "\n");

        assertTrue(output.contains(expectedOutput));
    }
}
