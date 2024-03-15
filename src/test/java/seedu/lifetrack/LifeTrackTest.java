package seedu.lifetrack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.lifetrack.parser.Parser.parseCaloriesInput;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.calorielist.CalorieList;
import seedu.lifetrack.exceptions.InvalidInputException;

class LifeTrackTest {

    @Test
    public void testDeleteCalorieValidIndex() {
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories out d/2024-03-14 t/12:00 a/Run c/200");
        int initialSize = calorieList.getSize();
        calorieList.deleteEntry(1);
        assertEquals(initialSize - 1, calorieList.getSize());
        calorieList.addEntry("calories out d/2024-03-14 t/12:00 a/Run c/200");
        calorieList.addEntry("calories in d/2024-03-14 t/13:00 a/Eat c/200");
        initialSize = calorieList.getSize();
        calorieList.deleteEntry(2);
        assertEquals(initialSize - 1, calorieList.getSize());
    }

    @Test
    public void testDeleteCalorieInvalidIndex() {
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories out d/2024-03-14 t/12:00 a/Run c/200");
        int initialSize = calorieList.getSize();
        calorieList.deleteEntry(2); // Index out of bounds
        calorieList.deleteEntry(-1);
        assertEquals(initialSize, calorieList.getSize());
    }

    @Test
    public void parseCaloriesInput_emptyFields_exceptionThrown() {
        try {
            parseCaloriesInput("calories in");
        } catch (InvalidInputException e) {
            assertEquals("Please ensure that you have keyed in the correct format!", e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_incompleteFields_exceptionThrown() {
        try {
            parseCaloriesInput("calories in d/220224 t/");
        } catch (InvalidInputException e) {
            assertEquals("Please ensure that you have keyed in the correct format!", e.getMessage());
        }
    }
}
