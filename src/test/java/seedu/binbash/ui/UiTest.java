package seedu.binbash.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UiTest {
    private static final String LINE_DIVIDER = "-------------------------------------------------------------";

    private Ui ui;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testTalk() {
        String testLine = "this is a test line of text.";
        ui.talk(testLine);
        assertEquals(LINE_DIVIDER + System.lineSeparator() + testLine + System.lineSeparator() + LINE_DIVIDER
                + System.lineSeparator(), outContent.toString());
    }
}
