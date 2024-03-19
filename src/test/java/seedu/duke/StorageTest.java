package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import Storage.Storage;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class StorageTest {

    private final Path TEST_FILE_PATH = Path.of("./test_save/test_storage.txt");

    public void setUpTest() throws IOException {
//        Storage.createNewFile();
    }

    public void tearDownTest() throws IOException {
        // Delete the test file
//        Files.deleteIfExists(TEST_FILE_PATH);
    }
}
