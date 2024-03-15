package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.calorielist.CalorieList;
import seedu.lifetrack.calorielist.Entry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalorieListTest {

    @Test
    public void addEntry_validInput_entryAdded() {
        // Test setup
        CalorieList calorieList = new CalorieList();
        String validInputCalorieIn = "calories in d/2024-03-14 t/15:30 a/Eat burger c/369";
        String validInputCalorieOut = "calories out d/2024-03-15 t/14:00 a/run c/679";

        // Call method to test
        calorieList.addEntry(validInputCalorieIn);
        calorieList.addEntry(validInputCalorieOut);

        // Verify that the entry has been added to the list
        assertEquals(2, calorieList.getSize());
        Entry firstEntry = calorieList.getEntry(0);
        Entry secondEntry = calorieList.getEntry(1);

        // Check calories intake entry
        assertEquals("2024-03-14", firstEntry.getActivity().getDate());
        assertEquals("15:30", firstEntry.getActivity().getTime());
        assertEquals("Eat burger", firstEntry.getActivity().getDescription());
        assertEquals(369, firstEntry.getCalorie().getCalories());

        // Check calories outflow entry
        assertEquals("2024-03-15", secondEntry.getActivity().getDate());
        assertEquals("14:00", secondEntry.getActivity().getTime());
        assertEquals("run", secondEntry.getActivity().getDescription());
        assertEquals(679, secondEntry.getCalorie().getCalories());
    }
}
