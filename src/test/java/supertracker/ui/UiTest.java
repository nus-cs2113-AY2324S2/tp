package supertracker.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Test
    public void printIndent_string_expectStringWithIndentation() {
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        Ui.printIndent("test");
        System.setOut(new PrintStream(originalOut));
        String expected = "     test\n";
        String actualString = outContent.toString();
        assertEquals(expected, actualString);
    }
}

