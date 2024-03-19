package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

//    @Test
//    void saveTasksToFile_validStorageTaskFile_writesFormattedTestFile() throws IOException {
//        // Sample tasks to save
//        Map<LocalDate, List<String>> tasksToSave = Map.of(
//                LocalDate.of(2024, 3, 19), List.of("Task 1", "Task 2"),
//                LocalDate.of(2024, 3, 20), List.of("Task 3"));
//
//        // Call the saveTasksToFile method
//        Storage.saveTasksToFile(tasksToSave, TEST_FILE_PATH);
//
//        assertEquals(tasksToSave.size(), loadedTasks.size());
//        for (LocalDate date : tasksToSave.keySet()) {
//            assertTrue(loadedTasks.containsKey(date));
//            List<String> originalTaskList = tasksToSave.get(date);
//            List<String> loadedTaskList = loadedTasks.get(date);
//            assertEquals(originalTaskList.size(), loadedTaskList.size());
//            assertTrue(loadedTaskList.containsAll(originalTaskList));
//        }
//    }
}
