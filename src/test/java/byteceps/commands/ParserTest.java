package byteceps.commands;

import org.junit.jupiter.api.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parseInput_validCommand_success() {
        Parser testParser = new Parser();
        String validInput = "exercise /add deadlift";
        testParser.parseInput(validInput);

        String command = testParser.getCommandString();
        String action = testParser.getAction();
        String parameter = testParser.getActionParameter();

        assertEquals(command, "exercise");
        assertEquals(action, "add");
        assertEquals(parameter, "deadlift");
    }

    @Test
    public void parseCommand_addExercise_ExerciseCommand() {
        Parser testParser = new Parser();
        String validInput = "exercise /add deadlift";
        testParser.parseInput(validInput);

        Command outputCommand = testParser.parseCommand();
        assertEquals(outputCommand.getClass(), ExerciseCommand.class);
    }

    @Test
    public void parseCommand_listWorkout_WorkoutCommand() {
        Parser testParser = new Parser();
        String validInput = "workout /list";
        testParser.parseInput(validInput);

        Command outputCommand = testParser.parseCommand();
        assertEquals(outputCommand.getClass(), WorkoutCommand.class);
    }

    @Test
    public void parseCommand_invalidCommand_throwException() {
        Parser testParser = new Parser();
        String validInput = "aaaaa /list";
        testParser.parseInput(validInput);


        Exception exception = assertThrows(UnsupportedOperationException.class, testParser::parseCommand);
    }
}
