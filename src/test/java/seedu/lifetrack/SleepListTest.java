package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.sleep.sleeplist.SleepList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.lifetrack.ui.LiquidListUI.WHITESPACE;

public class SleepListTest {
    @Test
    public void testDeleteSleepValidIndex(){
        SleepList sleepList = new SleepList();
        sleepList.addSleep("sleep add t/7.5 d/110324");
        sleepList.addSleep("sleep add t/8");
        int initialSize = sleepList.getSize();
        sleepList.deleteSleep("sleep delete 1");
        assertEquals(initialSize - 1, sleepList.getSize());
    }
    @Test
    public void testDeleteSleepInvalidIndex() {
        SleepList sleepList = new SleepList();
        sleepList.addSleep("sleep add t/7.5 d/110324");
        sleepList.addSleep("sleep add t/8");
        int initialSize = sleepList.getSize();
        sleepList.deleteSleep("sleep delete 5"); // Index out of bounds
        sleepList.deleteSleep("sleep delete -1");
        assertEquals(initialSize, sleepList.getSize());
    }
    @Test
    public void testPrintLiquidListEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        SleepList sleepList = new SleepList();
        sleepList.printSleepList();
        System.setOut(System.out);
        String expectedOutput = WHITESPACE + "Your sleep list is empty." + lineSeparator
                + WHITESPACE + "Populate your list with more entries :)" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }
    @Test
    public void testPrintSleepListNonEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        SleepList sleepList = new SleepList();
        sleepList.addSleep("sleep add t/7.5 d/110324");
        sleepList.printSleepList();
        System.setOut(System.out);
        String expectedOutput = WHITESPACE+ "New sleep record has been successfully added." + lineSeparator +
                WHITESPACE+ "Your Sleep List:" + lineSeparator +
                WHITESPACE + "1. Date: 110324, Duration: 7.5 hours" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintSleepListMultipleEntries() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        SleepList sleepList = new SleepList();
        sleepList.addSleep("sleep add t/7.5 d/110324");
        sleepList.addSleep("sleep add t/8.0 d/280524");
        sleepList.addSleep("sleep add t/4.2");
        sleepList.printSleepList();
        System.setOut(System.out);
        String expectedOutput = WHITESPACE + "New sleep record has been successfully added." + lineSeparator +
                WHITESPACE + "New sleep record has been successfully added." + lineSeparator +
                WHITESPACE + "New sleep record has been successfully added." + lineSeparator +
                WHITESPACE + "Your Sleep List:" + lineSeparator +
                WHITESPACE + "1. Date: 110324, Duration: 7.5 hours" + lineSeparator +
                WHITESPACE + "2. Date: 280524, Duration: 8.0 hours" + lineSeparator +
                WHITESPACE + "3. Date: N/A, Duration: 4.2 hours" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(3, sleepList.getSize());
    }
}
