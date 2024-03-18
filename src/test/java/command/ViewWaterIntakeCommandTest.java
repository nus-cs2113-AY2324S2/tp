package command;

import activeedge.task.GoalTask;
import activeedge.task.Task;
import activeedge.task.WaterTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewWaterIntakeCommandTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testExecute() {
        // Mock TaskList
        ArrayList<Task> tasksList = new ArrayList<>();
        tasksList.add(new WaterTask(200)); // Adding water intake
        tasksList.add(new GoalTask("w", 1500)); // Adding water goal
        ViewWaterIntakeCommand viewWaterIntakeCommand = new ViewWaterIntakeCommand();

        // Mock TaskList and execute command
        viewWaterIntakeCommand.execute();

        // Verify output message
        assertEquals("Total water consumed today: 200 ml (13% of 1500ml goal).", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testGetTotalWaterIntake() {
        // Create a mock task list
        ArrayList<Task> tasksList = new ArrayList<>();
        tasksList.add(new WaterTask(200)); // Adding water intake
        tasksList.add(new WaterTask(300)); // Adding water intake

        ViewWaterIntakeCommand viewWaterIntakeCommand = new ViewWaterIntakeCommand();

        // Call the private method getTotalWaterIntake using reflection or make it package-private for testing
        int totalWaterIntake = viewWaterIntakeCommand.getTotalWaterIntake(tasksList);

        // Verify total water intake
        assertEquals(500, totalWaterIntake);
    }

    @Test
    public void testGetWaterGoal() {
        // Create a mock task list
        ArrayList<Task> tasksList = new ArrayList<>();
        tasksList.add(new GoalTask("c", 2000)); // Adding calorie goal
        tasksList.add(new GoalTask("w", 1500)); // Adding water goal

        ViewWaterIntakeCommand viewWaterIntakeCommand = new ViewWaterIntakeCommand();

        // Call the private method getWaterGoal using reflection or make it package-private for testing
        int waterGoal = viewWaterIntakeCommand.getWaterGoal(tasksList);

        // Verify water goal
        assertEquals(1500, waterGoal);
    }
}
