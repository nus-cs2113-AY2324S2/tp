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
    public void testPrintLiquidListEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        LiquidList liquidList = new LiquidList();
        liquidList.printLiquidList();
        System.setOut(System.out);
        String expectedOutput = "\t Your liquid list is empty." + lineSeparator;
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
        String expectedOutput = "Beverage has been successfully added" + lineSeparator +
                "\t Liquid List:" + lineSeparator +
                "\t 1. Beverage: Milo, Volume: 200" + lineSeparator;
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
        String expectedOutput = "Beverage has been successfully added" + lineSeparator +
                "Beverage has been successfully added" + lineSeparator +
                "Beverage has been successfully added" + lineSeparator +
                "\t Liquid List:" + lineSeparator +
                "\t 1. Beverage: Milo, Volume: 200" + lineSeparator +
                "\t 2. Beverage: Water, Volume: 300" + lineSeparator +
                "\t 3. Beverage: Juice, Volume: 150" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(3, liquidList.getSize());
    }
}
