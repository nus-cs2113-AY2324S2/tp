package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.system.exceptions.InvalidInputException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lifetrack.system.parser.ParserCalories.parseCaloriesInput;


class LifeTrackTest {
    private final String addedEntryHeader = "\t The following entry has been added to your caloric list!";
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testDeleteCalorieValidIndex() {
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories out Run c/200 date/2024-03-14");
        int initialSize = calorieList.getSize();
        calorieList.deleteEntry("delete 1");
        assertEquals(initialSize - 1, calorieList.getSize());
        calorieList.addEntry("calories out Run c/200 date/2024-03-14");
        calorieList.addEntry("calories in eat c/200 date/2024-03-14");
        initialSize = calorieList.getSize();
        calorieList.deleteEntry("delete 2");
        assertEquals(initialSize - 1, calorieList.getSize());
    }

    @Test
    public void testDeleteCalorieInvalidIndex() {
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories out Run c/200 date/2024-03-14");
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
            assertEquals("\t Invalid input! \n"  + "\t Please ensure that you have" +
                            " keyed in the correct format in the correct order!\n" +
                    "\t Example input: calories in DESCRIPTION c/INTEGER_CALORIES date/DATE m/MACROS", e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_incompleteFields_exceptionThrown() {
        try {
            parseCaloriesInput("calories in d/220224 t/");
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input! \n" +
                    "\t Please ensure that you have keyed in the correct format" +
                    " in the correct order!\n" + "\t Example input: " +
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
        String expectedOutput = "\t Your caloric list is empty. " +
                "Add new entries to populate your list :)" + lineSeparator;
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
        String expectedOutput = addedEntryHeader + lineSeparator +
                "\t " + calorieList.getEntry(0).toString() + lineSeparator +
                "\t Your Caloric List:" + lineSeparator +
                "\t 1. \t Date: 2024-03-14, Description: Run, Calories: 200" + lineSeparator;
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
        StringBuilder expectedOutput = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            expectedOutput.append(addedEntryHeader)
                    .append(lineSeparator).append("\t ").append(calorieList.getEntry(i).toString())
                    .append(lineSeparator);
        }
        expectedOutput.append("\t Your Caloric List:")
                .append(lineSeparator)
                .append("\t 1. \t Date: 2024-03-14, Description: Run, Calories: 200")
                .append(lineSeparator)
                .append("\t 2. \t Date: 2024-03-14, Description: Walk, Calories: 150")
                .append(lineSeparator)
                .append("\t 3. \t Date: 2024-03-14, Description: Eat, Calories: 500")
                .append(lineSeparator)
                .append("\t 4. \t Date: 2024-03-14, Description: Run, Calories: 250")
                .append(lineSeparator)
                .append("\t 5. \t Date: 2024-03-14, Description: Eat, Calories: 300")
                .append(lineSeparator);
        assertEquals(expectedOutput.toString(), outputStream.toString());
        assertEquals(5, calorieList.getSize());
    }

}
