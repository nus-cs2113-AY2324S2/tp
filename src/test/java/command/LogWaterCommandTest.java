package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class LogWaterCommandTest {

    @Test
    void testValidQuantity() {
        LogWaterCommand logWaterCommand = new LogWaterCommand("500");
        assertDoesNotThrow(logWaterCommand::execute);
    }

    @Test
    void testInvalidQuantity() {
        LogWaterCommand logWaterCommand = new LogWaterCommand("abc");
        ActiveEdgeException exception = assertThrows(ActiveEdgeException.class, logWaterCommand::execute);
        assertEquals("Invalid water quantity. " +
                "Please provide a valid integer.", exception.getMessage());
    }

    @Test
    void testNonPositiveQuantity() throws ActiveEdgeException {
        LogWaterCommand logWaterCommand = new LogWaterCommand("-100");
        logWaterCommand.execute();
    }
}
