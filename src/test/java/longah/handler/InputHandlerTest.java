package longah.handler;

import longah.exception.ExceptionMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class InputHandlerTest {
    /**
     * Tests the parseInput method in InputHandler class with invalid input.
     */
    @Test
    public void parseInput_invalidCommand_exceptionThrown() {
        try {
            InputHandler.parseInput("");
            fail();
        } catch (Exception e) {
            String expected = ExceptionMessage.INVALID_COMMAND.getMessage();
            assertEquals(expected, e.getMessage());
        }
    }
}
