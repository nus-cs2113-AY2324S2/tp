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
    void addTodo_validInput_addsTask() throws TaskManagerException {
        // Arrange
        LocalDate date = LocalDate.now();
        String taskDescription = "Test Todo";

        // Act
        Task testTask = new Task(taskDescription);
        TaskType testTaskType = TaskType.TODO;
        String[] dummyTestDates = new String[]{null};
        addTask(date, taskDescription, testTaskType, dummyTestDates);
        Task addedTask = taskManager.getTasksForDate(date).get(0);

        // Assert
        assertEquals(testTask.getName(), addedTask.getName());
    }

    @Test
    void updateTodo_validInput_updatesTask() throws TaskManagerException {
        // Arrange
        LocalDate date = LocalDate.now();
        String initialTaskDescription = "Initial todo";
        String updatedTaskDescription = "Updated todo";
        TaskType testTaskType = TaskType.TODO;
        String[] dummyTestDates = new String[]{null};
        addTask(date, initialTaskDescription, testTaskType, dummyTestDates);

        // Act
        updateTask(date, 0, updatedTaskDescription);

        // Assert
        assertEquals(updatedTaskDescription, taskManager.getTasksForDate(date).get(0).getName());
    }

    @Test
    void getTasksForDate_validDate_returnsTasks() throws TaskManagerException {
        // Arrange
        LocalDate date = LocalDate.now();
        String taskDescription = "Test todo task";
        TaskType testTaskType = TaskType.TODO;
        String[] dummyTestDates = new String[]{null};
        addTask(date, taskDescription, testTaskType, dummyTestDates);

        // Act
        List<Task> tasksForDate = taskManager.getTasksForDate(date);
        Task createdTask = tasksForDate.get(0);

        // Assert
        assertEquals(createdTask, tasksForDate.get(0));
    }

    @Test
    void addTodoFromFile_validInput_addsTasks() throws TaskManagerException {
        // Arrange
        LocalDate date = LocalDate.now();
        Map<LocalDate, List<Task>> tasksFromFile = new HashMap<>();
        String taskDescription = "Test todo task";
        Task testTodoTask = new Todo(taskDescription);
        tasksFromFile.put(date, List.of(testTodoTask));

        // Act
        taskManager.addTasksFromFile(tasksFromFile);

        // Assert
        assertEquals(testTodoTask.getName() ,taskManager.getTasksForDate(date).get(0).getName());
    }
}
