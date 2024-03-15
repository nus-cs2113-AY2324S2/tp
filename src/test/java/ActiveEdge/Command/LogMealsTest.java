package ActiveEdge.Command;

import ActiveEdge.Task.LogMeals;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogMealsTest {

    @Test
    public void testAddCalories() {
        LogMeals logMeals = new LogMeals("Test Meal", "2", "100");
        int totalCalories = logMeals.addCalories();

        assertEquals(200, totalCalories);
    }

    @Test
    public void testToString() {
        LogMeals logMeals = new LogMeals("Test Meal", "2", "100");

        String result = logMeals.toString();

        String expected = "You've logged 2 serving of Test Meal.\n" +
                "Estimated calories: 200\n" +
                "Total calories: 0"; 
        assertEquals(expected, result);
    }
}
