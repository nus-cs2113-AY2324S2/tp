package parser;
import activeedge.parser.Parser;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void testHelpCommandPrintsHelpMessage() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Parser parser = new Parser();
        parser.handleInput("help");

        String expectedOutputPart = "Welcome to the Health Tracker Bot";
        assertTrue(outContent.toString().contains(expectedOutputPart));

        System.setOut(System.out);
    }
}
