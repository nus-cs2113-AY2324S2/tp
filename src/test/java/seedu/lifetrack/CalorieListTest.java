package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.calories.calorielist.Entry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalorieListTest {

    @Test
    public void addEntry_validInput_entryAdded() {
        // Test setup
        CalorieList calorieList = new CalorieList();
        String validInputCalorieIn = "calories in Eat burger c/369 date/2024-03-14";
        String validInputCalorieOut = "calories out run c/679 date/2024-03-15";

        // Call method to test
        calorieList.addEntry(validInputCalorieIn);
        calorieList.addEntry(validInputCalorieOut);

        // Verify that the entry has been added to the list
        assertEquals(2, calorieList.getSize());
        Entry firstEntry = calorieList.getEntry(0);
        Entry secondEntry = calorieList.getEntry(1);

        // Check calories intake entry
        assertEquals("2024-03-14", firstEntry.getDate());
        assertEquals("Eat burger", firstEntry.getDescription());
        assertEquals(369, firstEntry.getCalories());

        // Check calories outflow entry
        assertEquals("2024-03-15", secondEntry.getDate());
        assertEquals("run", secondEntry.getDescription());
        assertEquals(679, secondEntry.getCalories());
    }
}
