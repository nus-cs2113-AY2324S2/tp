package seedu.stockpal.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.stockpal.exceptions.StockPalException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.stockpal.common.Messages.LINE_SEPARATOR;

public class HelpCommandTest {
    private static final String HELP_PAGE_TEST_EXPECTED_TEXT_FILE = "src/test/data/HelpCommandTest/HelpPageTest.txt";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void printHelpPage() throws StockPalException, IOException {
        HelpCommand command = new HelpCommand();
        command.execute();
        String expectedOutput = new String(Files.readAllBytes(Paths.get(HELP_PAGE_TEST_EXPECTED_TEXT_FILE)));
        expectedOutput = expectedOutput.replace("\n", LINE_SEPARATOR);
        assertEquals(expectedOutput, outContent.toString());
    }
}
