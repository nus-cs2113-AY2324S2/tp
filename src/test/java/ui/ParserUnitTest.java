package ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserUnitTest {
    @Test
    void testAnalyzeInput_validInput() {
        Parser parser = new Parser();
        assertEquals(CommandType.CREATE_ORDER, parser.analyzeInput("create order -menu 1"));
        assertEquals(CommandType.VIEW_ORDER, parser.analyzeInput("view -order 2"));
        assertEquals(CommandType.EDIT_ORDER, parser.analyzeInput("edit -order 3"));
        assertEquals(CommandType.EXIT, parser.analyzeInput("bye"));
        assertEquals(CommandType.HELP, parser.analyzeInput("help"));

        // case-insensitive
        assertEquals(CommandType.CREATE_ORDER, parser.analyzeInput("CrEaTe OrDeR -mEnU 99"));
        assertEquals(CommandType.VIEW_ORDER, parser.analyzeInput("ViEw -OrDeR 2"));
    }

    @Test
    void testSplitInput_validInput() {
        Parser parser = new Parser();
        assertArrayEquals(new String[]{"1"}, parser.splitInput(CommandType.CREATE_ORDER, "create order -menu 1"));
        assertArrayEquals(new String[]{"2"}, parser.splitInput(CommandType.VIEW_ORDER, "view -order 2"));
        assertArrayEquals(new String[]{"3"}, parser.splitInput(CommandType.EDIT_ORDER, "edit -order 3"));
    }

    @Test
    void testAnalyzeInput_invalidInput() {
        Parser parser = new Parser();

        assertThrows(IllegalArgumentException.class, () -> parser.analyzeInput("asfdhih 123"));
        assertThrows(IllegalArgumentException.class, () -> parser.analyzeInput("create order -menu 1 2 3"));
        assertThrows(IllegalArgumentException.class, () -> parser.analyzeInput("view -order 1 2 3"));
        assertThrows(IllegalArgumentException.class, () -> parser.analyzeInput("edit -order 1 2 3"));
        assertThrows(IllegalArgumentException.class, () -> parser.analyzeInput("create order -menu"));
        assertThrows(IllegalArgumentException.class, () -> parser.analyzeInput("view -order"));
        assertThrows(IllegalArgumentException.class, () -> parser.analyzeInput("edit -order"));

        /*
        assertEquals(CommandType.INVALID, Parser.analyzeInput("asfdhih 123"));
        assertEquals(CommandType.INVALID, Parser.analyzeInput("create order -menu 1 2 3"));
        assertEquals(CommandType.INVALID, Parser.analyzeInput("view -order 1 2 3"));
        assertEquals(CommandType.INVALID, Parser.analyzeInput("edit -order 1 2 3"));
        assertEquals(CommandType.INVALID, Parser.analyzeInput("create order -menu"));
        assertEquals(CommandType.INVALID, Parser.analyzeInput("view -order"));
        assertEquals(CommandType.INVALID, Parser.analyzeInput("edit -order"));
        */
    }

    @Test
    void testSplitInput_invalidInput() {
        Parser parser = new Parser();
        assertThrows(IllegalStateException.class,
                () -> parser.splitInput(CommandType.CREATE_ORDER, "create order -menu"));
        assertThrows(IllegalStateException.class, () -> parser.splitInput(CommandType.VIEW_ORDER, "view -order"));
        assertThrows(IllegalStateException.class, () -> parser.splitInput(CommandType.EDIT_ORDER, "edit -order"));
    }

}
