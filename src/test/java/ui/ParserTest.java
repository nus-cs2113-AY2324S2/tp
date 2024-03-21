package ui;

import command.CommandType;
import exception.CommandInputException;
import exception.JobSelectException;
import exception.NameInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    @Test
    void parseName_validInput_returnsName() throws NameInputException {
        assertEquals("John", Parser.parseName("John"));
    }

    @Test
    void parseName_invalidInput_throwsException() {
        assertThrows(NameInputException.class, () -> Parser.parseName("John123"));
    }

    @Test
    void parseName_nameTooLong_throwsException() {
        assertThrows(NameInputException.class, () -> Parser.parseName("JohnJohnJohnJohnJohn"));
    }

    @Test
    void parseCareer_validInput_returnsCareer() throws JobSelectException {
        assertEquals("Robotics", Parser.parseCareer("/r"));
    }

    @Test
    void parseCareer_invalidInput_throwsException() {
        assertThrows(JobSelectException.class, () -> Parser.parseCareer("Robot"));
    }

    @Test
    void parseCommand_validInput_returnsCommandType() throws CommandInputException {
        assertEquals(CommandType.WORK, Parser.parseCommand("work"));
    }

    @Test
    void parseCommand_invalidInput_throwsException() {
        assertThrows(CommandInputException.class, () -> Parser.parseCommand("walk"));
    }

    @Test
    void parseCommand_validInputWithSpace_returnsCommandType() throws CommandInputException {
        assertEquals(CommandType.REST, Parser.parseCommand("rest "));
    }

    @Test
    void parseCommand_validInputWithUpperCase_returnsCommandType() throws CommandInputException {
        assertEquals(CommandType.EXERCISE, Parser.parseCommand("EXERCISE"));
    }

    @Test
    void parseCommand_validInputWithMixedCase_returnsCommandType() throws CommandInputException {
        assertEquals(CommandType.STATUS, Parser.parseCommand("StAtUs"));
    }
}
