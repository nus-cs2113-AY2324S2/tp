package ActiveEdge.Command;

import ActiveEdge.Task.LogMeals;
import ActiveEdge.Parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogMealsTest {
    @Test
    public void testToString() {
        LogMealCommand logMealCommand = new LogMealCommand("Sushi", 3, 1050);
        assertEquals("Sushi", logMealCommand.description);
        assertEquals(3, logMealCommand.servings);
        assertEquals(1050, logMealCommand.mealCalories);
    }
}
