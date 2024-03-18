package ActiveEdge.Command;

import ActiveEdge.Command.ActiveEdgeException;
import ActiveEdge.Command.LogWaterCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogWaterCommandTest {

    @Test
    void testExecute_ValidQuantity() {
        LogWaterCommand logWaterCommand = new LogWaterCommand("500");
        assertDoesNotThrow(logWaterCommand::execute);
    }

    @Test
    void testExecute_InvalidQuantity() {
        LogWaterCommand logWaterCommand = new LogWaterCommand("abc");
        ActiveEdgeException exception = assertThrows(ActiveEdgeException.class, logWaterCommand::execute);
        assertEquals("Invalid water quantity. Please provide a valid integer.", exception.getMessage());
    }

    @Test
    void testExecute_NonPositiveQuantity() throws ActiveEdgeException {
        LogWaterCommand logWaterCommand = new LogWaterCommand("-100");
        logWaterCommand.execute();
    }
}
