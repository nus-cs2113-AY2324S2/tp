package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

import static data.TaskManager.addTask;
import static data.TaskManager.updateTask;


class TaskManagerTest {
    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    void addTask_validInput_addsTask() {
        // Arrange
        LocalDate date = LocalDate.now();
        String taskDescription = "Test task";

        // Act
        addTask(date, taskDescription);

        // Assert
        assertTrue(taskManager.getTasksForDate(date).contains(taskDescription));
    }

    @Test
    void deleteTask_validInput_deletesTask() {
        // Arrange
        LocalDate date = LocalDate.now();
        String taskDescription = "Test task";
        addTask(date, taskDescription);

        // Act
        taskManager.deleteTask(date, 0);

        // Assert
        assertTrue(taskManager.getTasksForDate(date).isEmpty());
    }

    @Test
    void updateTask_validInput_updatesTask() {
        // Arrange
        LocalDate date = LocalDate.now();
        String initialTask = "Initial task";
        String updatedTask = "Updated task";
        addTask(date, initialTask);

        // Act
        updateTask(date, 0, updatedTask);

        // Assert
        assertEquals(updatedTask, taskManager.getTasksForDate(date).get(0));
    }

    @Test
    void getTasksForDate_validDate_returnsTasks() {
        // Arrange
        LocalDate date = LocalDate.now();
        String taskDescription = "Test task";
        addTask(date, taskDescription);

        // Act
        List<String> tasksForDate = taskManager.getTasksForDate(date);

        // Assert
        assertFalse(tasksForDate.isEmpty());
        assertTrue(tasksForDate.contains(taskDescription));
    }

    @Test
    void addTasksFromFile_validInput_addsTasks() {
        // Arrange
        LocalDate date = LocalDate.now();
        String taskDescription = "Test task";
        Map<LocalDate, List<String>> tasksFromFile = new HashMap<>();
        tasksFromFile.put(date, List.of(taskDescription));

        // Act
        taskManager.addTasksFromFile(tasksFromFile);

        // Assert
        assertTrue(taskManager.getTasksForDate(date).contains(taskDescription));
    }
}
