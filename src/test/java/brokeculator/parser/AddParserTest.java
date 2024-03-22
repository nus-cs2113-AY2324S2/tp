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
    void parseInput_stringEmptyCategoryWithSpace_addCommand() {
        String input = "add /n test1 /d 1900 /a 10 /c ";
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
    @Test
    void parseInput_stringEmptyDate_invalidCommand() {
        String input = "add /n test1 /d /a 10 /c category1";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringEmptyAmount_invalidCommand() {
        String input = "add /n test1 /d 1900 /a /c category1";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringInvalidDateOptionFormat_invalidCommand() {
        String input = "add /n test1/d1900 /a 10 /c category1";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringInvalidAmountOptionFormat_invalidCommand() {
        String input = "add /n test1 /d 1900 /a10 /c category1";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringInvalidCategoryOptionFormat_invalidCommand() {
        String input = "add /n test1 /d 1900 /a 10 /ccategory1";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringInvalidNameOptionFormat_invalidCommand() {
        String input = "add /ntest1 /d 1900 /a 10 /c category1 /c category2";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringAmountExtraDecimal_invalidCommand() {
        String input = "add /n test1 /d 1900 /a 10.0.0 /c category1";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringAmountInfinity_invalidCommand() {
        String input = "add /n test1 /d 1900 /a Infinity /c category1";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringAmountNegative_invalidCommand() {
        String input = "add /n test1 /d 1900 /a -10 /c category1";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringAmountNan_invalidCommand() {
        String input = "add /n test1 /d 1900 /a -10.0 /c category1";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringWrongAmountOptionOrder_addCommand() {
        String input = "add /n test1 /a 10 /d 1900 /c category1";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(AddCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringWrongCategoryOptionOrder_addCommand() {
        String input = "add /c category1 /n test1 /a 10 /d 1900 ";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(AddCommand.class, resultCommand);
    }
    @Test
    void parseInput_emptyOptionFields_invalidCommand() {
        String input = "add /n /d /a /c";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_emptyOptionFieldsNoSpaces_invalidCommand() {
        String input = "add /n/d/a/c";
        Command resultCommand = AddParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
}
