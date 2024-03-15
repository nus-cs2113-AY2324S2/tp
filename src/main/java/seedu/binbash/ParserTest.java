package seedu.binbash;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.binbash.command.ByeCommand;
import seedu.binbash.command.Command;
import seedu.binbash.command.DeleteCommand;
import seedu.binbash.command.ListCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {
    private ItemList itemList;
    private Parser parser;

    @BeforeEach
    public void setUp() {
        itemList = new ItemList();
        parser = new Parser(itemList);
    }

    @Test
    public void parseCommand_deleteCommand_returnsDeleteCommand() {
        Command command = parser.parseCommand("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parseCommand_listCommand_returnsListCommand() {
        Command command = parser.parseCommand("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parseCommand_unknownCommand_returnsByeCommand() {
        Command command = parser.parseCommand("unknown");
        assertTrue(command instanceof ByeCommand);
    }

    @Test
    public void parseDeleteCommand_validInput_returnsDeleteCommand() {
        Command command = parser.parseDeleteCommand("1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parseDeleteCommand_invalidInput_returnsNull() {
        Command command = parser.parseDeleteCommand("invalid");
        assertNull(command);
    }

    @Test
    public void parseListCommand_validInput_returnsListCommand() {
        Command command = parser.parseListCommand("");
        assertTrue(command instanceof ListCommand);
    }
}
