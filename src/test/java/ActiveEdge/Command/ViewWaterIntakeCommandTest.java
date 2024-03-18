package ActiveEdge.Command;

import ActiveEdge.Command.ViewWaterIntakeCommand;
import ActiveEdge.Task.LogWaterTask;
import ActiveEdge.Task.TaskList;
import ActiveEdge.Ui.CommandUi;
import ActiveEdge.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ViewWaterIntakeCommandTest {
    private ViewWaterIntakeCommand viewWaterIntakeCommand;
    private TaskList taskList;
    private CommandUi ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        viewWaterIntakeCommand = new ViewWaterIntakeCommand();
        taskList = new TaskList();
        ui = new CommandUi();
        storage = new Storage();
    }

    @Test
    public void testExecute() {
        taskList.addTask(new LogWaterTask(200));
        assertDoesNotThrow(() -> viewWaterIntakeCommand.execute(taskList, ui, storage));
    }
}

