package ActiveEdge.Command;

import ActiveEdge.Task.WaterTask;
import ActiveEdge.Task.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class LogWaterCommandTest {

    @Test
    public void testExecuteWithValidQuantity() throws ActiveEdgeException {
        // Arrange
        String quantityString = "10";
        LogWaterCommand logWaterCommand = new LogWaterCommand(quantityString);

        // Act
        logWaterCommand.execute();

        // Assert
        List<WaterTask> tasksList = TaskList.get();
        assertEquals(1, tasksList.size());
        assertEquals(10, tasksList.get(0).getQuantity());
    }

    @Test
    public void testExecuteWithInvalidQuantity() {
        // Arrange
        String quantityString = "invalid";

        // Act & Assert
        assertThrows(ActiveEdgeException.class, () -> {
            LogWaterCommand logWaterCommand = new LogWaterCommand(quantityString);
            logWaterCommand.execute();
        });
    }

    @Test
    public void testExecuteWithZeroQuantity() {
        // Arrange
        String quantityString = "0";

        // Act & Assert
        assertDoesNotThrow(() -> {
            LogWaterCommand logWaterCommand = new LogWaterCommand(quantityString);
            logWaterCommand.execute();
        });
        // Verify that no task is added
        assertEquals(0, TaskList.get().size());
    }

    @Test
    public void testExecuteWithNegativeQuantity() {
        // Arrange
        String quantityString = "-10";

        // Act & Assert
        assertDoesNotThrow(() -> {
            LogWaterCommand logWaterCommand = new LogWaterCommand(quantityString);
            logWaterCommand.execute();
        });
        // Verify that no task is added
        assertEquals(0, TaskList.get().size());
    }
}
