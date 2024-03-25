package data;

import storage.Storage;
import time.MonthView;
import time.WeekView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static data.TaskManagerException.checkIfDateHasTasks;
import static data.TaskManagerException.checkIfDateInCurrentWeek;
import static data.TaskManagerException.checkIfDateInCurrentMonth;
import static data.TaskType.DEADLINE;
import static data.TaskType.TODO;
import static data.TaskType.EVENT;
import static storage.Storage.saveTasksToFile;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages tasks by providing functionalities to add, delete, and update tasks.
 */
public class TaskManager {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static final Map<LocalDate, List<Task>> tasks = new HashMap<>();

    /**
     * Adds a task for a specific date.
     *
     * @param date The date for the task.
     * @param taskDescription The description of the task.
     * @param taskType The TaskType of the task to be added.
     * @param dates A String array that contains the relevant dates for the task to be added.
     */
    public static void addTask(LocalDate date, String taskDescription, TaskType taskType, String[] dates)
            throws TaskManagerException {
        Task taskToAdd;

        switch (taskType) {
        case TODO:
            taskToAdd = new Todo(taskDescription);
            break;

        case EVENT:
            String startDate = dates[0];
            String endDate = dates[1];
            taskToAdd = new Event(taskDescription, startDate, endDate);
            break;

        case DEADLINE:
            String deadline = dates[0];
            taskToAdd = new Deadline(taskDescription, deadline);
            break;

        default:
            throw new TaskManagerException("Invalid task type given. T for Todo, E for event, D for deadline.");
        }

        tasks.computeIfAbsent(date, k -> new ArrayList<>()).add(taskToAdd);
    }

    /**
     * Deletes a task for a specific date and task index.
     *
     * @param date The date of the task.
     * @param taskIndex The index of the task to delete.
     */
    public void deleteTask(LocalDate date, int taskIndex) {
        List<Task> dayTasks = tasks.get(date);
        if (dayTasks != null && taskIndex >= 0 && taskIndex < dayTasks.size()) {
            dayTasks.remove(taskIndex);
            if (dayTasks.isEmpty()) {
                tasks.remove(date);
            }
            System.out.println("Task deleted.");
        } else {
            System.out.println("The task you are trying to delete does not exist.");
        }
    }


    /**
     * Updates a task for a specific date and task index.
     *
     * @param date              The date of the task.
     * @param taskIndex         The index of the task to update.
     * @param newTaskDescription The updated description of the task.
     * @throws IndexOutOfBoundsException If the task index is out of bounds.
     */
    public static void updateTask(LocalDate date, int taskIndex, String newTaskDescription)
            throws IndexOutOfBoundsException, TaskManagerException {
        try {
            List<Task> dayTasks = tasks.get(date);
            if (dayTasks == null || taskIndex < 0 || taskIndex >= dayTasks.size()) {
                throw new TaskManagerException("The task you are trying to update does not exist.");
            }

            String oldDescription = dayTasks.get(taskIndex).getName();

            Task task = new Task(newTaskDescription);
            dayTasks.set(taskIndex, task);

            logger.log(Level.INFO, "Updating task description from " +
                    oldDescription + " to: " + newTaskDescription);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Task index is out of bounds.");
        }
    }


    /**
     * Retrieves tasks for a specific date.
     *
     * @param date The date to retrieve tasks for.
     * @return A list of tasks for the given date.
     */
    public List<Task> getTasksForDate(LocalDate date) {
        return tasks.getOrDefault(date, new ArrayList<>());
    }

    /**
     * Adds a task from user input along with the date.
     *
     * @param weekView WeekView object to validate the date.
     * @param inMonthView A boolean indicating whether the view is in month view or not.
     * @throws TaskManagerException If there is an error in managing tasks.
     * @throws DateTimeParseException If there is an error parsing the date.
     */
    public void addManager(WeekView weekView, MonthView monthView,boolean inMonthView, String action,
            String day, String taskTypeString, String taskDescription)
            throws TaskManagerException,DateTimeParseException {

        // Convert the day to a LocalDate
        LocalDate date;
        // Convert the dayString to date range
        String[] dates = new String[2];
        if (day.contains("-")) {
            dates = day.split("-");
        } else {
            dates[0] = day;
        }

        // Convert dayString to int
        int dayInt = Integer.parseInt(day);

        // Check if the date is in the current week/month view
        if (inMonthView) {
            date = monthView.getStartOfMonth().plusDays(dayInt - 1);
            checkIfDateInCurrentMonth(date);

        } else {
            date = weekView.getStartOfWeek().plusDays(dayInt - 1);
            checkIfDateInCurrentWeek(date, weekView);
        }

        // Parse the task type
        TaskType taskType = parseTaskType(taskTypeString.toUpperCase());

        if (taskType == null) {
            throw new TaskManagerException("Invalid task type. Please provide valid task type: " +
                    "T for Todo, E for event, D for deadline.");
        }

        // Add the task based on the parsed inputs
        addTask(date, taskDescription, taskType, dates); // Assuming no additional dates are needed for the task

        // Save tasks to file
        saveTasksToFile(tasks, Storage.FILE_PATH); // Update tasks.txt file
        System.out.println("Task added.");
    }


