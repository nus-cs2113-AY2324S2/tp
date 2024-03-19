package brokeculator.parser;

import brokeculator.command.SummariseCommand;
import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class SummariseParserTest {
    @Test
    public void parseInput_stringSummarise_summariseCommand() {
        String input = "summarise";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(SummariseCommand.class, resultCommand);
    }

    @Test
    public void parseInput_stringFromZero_summariseCommand() {
        String input = "summarise /from 0";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(SummariseCommand.class, resultCommand);
    }

    @Test
    public void parseInput_stringFromPosInt_summariseCommand() {
        String input = "summarise /from 2";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(SummariseCommand.class, resultCommand);
    }

    @Test
    public void parseInput_stringFromNegInt_invalidCommand() {
        String input = "summarise /from -1";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }

    @Test
    public void parseInput_stringToZero_summariseCommand() {
        String input = "summarise /to 0";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(SummariseCommand.class, resultCommand);
    }
}
