package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.liquids.liquidlist.LiquidList;
import seedu.lifetrack.system.exceptions.InvalidInputException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lifetrack.system.parser.ParserCalories.parseCaloriesInput;
import static seedu.lifetrack.system.parser.ParserLiquid.parseLiquidInput;


class LifeTrackTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testDeleteCalorieValidIndex() {
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories out d/2024-03-14 t/12:00 a/Run c/200");
        int initialSize = calorieList.getSize();
        calorieList.deleteEntry("delete calories 1");
        assertEquals(initialSize - 1, calorieList.getSize());
        calorieList.addEntry("calories out d/2024-03-14 t/12:00 a/Run c/200");
        calorieList.addEntry("calories in d/2024-03-14 t/13:00 a/Eat c/200");
        initialSize = calorieList.getSize();
        calorieList.deleteEntry("delete calories 2");
        assertEquals(initialSize - 1, calorieList.getSize());
    }

    @Test
    public void testDeleteCalorieInvalidIndex() {
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories out d/2024-03-14 t/12:00 a/Run c/200");
        int initialSize = calorieList.getSize();
        calorieList.deleteEntry("delete calories 2"); // Index out of bounds
        calorieList.deleteEntry("delete calories -1");
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
        calorieList.addEntry("calories in d/2024-03-14 t/12:00 a/Run c/200");
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
        calorieList.addEntry("calories in d/2024-03-14 t/12:00 a/Run c/200");
        calorieList.addEntry("calories out d/2024-03-14 t/13:00 a/Walk c/150");
        calorieList.addEntry("calories in d/2024-03-14 t/14:00 a/Eat c/500");
        calorieList.addEntry("calories out d/2024-03-14 t/15:00 a/Run c/250");
        calorieList.addEntry("calories in d/2024-03-14 t/16:00 a/Eat c/300");
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
    @Test
    public void testDeleteLiquidValidIndex() {
        LiquidList liquidList = new LiquidList();
        liquidList.addEntry("liquids in b/Milo v/200");
        int initialSize = liquidList.getSize();
        liquidList.deleteEntry("delete liquids 1");
        assertEquals(initialSize - 1, liquidList.getSize());
    }

    @Test
    public void testDeleteLiquidInvalidIndex() {
        LiquidList liquidList = new LiquidList();
        liquidList.addEntry("liquids in b/Milo v/200");
        int initialSize = liquidList.getSize();
        liquidList.deleteEntry("delete liquids 2"); // Index out of bounds
        liquidList.deleteEntry("delete liquids -1");
        assertEquals(initialSize, liquidList.getSize());
    }

    @Test
    public void parseLiquidInput_emptyFields_exceptionThrown() {
        try {
            parseLiquidInput("liquids in");
        } catch (InvalidInputException e) {
            assertEquals("Please ensure that you have keyed in the correct format!", e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_incompleteFields_exceptionThrown() {
        try {
            parseLiquidInput("liquids in b/Milo");
        } catch (InvalidInputException e) {
            assertEquals("Please ensure that you have keyed in the correct format!", e.getMessage());
        }
    }

    @Test
    public void testPrintLiquidListEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        LiquidList liquidList = new LiquidList();
        liquidList.printLiquidList();
        System.setOut(System.out);
        String expectedOutput = "Your liquid list is empty." + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintLiquidListNonEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        LiquidList liquidList = new LiquidList();
        liquidList.addEntry("liquids in b/Milo v/200");
        liquidList.printLiquidList();
        System.setOut(System.out);
        String expectedOutput = "Liquid List:" + lineSeparator +
                "1. Beverage: Milo, Volume: 200" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintLiquidListMultipleEntries() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        LiquidList liquidList = new LiquidList();
        liquidList.addEntry("liquids in b/Milo v/200");
        liquidList.addEntry("liquids in b/Water v/300");
        liquidList.addEntry("liquids in b/Juice v/150");
        liquidList.printLiquidList();
        System.setOut(System.out);
        String expectedOutput = "Liquid List:" + lineSeparator +
                "1. Beverage: Milo, Volume: 200" + lineSeparator +
                "2. Beverage: Water, Volume: 300" + lineSeparator +
                "3. Beverage: Juice, Volume: 150" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(3, liquidList.getSize());
    }
}
