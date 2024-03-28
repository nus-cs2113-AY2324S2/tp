package seedu.stockpal.parser;

import org.junit.jupiter.api.Test;
import seedu.stockpal.commands.Command;
import seedu.stockpal.commands.HelpCommand;
import seedu.stockpal.commands.ListCommand;
import seedu.stockpal.commands.ExitCommand;
import seedu.stockpal.commands.NewCommand;

import seedu.stockpal.exceptions.InvalidCommandException;
import seedu.stockpal.exceptions.InvalidFormatException;
import seedu.stockpal.exceptions.UnsignedIntegerExceededException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    private final Parser testParser = new Parser();
    private String testInput;
    @Test
    public void parseInput_helpCommand_success()
            throws InvalidCommandException, InvalidFormatException, UnsignedIntegerExceededException {
        testInput = "help";
        assertEquals(HelpCommand.class, testParser.parseInput(testInput).getClass());
    }
    @Test
    public void parseInput_listCommand_success()
            throws InvalidCommandException, InvalidFormatException, UnsignedIntegerExceededException {
        testInput = "list";
        assertEquals(ListCommand.class, testParser.parseInput(testInput).getClass());
    }
    @Test
    public void parseInput_exitCommand_success()
            throws InvalidCommandException, InvalidFormatException, UnsignedIntegerExceededException {
        testInput = "exit";
        assertEquals(ExitCommand.class, testParser.parseInput(testInput).getClass());
    }

    @Test
    public void parseInput_newCommandWithoutPriceWithoutDescription_success() {
        try {
            testInput = "new n/Test name q/123";
            Command command = testParser.parseInput(testInput);
            assertEquals(NewCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseInput_newCommandWithPriceWithoutDescription_success() {
        try {
            testInput = "new n/Test name q/123 p/4.56";
            Command command = testParser.parseInput(testInput);
            assertEquals(NewCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }
    @Test
    public void parseInput_newCommandWithoutPriceWithDescription_success() {
        try {
            testInput = "new n/Test Name q/123 d/Test description";
            Command command = testParser.parseInput(testInput);
            assertEquals(NewCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseInput_newCommandWithPriceWithDescription_success() {
        try {
            testInput = "new n/Test Name q/123 p/4.56 d/test description";
            Command command = testParser.parseInput(testInput);
            assertEquals(NewCommand.class, command.getClass());
        } catch (InvalidCommandException | InvalidFormatException | UnsignedIntegerExceededException e) {
            fail();
        }
    }

    @Test
    public void parseInput_newCommandWithoutName_invalidFormatExceptionThrown() {
        testInput = "new q/123 p/4.56 d/Test description";
        assertThrows(InvalidFormatException.class, () -> testParser.parseInput(testInput));
    }

    @Test
    public void parseInput_newCommandWithoutQuantity_invalidFormatExceptionThrown() {
        testInput = "new n/Test Name p/4.56 d/Test description";
        assertThrows(InvalidFormatException.class, () -> testParser.parseInput(testInput));
    }
}
