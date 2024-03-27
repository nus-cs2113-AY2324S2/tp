package seedu.duke.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import seedu.duke.command.AddCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.command.GradeCommand;
import seedu.duke.command.InvalidCommand;
import seedu.duke.command.InitCommand;
import seedu.duke.command.RemoveCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.command.ViewGpaCommand;

public class ParserTest {

    @Test
    public void testGetCommand_InitCommand() {
        String userInput = "init n/John Doe";
        Command command = Parser.getCommand(userInput);
        assertTrue(command instanceof InitCommand);
    }

    @Test
    public void testGetCommand_GpaCommand() {
        String userInput = "gpa";
        Command command = Parser.getCommand(userInput);
        assertTrue(command instanceof ViewGpaCommand);
    }

    @Test
    public void testGetCommand_ViewCommand() {
        String userInput = "view";
        Command command = Parser.getCommand(userInput);
        assertTrue(command instanceof ViewCommand);
    }

    @Test
    public void testGetCommand_RemoveCommand() {
        String userInput = "remove c/CS1010";
        Command command = Parser.getCommand(userInput);
        assertTrue(command instanceof RemoveCommand);
    }

    @Test
    public void testGetCommand_AddCommand() {
        String userInput = "add c/CS1010 w/1";
        Command command = Parser.getCommand(userInput);
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void testGetCommand_GradeCommand() {
        String userInput = "grade c/CS1010 g/A";
        Command command = Parser.getCommand(userInput);
        assertTrue(command instanceof GradeCommand);
    }

    @Test
    public void testGetCommand_ByeCommand() {
        String userInput = "bye";
        Command command = Parser.getCommand(userInput);
        assertTrue(command instanceof ByeCommand);
    }

    @Test
    public void testGetCommand_InvalidCommand() {
        String userInput = "invalid command";
        Command command = Parser.getCommand(userInput);
        assertTrue(command instanceof InvalidCommand);
    }
}
