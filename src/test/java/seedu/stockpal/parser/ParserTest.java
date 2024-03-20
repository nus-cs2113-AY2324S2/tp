package seedu.stockpal.parser;

import org.junit.jupiter.api.Test;
import seedu.stockpal.commands.HelpCommand;
import seedu.stockpal.commands.ListCommand;
import seedu.stockpal.commands.ExitCommand;
import seedu.stockpal.exceptions.InvalidCommandException;
import seedu.stockpal.exceptions.InvalidFormatException;
import seedu.stockpal.exceptions.UnsignedIntegerExceededException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private final Parser testParser = new Parser(null, null);
    private String testInput;
    @Test
    public void parseCommand_helpCommand_success()
            throws InvalidCommandException, InvalidFormatException, UnsignedIntegerExceededException {
        testInput = "help";
        assertEquals(HelpCommand.class, testParser.parseCommand(testInput).getClass());
    }
    @Test
    public void parseCommand_listCommand_success()
            throws InvalidCommandException, InvalidFormatException, UnsignedIntegerExceededException {
        testInput = "list";
        assertEquals(ListCommand.class, testParser.parseCommand(testInput).getClass());
    }
    @Test
    public void parseCommand_exitCommand_success()
            throws InvalidCommandException, InvalidFormatException, UnsignedIntegerExceededException {
        testInput = "exit";
        assertEquals(ExitCommand.class, testParser.parseCommand(testInput).getClass());
    }

    /*
    @Test
    public void parseCommand_newCommandWithoutPriceWithoutDescription_success() {
        try {
            testInput = "new n/Test name q/123";
            Command command = testParser.parseCommand(testInput);
            assertEquals(NewCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void parseCommand_newCommandWithPriceWithoutDescription_success() {
        try {
            testInput = "new n/Test name q/123 p/4.56";
            Command command = testParser.parseCommand(testInput);
            assertEquals(NewCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }
    @Test
    public void parseCommand_newCommandWithoutPriceWithDescription_success() {
        try {
            testInput = "new n/Test name q/123 d/Test description";
            Command command = testParser.parseCommand(testInput);
            assertEquals(NewCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_newCommandWithPriceWithDescription_success() {
        try {
            testInput = "new n/Test Name q/123 p/4.56 d/Test description";
            Command command = testParser.parseCommand(testInput);
            assertEquals(NewCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }
    */
}
