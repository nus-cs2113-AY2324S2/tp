package command;

import exception.CommandInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandTypeTest {

    @Test
    void analyseInput_validCommand_returnsCommandType() throws CommandInputException {
        assertEquals(CommandType.EXIT, CommandType.analyseInput("bye"));
        assertEquals(CommandType.WORK, CommandType.analyseInput("work"));
        assertEquals(CommandType.REST, CommandType.analyseInput("rest"));
        assertEquals(CommandType.EXERCISE, CommandType.analyseInput("exercise"));
        assertEquals(CommandType.STATUS, CommandType.analyseInput("status"));
    }

    @Test
    void analyseInput_invalidCommand_throwsException() {
        assertThrows(CommandInputException.class, () -> CommandType.analyseInput("walk"));
    }

    @Test
    void analyseInput_validCommandWithSpace_returnsCommandType() throws CommandInputException {
        assertEquals(CommandType.REST, CommandType.analyseInput("rest "));
    }

    @Test
    void analyseInput_validCommandWithUpperCase_returnsCommandType() throws CommandInputException {
        assertEquals(CommandType.EXERCISE, CommandType.analyseInput("EXERCISE"));
    }

    @Test
    void analyseInput_validCommandWithMixedCase_returnsCommandType() throws CommandInputException {
        assertEquals(CommandType.EXERCISE, CommandType.analyseInput("eXeRcIsE"));
    }
}
