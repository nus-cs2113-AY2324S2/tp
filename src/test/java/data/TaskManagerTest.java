package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static data.TaskManager.addTask;
import static data.TaskManager.updateTask;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;


class TaskManagerTest {
    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @AfterEach
    void resetTaskManager() {
        taskManager = null;
    }

    @Test
    void addTask_validInput_addsTask() {
        // Arrange
        LocalDate date = LocalDate.now();
        String taskDescription = "Test task";

        // Act
        Task testTask = new Task(taskDescription);
        addTask(date, taskDescription);

        // Assert
        assertTrue(taskManager.getTasksForDate(date).contains(testTask));
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
        Task testTask = new Task(taskDescription);
        addTask(date, taskDescription);

        // Act
        List<Task> tasksForDate = taskManager.getTasksForDate(date);

        // Assert
        assertFalse(tasksForDate.isEmpty());
        assertTrue(tasksForDate.contains(testTask));
    }

    @Test
    void addTasksFromFile_validInput_addsTasks() {
        // Arrange
        LocalDate date = LocalDate.now();
        String taskDescription = "Test task";
        Map<LocalDate, List<Task>> tasksFromFile = new HashMap<>();
        Task testTask = new Task(taskDescription);
        tasksFromFile.put(date, List.of(testTask));

        // Act
        taskManager.addTasksFromFile(tasksFromFile);

        // Assert
        assertTrue(taskManager.getTasksForDate(date).contains(testTask));
    }
}
