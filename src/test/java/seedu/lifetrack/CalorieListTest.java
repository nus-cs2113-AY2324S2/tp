package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.calories.calorielist.Entry;

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

    @Test
    public void testDeleteCalorieValidIndex() {
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories out Run c/200 date/2024-03-14");
        int initialSize = calorieList.getSize();
        calorieList.deleteEntry("delete calories 1");
        assertEquals(initialSize - 1, calorieList.getSize());
        calorieList.addEntry("calories out Run c/200 date/2024-03-14");
        calorieList.addEntry("calories in Eat c/200 date/2024-03-14");
        initialSize = calorieList.getSize();
        calorieList.deleteEntry("delete calories 2");
        assertEquals(initialSize - 1, calorieList.getSize());
    }

    @Test
    public void testDeleteCalorieInvalidIndex() {
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories out Run c/200 date/2024-03-14");
        int initialSize = calorieList.getSize();
        calorieList.deleteEntry("delete calories 2"); // Index out of bounds
        calorieList.deleteEntry("delete calories -1");
        assertEquals(initialSize, calorieList.getSize());
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
        calorieList.addEntry("calories in Run c/200 date/2024-03-14");
        calorieList.printCalorieList();
        System.setOut(System.out);
        String expectedOutput = "New entry successfully added!" + lineSeparator +
                "Caloric List:" + lineSeparator +
                "1. Date: 2024-03-14, Description: Run, Calories: 200" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintCalorieListMultipleEntries() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories in Run c/200 date/2024-03-14");
        calorieList.addEntry("calories out Walk c/150 date/2024-03-14");
        calorieList.addEntry("calories in Eat c/500 date/2024-03-14");
        calorieList.addEntry("calories out Run c/250 date/2024-03-14");
        calorieList.addEntry("calories in Eat c/300 date/2024-03-14");
        calorieList.printCalorieList();
        System.setOut(System.out);
        String expectedOutput = "New entry successfully added!" + lineSeparator +
                "New entry successfully added!" + lineSeparator +
                "New entry successfully added!" + lineSeparator +
                "New entry successfully added!" + lineSeparator +
                "New entry successfully added!" + lineSeparator +
                "Caloric List:" + lineSeparator +
                "1. Date: 2024-03-14, Description: Run, Calories: 200" + lineSeparator +
                "2. Date: 2024-03-14, Description: Walk, Calories: 150" + lineSeparator +
                "3. Date: 2024-03-14, Description: Eat, Calories: 500" + lineSeparator +
                "4. Date: 2024-03-14, Description: Run, Calories: 250" + lineSeparator +
                "5. Date: 2024-03-14, Description: Eat, Calories: 300" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(5, calorieList.getSize());
    }
}
