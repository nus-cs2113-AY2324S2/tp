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
import static data.TaskManager.deleteAllTasksOnDate;
import static org.junit.jupiter.api.Assertions.assertEquals;


class TaskManagerTest {
    public static List<Task> emptyTaskList = List.of();
    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @AfterEach
    void resetTaskManager() {
        LocalDate date = LocalDate.now();
        deleteAllTasksOnDate(taskManager, date);
    }

    @Test
    void addTask_validInput_addsTask() {
        // Arrange
        LocalDate date = LocalDate.now();
        String taskDescription = "Test task";

        // Act
        Task testTask = new Task(taskDescription);
        addTask(date, taskDescription);
        Task addedTask = taskManager.getTasksForDate(date).get(0);

        // Assert
        assertEquals(testTask.getName(), addedTask.getName());
    }

    @Test
    void updateTask_validInput_updatesTask() {
        // Arrange
        LocalDate date = LocalDate.now();
        String initialTaskDescription = "Initial task";
        String updatedTaskDescription = "Updated task";
        addTask(date, initialTaskDescription);

        // Act
        updateTask(date, 0, updatedTaskDescription);

        // Assert
        assertEquals(updatedTaskDescription, taskManager.getTasksForDate(date).get(0).getName());
    }

    @Test
    void getTasksForDate_validDate_returnsTasks() {
        // Arrange
        LocalDate date = LocalDate.now();
        String taskDescription = "Test task";
        addTask(date, taskDescription);

        // Act
        List<Task> tasksForDate = taskManager.getTasksForDate(date);
        Task createdTask = tasksForDate.get(0);

        // Assert
        assertEquals(createdTask, tasksForDate.get(0));
    }

    @Test
    void addTasksFromFile_validInput_addsTasks() {
        // Arrange
        LocalDate date = LocalDate.now();
        Map<LocalDate, List<Task>> tasksFromFile = new HashMap<>();
        String taskDescription = "Test task";
        Task testTask = new Task(taskDescription);
        tasksFromFile.put(date, List.of(testTask));

        // Act
        taskManager.addTasksFromFile(tasksFromFile);

        // Assert
        assertEquals(testTask.getName() ,taskManager.getTasksForDate(date).get(0).getName());
    }
}
