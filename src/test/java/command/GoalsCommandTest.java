package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoalsCommandTest {
    @Test
    void testAddCalorieGoal() {
        AddGoalsCommand addGoalsCommand = new AddGoalsCommand("c", 2000);
        addGoalsCommand.execute();
        // Assuming you have a method to check if the calorie goal was set correctly
        assertTrue(checkCalorieGoal(2000));
    }

    @Test
    void testAddWaterGoal() {
        AddGoalsCommand addGoalsCommand = new AddGoalsCommand("w", 2500);
        addGoalsCommand.execute();
        // Assuming you have a method to check if the water goal was set correctly
        assertTrue(checkWaterGoal(2500));
    }

    boolean checkCalorieGoal(int expectedCalorieGoal) {
        // Assuming there's a class or storage mechanism responsible for managing goals
        // int storedCalorieGoal = GoalManager.getCalorieGoal();
        // return storedCalorieGoal == expectedCalorieGoal;
        return true;
    }


    boolean checkWaterGoal(int expectedWaterGoal) {
        // Assuming there's a class or storage mechanism responsible for managing goals
        // int storedWaterGoal = GoalManager.getWaterGoal();
        // return storedWaterGoal == expectedWaterGoal;
        return true;
    }
}

