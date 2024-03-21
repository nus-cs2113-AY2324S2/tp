package brokeculator.parser;

import brokeculator.command.SummariseCommand;
import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class SummariseParserTest {
    @Test
    public void parseInput_inputSummarise_summariseCommand() {
        String input = "summarise";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(SummariseCommand.class, resultCommand);
    }

    @Test
    public void parseInput_inputFromZero_summariseCommand() {
        String input = "summarise /from 0";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(SummariseCommand.class, resultCommand);
    }

    @Test
    public void parseInput_inputFromPosInt_summariseCommand() {
        String input = "summarise /from 2";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(SummariseCommand.class, resultCommand);
    }

    @Test
    public void parseInput_inputFromNegInt_invalidCommand() {
        String input = "summarise /from -1";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }

    @Test
    public void parseInput_inputFromPosDouble_invalidCommand() {
        String input = "summarise /from 3.5";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }

    @Test
    public void parseInput_inputFromNegDouble_invalidCommand() {
        String input = "summarise /from -1.5";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }

    @Test
    public void parseInput_inputFromString_invalidCommand() {
        String input = "summarise /from one";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }

    @Test
    public void parseInput_inputToZero_summariseCommand() {
        String input = "summarise /to 0";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(SummariseCommand.class, resultCommand);
    }

    @Test
    public void parseInput_inputToPosInt_summariseCommand() {
        String input = "summarise /to 4";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(SummariseCommand.class, resultCommand);
    }

    @Test
    public void parseInput_inputToNegInt_invalidCommand() {
        String input = "summarise /to -2";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }

    @Test
    public void parseInput_inputToPosDouble_invalidCommand() {
        String input = "summarise /to 2.5";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }

    @Test
    public void parseInput_inputToNegDouble_invalidCommand() {
        String input = "summarise /to -4.5";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }

    @Test
    public void parseInput_inputToString_invalidCommand() {
        String input = "summarise /to three";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }

    @Test
    public void parseInput_invalidFormatLong_invalidCommand() {
        String input = "summarise /from 2 /to 3 blah";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }

    @Test
    public void parseInput_invalidFormatShort_invalidCommand() {
        String input = "summarise /to ";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }

    @Test
    public void parseInput_invalidFormatWrongOrderShort_invalidCommand() {
        String input = "summarise 5 /from";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }

    @Test
    public void parseInput_invalidFormatWrongOrderLong_invalidCommand() {
        String input = "summarise /to 9 /from 3 ";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }

    @Test
    public void parseInput_inputEndLessThanStart_invalidCommand() {
        String input = "summarise /from 2 /to 1 ";
        Command resultCommand = SummariseParser.parseInput(input);
        assertInstanceOf(InvalidCommand.class, resultCommand);
    }
}
