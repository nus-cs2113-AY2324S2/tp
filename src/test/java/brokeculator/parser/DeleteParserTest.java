package brokeculator.parser;

import brokeculator.command.DeleteCommand;
import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
public class DeleteParserTest {

    @Test
    public void parseInput_inputPosIntIndex_deleteCommand() {
        String input = "delete 1";
        Command resultCommand = DeleteParser.parseInput(input);
        assertInstanceOf(DeleteCommand.class, resultCommand);
    }

    @Test
    public void parseInput_inputPosDoubleIndex_invalidCommand() {
        String input = "delete 2.5";
        Command resultCommand = DeleteParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }

    @Test
    public void parseInput_inputNegDoubleIndex_invalidCommand() {
        String input = "delete -1.5";
        Command resultCommand = DeleteParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }

    @Test
    public void parseInput_inputNoIndex_invalidCommand() {
        String input = "delete  ";
        Command resultCommand = DeleteParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
}
