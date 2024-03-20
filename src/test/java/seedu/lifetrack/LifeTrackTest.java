package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.system.exceptions.InvalidInputException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lifetrack.system.Parser.parseCaloriesInput;


class LifeTrackTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testDeleteCalorieValidIndex() {
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories out a/Run c/200 d/2024-03-14");
        int initialSize = calorieList.getSize();
        calorieList.deleteEntry("delete 1");
        assertEquals(initialSize - 1, calorieList.getSize());
        calorieList.addEntry("calories out a/Run c/200 d/2024-03-14");
        calorieList.addEntry("calories in a/Eat c/200 d/2024-03-14");
        initialSize = calorieList.getSize();
        calorieList.deleteEntry("delete 2");
        assertEquals(initialSize - 1, calorieList.getSize());
    }

    @Test
    public void testDeleteCalorieInvalidIndex() {
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories out a/Run c/200 d/2024-03-14");
        int initialSize = calorieList.getSize();
        calorieList.deleteEntry("delete 2"); // Index out of bounds
        calorieList.deleteEntry("delete -1");
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
            parseCaloriesInput("calories in d/220224");
        } catch (InvalidInputException e) {
            assertEquals("Please ensure that you have keyed in the correct format!", e.getMessage());
        }
    }

    @Test
    public void testPrintCalorieListEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        CalorieList calorieList = new CalorieList();
        calorieList.printCalorieList();
        System.setOut(System.out);
        String expectedOutput = "Your caloric list is empty." + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintCalorieListNonEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories in a/Run c/200 d/2024-03-14");
        calorieList.printCalorieList();
        System.setOut(System.out);
        String expectedOutput = "Caloric List:" + lineSeparator + "1. Activity: Run, Calories: 200" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintCalorieListMultipleEntries() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories in a/Run c/200 d/2024-03-14");
        calorieList.addEntry("calories out a/Walk c/150 d/2024-03-14");
        calorieList.addEntry("calories in a/Eat c/500 d/2024-03-14");
        calorieList.addEntry("calories out a/Run c/250 d/2024-03-14");
        calorieList.addEntry("calories in a/Eat c/300 d/2024-03-14");
        calorieList.printCalorieList();
        System.setOut(System.out);
        String expectedOutput = "Caloric List:" + lineSeparator +
                "1. Activity: Run, Calories: 200" + lineSeparator +
                "2. Activity: Walk, Calories: 150" + lineSeparator +
                "3. Activity: Eat, Calories: 500" + lineSeparator +
                "4. Activity: Run, Calories: 250" + lineSeparator +
                "5. Activity: Eat, Calories: 300" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(5, calorieList.getSize());
    }

}
