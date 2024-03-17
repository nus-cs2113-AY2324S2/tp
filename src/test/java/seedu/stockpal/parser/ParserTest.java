package seedu.stockpal.parser;

import org.junit.jupiter.api.Test;
import seedu.stockpal.commands.ExitCommand;
import seedu.stockpal.commands.HelpCommand;
import seedu.stockpal.commands.ListCommand;
import seedu.stockpal.exceptions.InvalidCommandException;
import seedu.stockpal.exceptions.InvalidFormatException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {
    @Test
    public void parseCommand_helpCommand_success() throws InvalidCommandException, InvalidFormatException {
        String testInput = "help";
        Parser testParser = new Parser(null, null);

        assertInstanceOf(HelpCommand.class, testParser.parseCommand(testInput));
    }
    @Test
    public void parseCommand_listCommand_success() throws InvalidCommandException, InvalidFormatException {
        String testInput = "list";
        Parser testParser = new Parser(null, null);

        assertInstanceOf(ListCommand.class, testParser.parseCommand(testInput));
    }
    @Test
    public void parseCommand_exitCommand_success() throws InvalidCommandException, InvalidFormatException {
        String testInput = "exit";
        Parser testParser = new Parser(null, null);

        assertInstanceOf(ExitCommand.class, testParser.parseCommand(testInput));
    }
}
