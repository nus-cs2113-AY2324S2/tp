import ActiveEdge.Command.ViewWaterIntakeCommand;
import ActiveEdge.Task.TaskList;
import ActiveEdge.Task.WaterTask;
import ActiveEdge.Ui.CommandUi;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.mockito.Mockito.*;

public class ViewWaterIntakeCommandTest {

    @Test
    public void testExecute() throws InterruptedException {
        // Create a mock TaskList
        TaskList taskListMock = mock(TaskList.class);

        // Create a mock CommandUi
        CommandUi uiMock = mock(CommandUi.class);

        // Create a ViewWaterIntakeCommand instance with mocked dependencies
        ViewWaterIntakeCommand viewWaterIntakeCommand = new ViewWaterIntakeCommand(taskListMock, uiMock);

        // Define behavior for the mock TaskList
        WaterTask waterTask1 = new WaterTask(200);
        WaterTask waterTask2 = new WaterTask(300);
        wait(taskListMock.get()).thenReturn(new ArrayList<>(Arrays.asList(waterTask1, waterTask2)));

        // Call the execute method
        viewWaterIntakeCommand.execute();

        // Verify that the printWaterIntakeMessage method was called with the correct total water intake
        verify(uiMock).printWaterIntakeMessage(500);
    }

    private CommandUi verify(CommandUi uiMock) {
        return null;
    }
}

