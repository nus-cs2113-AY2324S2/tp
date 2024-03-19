package ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JunitParserTest {
    @Test
    public void analyzeInput_invalidInput_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> Parser.analyzeInput("invalid input"));
    }

    @Test
    public void analyzeInput_validInput_success() {
        assertEquals(CommandType.CREATE_ORDER, new Parser().analyzeInput("Create Order -menu 1"));
        assertEquals(CommandType.VIEW_ORDER, new Parser().analyzeInput("View -order 2"));
    }

    @Test
    void splitInputTest() {
        Parser parser = new Parser();
        assertArrayEquals(new String[]{"7"}, parser.splitInput(CommandType.CREATE_ORDER, "create order -menu 7"));
        assertArrayEquals(new String[]{"6"}, parser.splitInput(CommandType.VIEW_ORDER, "view -order 6"));
        assertArrayEquals(new String[]{"5"}, parser.splitInput(CommandType.EDIT_ORDER, "edit -order 5"));
    }

    @Test
    void testSplitInput_invalidInput() {
        Parser parser = new Parser();
        assertThrows(IllegalStateException.class,
                () -> parser.splitInput(CommandType.CREATE_ORDER, "create -Order -menu"));
        assertThrows(IllegalStateException.class, () -> parser.splitInput(CommandType.VIEW_ORDER, "view -order"));
        assertThrows(IllegalStateException.class, () -> parser.splitInput(CommandType.EDIT_ORDER, "edit -order"));
    }
}
