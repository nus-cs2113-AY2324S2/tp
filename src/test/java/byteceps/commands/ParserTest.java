package byteceps.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseInput_validCommand_success() {
        Parser testParser = new Parser();
        String validInput = "exercise /add deadlift";
        testParser.parseInput(validInput);

        String command = testParser.getCommand();
        String action = testParser.getAction();
        String parameter = testParser.getActionParameter();

        assertEquals(command, "exercise");
        assertEquals(action, "add");
        assertEquals(parameter, "deadlift");
    }

    @Test
    public void parseCommand_addExercise_exerciseCommand() {
        Parser testParser = new Parser();
        String validInput = "exercise /add deadlift";
        testParser.parseInput(validInput);

        String outputCommand = testParser.getCommand();
        assertEquals(outputCommand, "exercise");
    }

}
