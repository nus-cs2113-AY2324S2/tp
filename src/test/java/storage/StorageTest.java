package storage;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class StorageTest {
    @Test
    public void readFromFile_fileNotFound() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String directory = "./testFile.txt";
        File testFile = new File(directory);
        try {
            Storage.writeToFile(directory, "Created", true);
            testFile.delete();
            Storage.readFromFile(testFile);
            assertEquals("File does not exist." + System.lineSeparator(), outputStream.toString());
        } catch (IOException e) {
            fail("failed to create a file.");
        }
    }

    @Test
    public void writeToFile_aLine_writeSuccessful() {
        String directory = "./testFile.txt";
        File testFile = new File(directory);
        String aLine = "A line";
        try {
            Storage.writeToFile(directory, aLine, true);
            Scanner scanner = new Scanner(testFile);
            String lineSkipped = scanner.nextLine();
            scanner.close();
            testFile.delete();
            assertEquals(aLine, lineSkipped);
        } catch (IOException e) {
            fail("File not found");
        }
    }
}
