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
        calorieList.addEntry("calories out desc/Run c/200 date/2024-03-14");
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
            assertEquals("Invalid input exception:" + "Please ensure that you have keyed in the correct format " +
                    "in the correct order!" + "Example input: " +
                    "calories in DESCRIPTION c/INTEGER_CALORIES date/DATE m/MACROS", e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_incompleteFields_exceptionThrown() {
        try {
            parseCaloriesInput("calories in d/220224");
        } catch (InvalidInputException e) {
            assertEquals("Invalid input exception:" + "Please ensure that you have keyed in the correct format " +
                    "in the correct order!" + "Example input: " +
                    "calories in DESCRIPTION c/INTEGER_CALORIES date/DATE m/MACROS", e.getMessage());
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
            assertEquals("Invalid input exception: " +
                    "Please ensure that you have entered b/ and v/\n" +
                    "For example: liquids in b/Milo v/1000", e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_incompleteFields_exceptionThrown() {
        try {
            parseLiquidInput("liquids in b/Milo");
        } catch (InvalidInputException e) {
            assertEquals("Invalid input exception: " +
                    "Please ensure that you have entered b/ and v/\n" +
                    "For example: liquids in b/Milo v/1000", e.getMessage());
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
        String expectedOutput = "Beverage has been successfully added" +  lineSeparator +
                "Liquid List:" + lineSeparator +
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
        String expectedOutput = "Beverage has been successfully added" +  lineSeparator +
                "Beverage has been successfully added" +  lineSeparator +
                "Beverage has been successfully added" +  lineSeparator +
                "Liquid List:" + lineSeparator +
                "1. Beverage: Milo, Volume: 200" + lineSeparator +
                "2. Beverage: Water, Volume: 300" + lineSeparator +
                "3. Beverage: Juice, Volume: 150" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(3, liquidList.getSize());
    }
}
