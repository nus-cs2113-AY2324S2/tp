package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.calorielist.CalorieList;
import seedu.lifetrack.calorielist.Entry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalorieListTest {

    @Test
    public void calorieInTest() {
        // Test setup
        CalorieList calorieList = new CalorieList();
        String validInput = "calories in d/2024-03-14 t/15:30 a/Eat burger c/369";
        final int INDEX0 = 0;

        // Call method to test
        CalorieList.calorieIn(validInput);

        // Verify that the entry has been added to the list
        assertEquals(1, CalorieList.calorieArrayList.size());
        Entry addedEntry = CalorieList.calorieArrayList.get(INDEX0);
        assertEquals("2024-03-14", addedEntry.getActivity().getDate());
        assertEquals("15:30", addedEntry.getActivity().getTime());
        assertEquals("Eat burger", addedEntry.getActivity().getDescription());
        assertEquals(369, addedEntry.getCalorie().getCalories());
    }
}
