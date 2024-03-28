package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.calories.calorielist.InputEntry;
import seedu.lifetrack.calories.calorielist.OutputEntry;

public class CalorieListTest {

    private final String addedEntryHeader = "\t The following entry has been added to your caloric list!";

    @Test
    public void addEntry_validInput_entryAdded() {
        // Test setup
        CalorieList calorieList = new CalorieList();
        String validInputCalorieIn = "calories in Eat burger c/369 d/2024-03-14";
        String validInputCalorieOut = "calories out run c/679 d/2024-03-15";

        // Call method to test
        calorieList.addEntry(validInputCalorieIn);
        calorieList.addEntry(validInputCalorieOut);

        // Verify that the entry has been added to the list
        assertEquals(2, calorieList.getSize());
        InputEntry firstEntry = (InputEntry)calorieList.getEntry(0);
        OutputEntry secondEntry = (OutputEntry)calorieList.getEntry(1);

        // Check calories intake entry
        assertEquals("2024-03-14", firstEntry.getDate());
        assertEquals("Eat burger", firstEntry.getDescription());
        assertEquals(369, firstEntry.getCalories());

        // Check calories outflow entry
        assertEquals("2024-03-15", secondEntry.getDate());
        assertEquals("run", secondEntry.getDescription());
        assertEquals(679, secondEntry.getCalories());
    }
    
    //@@author a-wild-chocolate
    @Test
    public void testDeleteCalorieValidIndex() {
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories out Run c/200 d/2024-03-14");
        int initialSize = calorieList.getSize();
        calorieList.deleteEntry("calories delete 1");
        assertEquals(initialSize - 1, calorieList.getSize());
        calorieList.addEntry("calories out Run c/200 d/2024-03-14");
        calorieList.addEntry("calories in Eat c/200 d/2024-03-14");
        initialSize = calorieList.getSize();
        calorieList.deleteEntry("calories delete 2");
        assertEquals(initialSize - 1, calorieList.getSize());
    }

    @Test
    public void testDeleteCalorieInvalidIndex() {
        CalorieList calorieList = new CalorieList();
        calorieList.addEntry("calories out Run c/200 date/2024-03-14");
        int initialSize = calorieList.getSize();
        calorieList.deleteEntry("calories delete 2"); // Index out of bounds
        calorieList.deleteEntry("calories delete -1");
        assertEquals(initialSize, calorieList.getSize());
    }

    //@@author shawnpong
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
        calorieList.addEntry("calories in Run c/200 d/2024-03-14");
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
        calorieList.addEntry("calories in Run c/200 d/2024-03-14");
        calorieList.addEntry("calories out Walk c/150 d/2024-03-14");
        calorieList.addEntry("calories in Eat c/500 d/2024-03-14");
        calorieList.addEntry("calories out Run c/250 d/2024-03-14");
        calorieList.addEntry("calories in Eat c/300 d/2024-03-14");
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
