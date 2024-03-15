package supertracker.parser;

import org.junit.jupiter.api.Test;
import supertracker.command.Command;
import supertracker.command.InvalidCommand;
import supertracker.command.NewCommand;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {
    @Test
    public void parseCommand_validNewCommandInput_newCommand() {
        String[] inputs = {"new n/apple q/50 p/99.5", "new p/99.5 q/23 n/elephant", "new q/88 n/banana p/213"};

        for (String input : inputs) {
            Command resultCommand = Parser.parseCommand(input);
            assertInstanceOf(NewCommand.class, resultCommand);
        }
    }

    @Test
    public void parseCommand_invalidCommandInput_invalidCommand() {
        String[] inputs = {"abcdefg", "1239", "newnew n/j q/2 p/123", "elephant"};

        for (String input : inputs) {
            Command resultCommand = Parser.parseCommand(input);
            assertInstanceOf(InvalidCommand.class, resultCommand);
        }
    }
}
