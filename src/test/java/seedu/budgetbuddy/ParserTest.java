package seedu.budgetbuddy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
public class ParserTest {
    @Test
    public void testHandleMenuCommandWithoutIndex() {
        Parser parser = new Parser();
        Command emptyMenuCommand = parser.parseCommand("menu");

        assertInstanceOf(MenuCommand.class, emptyMenuCommand);
        assertEquals(0,((MenuCommand)emptyMenuCommand).getIndex());
    }

    @Test
    public void testHandleMenuCommandWithValidIndex() {
        Parser parser = new Parser();
        Command validMenuCommand = parser.parseCommand("menu 2");

        assertInstanceOf(MenuCommand.class, validMenuCommand);
        assertEquals(2, ((MenuCommand)validMenuCommand).getIndex());
    }

    @Test
    public void testInvalidMenuCommand() {
        Parser parser = new Parser();
        Command invalidMenuCommand = parser.parseCommand("menu invalidNumber");

        assertNull(invalidMenuCommand);
    }

    @Test
    public void testInvalidCommand() {
        Parser parser = new Parser();
        Command invalidCommand = parser.parseCommand("notACommand");

        assertNull(invalidCommand);
    }
}
