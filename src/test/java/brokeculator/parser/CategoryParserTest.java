package brokeculator.parser;
import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import brokeculator.command.CategoryCommand;
public class CategoryParserTest {
    @Test
    void parseInput_stringAddCorrectFormat_addCommand() {
        String input = "category add category1";
        Command resultCommand = CategoryParser.parseInput(input);
        assertInstanceOf(CategoryCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringAddEmptyCategory_invalidCommand() {
        String input = "category add ";
        Command resultCommand = CategoryParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringAddEmptyCategoryWithSpace_invalidCommand() {
        String input = "category add  ";
        Command resultCommand = CategoryParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringDeleteCorrectFormat_deleteCommand() {
        String input = "category delete category1";
        Command resultCommand = CategoryParser.parseInput(input);
        assertInstanceOf(CategoryCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringDeleteEmptyCategory_invalidCommand() {
        String input = "category delete ";
        Command resultCommand = CategoryParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringDeleteEmptyCategoryWithSpace_invalidCommand() {
        String input = "category delete  ";
        Command resultCommand = CategoryParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringListCorrectFormat_listCommand() {
        String input = "category list";
        Command resultCommand = CategoryParser.parseInput(input);
        assertInstanceOf(CategoryCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringInvalidList_invalidCommand() {
        String input = "category list invalid";
        Command resultCommand = CategoryParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringInvalidSubcommand_invalidCommand() {
        String input = "category invalid";
        Command resultCommand = CategoryParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
    @Test
    void parseInput_stringEmptySubcommand_invalidCommand() {
        String input = "category ";
        Command resultCommand = CategoryParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
}
