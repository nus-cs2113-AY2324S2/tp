//@@author shawnpong
package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.lifetrack.ui.LiquidListUI.WHITESPACE;

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
        String expectedOutput = WHITESPACE + "Your hydration list is empty." + lineSeparator
                + WHITESPACE + "Populate your list with more entries :)" + lineSeparator;
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
        String expectedOutput = WHITESPACE+ "Beverage has been successfully added" + lineSeparator +
                WHITESPACE+ "Your hydration List:" + lineSeparator +
                WHITESPACE + "1. Beverage: Milo, Volume: 200, Date: 220224" + lineSeparator;
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
        String expectedOutput = WHITESPACE + "Beverage has been successfully added" + lineSeparator +
                WHITESPACE + "Beverage has been successfully added" + lineSeparator +
                WHITESPACE + "Beverage has been successfully added" + lineSeparator +
                WHITESPACE + "Your hydration List:" + lineSeparator +
                WHITESPACE + "1. Beverage: Milo, Volume: 200, Date: 220224" + lineSeparator +
                WHITESPACE + "2. Beverage: Water, Volume: 300, Date: 220224" + lineSeparator +
                WHITESPACE + "3. Beverage: Juice, Volume: 150, Date: 220224" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(3, liquidList.getSize());
    }
}
