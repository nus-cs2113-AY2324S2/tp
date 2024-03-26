package command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;


public class MealTaskTest {
    @Test
    public void testToString() {
        LocalDateTime dateTime = LocalDateTime.now();
        LogMealCommand logMealCommand = new LogMealCommand("Sushi", 3, 1050, dateTime);
        assertEquals("Sushi", logMealCommand.getDescription());
        assertEquals(3, logMealCommand.getServings());
        assertEquals(1050, logMealCommand.getMealCalories());
    }
}
