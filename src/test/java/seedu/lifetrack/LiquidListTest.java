//@@author shawnpong
package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import seedu.lifetrack.liquids.liquidlist.LiquidList;

public class LiquidListTest {

    @Test
    public void testDeleteLiquidValidIndex() {
        LiquidList liquidList = new LiquidList();
        liquidList.addEntry("hydration add Milo v/200 date/220224");
        int initialSize = liquidList.getSize();
        liquidList.deleteEntry("hydration delete 1");
        assertEquals(initialSize - 1, liquidList.getSize());
    }

    @Test
    public void testDeleteLiquidInvalidIndex() {
        LiquidList liquidList = new LiquidList();
        liquidList.addEntry("hydration add Milo v/200 date/220224");
        int initialSize = liquidList.getSize();
        liquidList.deleteEntry("hydration delete 2"); // Index out of bounds
        liquidList.deleteEntry("hydration delete -1");
        assertEquals(initialSize, liquidList.getSize());
    }

    @Test
    public void testPrintLiquidListEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        LiquidList liquidList = new LiquidList();
        liquidList.printLiquidList();
        System.setOut(System.out);
        String expectedOutput = "\t Your liquid list is empty." + lineSeparator
                + "\t Populate your list with more entries :)" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintLiquidListNonEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        LiquidList liquidList = new LiquidList();
        liquidList.addEntry("hydration add Milo v/200 date/220224");
        liquidList.printLiquidList();
        System.setOut(System.out);
        String expectedOutput = "\t Beverage has been successfully added" + lineSeparator +
                "\t Your liquid List:" + lineSeparator +
                "\t 1. \t Date: 111111, Description: Milo, Volume: 200" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintLiquidListMultipleEntries() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        LiquidList liquidList = new LiquidList();
        liquidList.addEntry("hydration add Milo v/200 date/220224");
        liquidList.addEntry("hydration add Water v/300 date/220224");
        liquidList.addEntry("hydration add Juice v/150 date/220224");
        liquidList.printLiquidList();
        System.setOut(System.out);
        String expectedOutput = "\t Beverage has been successfully added" + lineSeparator +
                "\t Beverage has been successfully added" + lineSeparator +
                "\t Beverage has been successfully added" + lineSeparator +
                "\t Your liquid List:" + lineSeparator +
                "\t 1. \t Date: 111111, Description: Milo, Volume: 200" + lineSeparator +
                "\t 2. \t Date: 111111, Description: Water, Volume: 300" + lineSeparator +
                "\t 3. \t Date: 111111, Description: Juice, Volume: 150" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(3, liquidList.getSize());
    }
}
