package brokeculator.parser;

import brokeculator.command.AddCommand;
import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class AddParserTest {
    @Test
    void parseInput_stringCorrectFormat_addCommand() {
        String input = "add /n test1 /d 1900 /a 10 /c category1";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(AddCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringNoCategory_addCommand() {
        String input = "add /n test1 /d 1900 /a 10";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(AddCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringEmptyCategory_addCommand() {
        String input = "add /n test1 /d 1900 /a 10 /c";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(AddCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringInvalidAmount_invalidCommand() {
        String input = "add /n test1 /d 1900 /a a /c category1";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringEmptyDescription_invalidCommand() {
        String input = "add /n /d 1900 /a 10 /c category1";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
}
