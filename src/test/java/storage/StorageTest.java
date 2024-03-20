package storage;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StorageTest {
    @Test
    public void testReadFile_FileNotFound() {
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
}
