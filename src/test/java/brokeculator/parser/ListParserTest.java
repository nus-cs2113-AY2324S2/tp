package brokeculator.parser;

import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;
import brokeculator.command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ListParserTest {
    @Test
    void parseInput_stringCorrectFormatNoNumber_listCommand() {
        String input = "list";
        Command resultCommand = ListParser.parseInput(input);
        assertInstanceOf(ListCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringCorrectFormatWithNumber_listCommand() {
        String input = "list 1";
        Command resultCommand = ListParser.parseInput(input);
        assertInstanceOf(ListCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringInvalidNumber_invalidCommand() {
        String input = "list a";
        Command resultCommand = ListParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringEmptyNumber_invalidCommand() {
        String input = "list ";
        Command resultCommand = ListParser.parseInput(input);
        assertInstanceOf(ListCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringHexadecimalNumber_invalidCommand() {
        String input = "list ff";
        Command resultCommand = ListParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringNanNumber_invalidCommand() {
        String input = "list NaN";
        Command resultCommand = ListParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
}
