package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.sleep.sleeplist.SleepList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepListTest {
    @Test
    public void testDeleteSleepValidIndex(){
        SleepList sleepList = new SleepList();
        sleepList.addSleep("sleep add 7.5 d/110324");
        sleepList.addSleep("sleep add 8");
        int initialSize = sleepList.getSize();
        sleepList.deleteSleep("sleep delete 1");
        assertEquals(initialSize - 1, sleepList.getSize());
    }
    @Test
    public void testDeleteSleepInvalidIndex() {
        SleepList sleepList = new SleepList();
        sleepList.addSleep("sleep add 7.5 d/110324");
        sleepList.addSleep("sleep add 8");
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
        String expectedOutput = "\t Your sleep list is empty." + lineSeparator
                + "\t Populate your list with more entries :)" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }
    @Test
    public void testPrintSleepListNonEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        SleepList sleepList = new SleepList();
        sleepList.addSleep("sleep add 7.5 d/110324");
        sleepList.printSleepList();
        System.setOut(System.out);
        String expectedOutput = "\t New sleep record has been successfully added." + lineSeparator +
                "\t Your Sleep List:" + lineSeparator +
                "\t 1. \t Date: 110324, Duration: 7.5 hours" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintSleepListMultipleEntries() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        SleepList sleepList = new SleepList();
        sleepList.addSleep("sleep add 7.5 d/110324");
        sleepList.addSleep("sleep add 8.0 d/280524");
        sleepList.addSleep("sleep add 4.2");
        sleepList.printSleepList();
        System.setOut(System.out);
        String expectedOutput = "\t New sleep record has been successfully added." + lineSeparator +
                "\t New sleep record has been successfully added." + lineSeparator +
                "\t New sleep record has been successfully added." + lineSeparator +
                "\t Your Sleep List:" + lineSeparator +
                "\t 1. \t Date: 110324, Duration: 7.5 hours" + lineSeparator +
                "\t 2. \t Date: 280524, Duration: 8.0 hours" + lineSeparator +
                "\t 3. \t Date: N/A, Duration: 4.2 hours" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(3, sleepList.getSize());
    }
}
