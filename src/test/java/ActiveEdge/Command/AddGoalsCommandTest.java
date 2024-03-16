package ActiveEdge.Command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddGoalsCommandTest {

    @Test
    void testExecute_AddCalorieGoal() {
        AddGoalsCommand addGoalsCommand = new AddGoalsCommand();
        String resultMessage = addGoalsCommand.execute("calorie", 2000);
        assertEquals("Calorie goal added: 2000", resultMessage);
        // Assuming you have a method to check if the calorie goal was set correctly
        assertTrue(checkCalorieGoal(2000));
    }

    @Test
    void testExecute_AddWaterGoal() {
        AddGoalsCommand addGoalsCommand = new AddGoalsCommand();
        String resultMessage = addGoalsCommand.execute("water", 2500);
        assertEquals("Water goal added: 2500 ml", resultMessage);
        // Assuming you have a method to check if the water goal was set correctly
        assertTrue(checkWaterGoal(2500));
    }

    boolean checkCalorieGoal(int expectedCalorieGoal) {
        // Assuming there's a class or storage mechanism responsible for managing goals
        int storedCalorieGoal = Goals.getCalorieGoal(); // Retrieve the stored calorie goal
        return storedCalorieGoal == expectedCalorieGoal;
    }
}
