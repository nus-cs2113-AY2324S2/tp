package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class StorageTest {

    private static final Path TEST_FILE_PATH = Path.of("save", "test_tasks.txt");

    void setUp() throws IOException {
        // Delete the test file before each test
        Files.deleteIfExists(TEST_FILE_PATH);
    }

    void tearDown() throws IOException {
        // Delete the test file after each test
        Files.deleteIfExists(TEST_FILE_PATH);
    }

}
