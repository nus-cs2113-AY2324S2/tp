package Command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogMealsTest {
    @Test
    public void testToString() {
        LogMealCommand logMealCommand = new LogMealCommand("Sushi", 3, 1050);
        assertEquals("Sushi", logMealCommand.getDescription());
        assertEquals(3, logMealCommand.getServings());
        assertEquals(1050, logMealCommand.getMealCalories());
    }
}
