//@@author shawnpong
package seedu.lifetrack;

import org.junit.jupiter.api.Test;

import seedu.lifetrack.hydration.hydrationlist.HydrationList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class HydrationListTest {

    @Test
    public void testDeleteHydrationValidIndex() {
        HydrationList hydrationList = new HydrationList();
        hydrationList.addEntry("hydration add Milo v/200 date/220224");
        int initialSize = hydrationList.getSize();
        hydrationList.deleteEntry("hydration delete 1");
        assertEquals(initialSize - 1, hydrationList.getSize());
    }

    @Test
    public void testDeleteHydrationInvalidIndex() {
        HydrationList hydrationList = new HydrationList();
        hydrationList.addEntry("hydration add Milo v/200 date/220224");
        int initialSize = hydrationList.getSize();
        hydrationList.deleteEntry("hydration delete 2"); // Index out of bounds
        hydrationList.deleteEntry("hydration delete -1");
        assertEquals(initialSize, hydrationList.getSize());
    }

    @Test
    public void testPrintHydrationListEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        HydrationList hydrationList = new HydrationList();
        hydrationList.printHydrationList();
        System.setOut(System.out);
        String expectedOutput = "\t Your liquid list is empty." + lineSeparator
                + "\t Populate your list with more entries :)" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintHydrationListNonEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        HydrationList hydrationList = new HydrationList();
        hydrationList.addEntry("hydration add Milo v/200 date/220224");
        hydrationList.printHydrationList();
        System.setOut(System.out);
        String expectedOutput = "\t Beverage has been successfully added" + lineSeparator +
                "\t Your liquid List:" + lineSeparator +
                "\t 1. \t Date: 111111, Description: Milo, Volume: 200" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintHydrationListMultipleEntries() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        HydrationList hydrationList = new HydrationList();
        hydrationList.addEntry("hydration add Milo v/200 date/220224");
        hydrationList.addEntry("hydration add Water v/300 date/220224");
        hydrationList.addEntry("hydration add Juice v/150 date/220224");
        hydrationList.printHydrationList();
        System.setOut(System.out);
        String expectedOutput = "\t Beverage has been successfully added" + lineSeparator +
                "\t Beverage has been successfully added" + lineSeparator +
                "\t Beverage has been successfully added" + lineSeparator +
                "\t Your liquid List:" + lineSeparator +
                "\t 1. \t Date: 111111, Description: Milo, Volume: 200" + lineSeparator +
                "\t 2. \t Date: 111111, Description: Water, Volume: 300" + lineSeparator +
                "\t 3. \t Date: 111111, Description: Juice, Volume: 150" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(3, hydrationList.getSize());
    }
}
