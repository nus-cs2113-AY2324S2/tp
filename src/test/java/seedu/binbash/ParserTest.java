package seedu.binbash;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import seedu.binbash.command.AddCommand;
import seedu.binbash.command.Command;
import seedu.binbash.command.DeleteCommand;
import seedu.binbash.command.SearchCommand;
import seedu.binbash.command.ListCommand;
import seedu.binbash.command.ByeCommand;
import seedu.binbash.exceptions.InvalidCommandException;
import seedu.binbash.exceptions.InvalidArgumentException;
import seedu.binbash.exceptions.InvalidFormatException;

public class ParserTest {
    private ItemList itemList;
    private Parser parser;

    @BeforeEach
    public void setUp() {
        itemList = new ItemList(new ArrayList<>());
        parser = new Parser(itemList);
    }

    @Test
    public void testParseCommand_validCommandBye_returnsByeCommand() {
        try {
            Command command = parser.parseCommand("bye");
            assertTrue(command instanceof ByeCommand);
        } catch (InvalidCommandException e) {
            fail("Unexpected InvalidCommandException: " + e.getMessage());
        }
    }

    @Test
    public void testParseCommand_invalidCommand_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> parser.parseCommand("invalid"));
    }

    @Test
    public void testParseCommand_validCommandDelete_returnsDeleteCommand() throws InvalidCommandException {
        itemList.addItem("Test Item", "Test Description", 5, Optional.of(LocalDate.now()), 10.5, 7.5);
        Command command = parser.parseCommand("delete Test Item");
        assertTrue(command instanceof DeleteCommand);
    }

    public void parseAddCommand_createItemWithNoQuantityAndExpirationDate_returnsAddCommand() {
        try {
            itemList.addItem("Test Item", "Test Description", 0, Optional.empty(), 0.00, 0.00);
            Command command = parser.parseCommand("add n/Test Item d/Test Description s/0.00 c/0.00");
            assertTrue(command instanceof AddCommand);
        } catch (InvalidCommandException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void parseAddCommand_createItemWithNoQuantity_returnsAddCommand() {
        try {
            itemList.addItem("Test Item", "Test Description", 0, Optional.of(LocalDate.of(1999, 1, 1)), 0.00, 0.00);
            Command command = parser.parseCommand("add n/Test Item d/Test Description e/01-01-1999 s/0.00 c/0.00");
            assertTrue(command instanceof AddCommand);
        } catch (InvalidCommandException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void parseAddCommand_createItemWithNoExpiration_returnsAddCommand() {
        try {
            itemList.addItem("Test Item", "Test Description", 10, Optional.empty(), 0.00, 0.00);
            Command command = parser.parseCommand("add n/Test Item d/Test Description q/10 s/0.00 c/0.00");
            assertTrue(command instanceof AddCommand);
        } catch (InvalidCommandException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void parseAddCommand_createItemWithAllArguments_returnsAddCommand() {
        try {
            itemList.addItem("Test Item", "Test Description", 10, Optional.of(LocalDate.of(1999, 1, 1)), 0.00, 0.00);
            Command command = parser.parseCommand("add n/Test Item d/Test Description q/10 e/01-01-1999 s/0.00 c/0.00");
            assertTrue(command instanceof AddCommand);
        } catch (InvalidCommandException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testParseCommand_validCommandList_returnsListCommand() throws InvalidCommandException {
        Command command = parser.parseCommand("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void testParseCommand_validCommandSearch_returnsSearchCommand() throws InvalidCommandException {
        Command command = parser.parseCommand("search keyword");
        assertTrue(command instanceof SearchCommand);
    }

    @Test
    public void testParseCommand_invalidAddCommand_throwsInvalidFormatException() {
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand("add invalid format"));
    }

    @Test
    public void testParseCommand_invalidDeleteCommand_throwsInvalidArgumentException() {
        assertThrows(InvalidArgumentException.class, () -> parser.parseCommand("delete -1"));
    }

    @Test
    public void testParseCommand_invalidSearchCommand_throwsInvalidFormatException() {
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand("search"));
    }
}
