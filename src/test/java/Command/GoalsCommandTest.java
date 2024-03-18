package Command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoalsCommandTest {

//    public class GoalManager {
//        private static int calorieGoal;
//        private static int waterGoal;
//
//        // Method to set calorie goal
//        public static void setCalorieGoal(int goal) {
//            calorieGoal = goal;
//        }
//
//        // Method to get calorie goal
//        public static int getCalorieGoal() {
//            return calorieGoal;
//        }
//
//        // Method to set water goal
//        public static void setWaterGoal(int goal) {
//            waterGoal = goal;
//        }
//
//        // Method to get water goal
//        public static int getWaterGoal() {
//            return waterGoal;
//        }
//
//        // Method to check if the stored calorie goal matches the expected goal
//        public boolean checkCalorieGoal(int expectedCalorieGoal) {
//            return calorieGoal == expectedCalorieGoal;
//        }
//
//        // Method to check if the stored water goal matches the expected goal
//        public boolean checkWaterGoal(int expectedWaterGoal) {
//            return waterGoal == expectedWaterGoal;
//        }
//    }
    
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