    /**
     * Method that parses the TaskType to be specified based on the user's input.
     *
     * @param userInput The String containing the user's cleaned input.
     * @return TaskType of the user's choosing.
     */
    public static TaskType parseTaskType(String userInput) {
        TaskType currentTaskType;
        switch (userInput) {
        case "T":
            currentTaskType = TODO;
            break;

        case "D":
            currentTaskType = DEADLINE;
            break;

        case "E":
            currentTaskType = EVENT;
            break;

        default:
            currentTaskType = null;
            // intentional fallthrough since addTask method checks for invalid taskType already
        }

        return currentTaskType;
    }

    /**
     * Prompts user for updated task description.
     *
     * @param scanner User input.
     * @param weekView Current week being viewed.
     * @param inMonthView Whether month is being viewed.
     * @param taskManager The taskManager class being used.
     * @throws TaskManagerException If not in correct week/month view.
     * @throws DateTimeParseException If there is an error parsing the date.
     */

    public void updateManager(Scanner scanner, WeekView weekView, MonthView monthView, boolean inMonthView,
                              TaskManager taskManager,int day, int taskIndex, String newDescription)
            throws TaskManagerException, DateTimeParseException {
        // Convert the day to a LocalDate
        LocalDate date;

        // Check if the date is in the current week/month view
        if (inMonthView) {
            date = monthView.getStartOfMonth().plusDays(day - 1);
            checkIfDateInCurrentMonth(date);
        } else {
            date = weekView.getStartOfWeek().plusDays(day - 1);
            checkIfDateInCurrentWeek(date, weekView);
        }

        // Update the task based on the parsed inputs
        updateTask(date, taskIndex - 1, newDescription); // Subtract 1 to convert to zero-based index
        System.out.println("Task updated.");

        // Save tasks to file
        saveTasksToFile(tasks, Storage.FILE_PATH); // Update tasks.txt file
    }


    /**
     * Adds tasks from a file to the TaskManager.
     *
     * @param tasksFromFile A map containing tasks read from a file.
     * @throws TaskManagerException If there is an error adding tasks.
     */
    public void addTasksFromFile(Map<LocalDate, List<Task>> tasksFromFile) throws TaskManagerException {
        for (Map.Entry<LocalDate, List<Task>> entry : tasksFromFile.entrySet()) {
            LocalDate date = entry.getKey();
            List<Task> taskList = entry.getValue();

            for (Task task : taskList) {
                String taskDescription = task.getName();
                TaskType taskType = parseTaskType(task.getTaskType());
                String[] dates = new String[]{null, null};

                switch (taskType) {
                case TODO:
                    dates[0] = taskDescription;
                    break;

                case EVENT:
                    String startDate = task.getStartDate();
                    String endDate = task.getEndDate();
                    dates[0] = startDate;
                    dates[1] = endDate;
                    break;

                case DEADLINE:
                    String deadline = task.getByDate();
                    dates[0] = deadline;
                    break;

                default:
                    logger.log(Level.INFO, "Task to add was invalid. Task in question: " + taskDescription);
                }

                addTask(date, taskDescription, taskType, dates);
            }
        }
    }

    /**
     * Lists task of the input date.
     *
     * @param taskManager Hashmap of tasks.
     * @param date Date that's prompted by user.
     * @param message Message to be prompted to the user.
     * @throws TaskManagerException If not in correct week/month view.
     */
    private static void listTasksAtDate(TaskManager taskManager, LocalDate date, String message)
            throws TaskManagerException {
        List<Task> dayTasks = taskManager.getTasksForDate(date);
        checkIfDateHasTasks(dayTasks);

        System.out.println(message);
        for (int i = 0; i < dayTasks.size(); i++) {
            System.out.println((i + 1) + ". " + dayTasks.get(i).getName());
        }
    }

    /**
     * Prompts user for task description and deletes task from hashmap and tasks.txt file.
     *
     * @param weekView Current week being viewed.
     * @param inMonthView Whether month is being viewed.
     * @param taskManager The taskManager class being used.
     * @throws TaskManagerException If not in correct week/month view
     * @throws DateTimeParseException If there is an error parsing the date.
     */
    public static void deleteManager(WeekView weekView,MonthView monthView, boolean inMonthView,
            TaskManager taskManager,String day, int taskIndex) throws TaskManagerException, DateTimeParseException {

        // Convert the day to a LocalDate
        LocalDate date;

        int dayInt = Integer.parseInt(day);
        // Check if the date is in the current week/month view
        if (inMonthView) {
            date = monthView.getStartOfMonth().plusDays(dayInt - 1);
            checkIfDateInCurrentMonth(date);

        } else {
            date = weekView.getStartOfWeek().plusDays(dayInt - 1);
            checkIfDateInCurrentWeek(date, weekView);
        }

        // Delete the task based on the parsed inputs
        taskManager.deleteTask(date, taskIndex - 1); // Subtract 1 to convert to zero-based index
        //System.out.println("Task deleted.");

        // Save tasks to file
        saveTasksToFile(tasks, Storage.FILE_PATH); // Update tasks.txt file
    }

    /**
     * Function to delete all tasks on a specified date.
     * Currently only used to complement JUnit testing.
     *
     * @param taskManager The taskManager class in use.
     * @param specifiedDate The date on which all tasks are to be deleted.
     */

    public static void deleteAllTasksOnDate (TaskManager taskManager, LocalDate specifiedDate) {
        List<Task> dayTasks = tasks.get(specifiedDate);
        int numOfTasks = dayTasks.size();
        for (int i = numOfTasks; i >= 0; i--) {
            taskManager.deleteTask(specifiedDate, i - 1);
        }
    }

}
