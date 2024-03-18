package ActiveEdge.Command;

import ActiveEdge.Command.LogWaterCommand;
import ActiveEdge.Task.LogWaterTask;
import ActiveEdge.Task.TaskList;
import ActiveEdge.Ui.CommandUi;
import ActiveEdge.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogWaterCommandTest {
    private LogWaterCommand logWaterCommand;
    private TaskList taskList;
    private CommandUi ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        logWaterCommand = new LogWaterCommand(500);
        taskList = new TaskList();
        ui = new CommandUi();
        storage = new Storage();
    }

    @Test
    public void testExecute() {
        assertDoesNotThrow(() -> logWaterCommand.execute(taskList, ui, storage));

        assertEquals(1, taskList.getTasks().size());
        assertTrue(taskList.getTasks().get(0) instanceof LogWaterTask);
        assertEquals(500, ((LogWaterTask) taskList.getTasks().get(0)).getQuantity());
    }

    @Test
    public void testNegativeQuantity() {
        LogWaterCommand negativeQuantityCommand = new LogWaterCommand(-100);
        assertThrows(ActiveEdgeException.class, () -> negativeQuantityCommand.execute(taskList, ui, storage));
    }
}

