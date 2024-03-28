package data;

import storage.Storage;
import time.MonthView;
import time.WeekView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    public static void addTask(LocalDate date, String taskDescription, TaskType taskType, String[] dates,
                               String[] times)
            throws TaskManagerException {
        Task taskToAdd;

        switch (taskType) {
        case TODO:
            taskToAdd = new Todo(taskDescription);
            break;

        case EVENT:
            String startDate = dates[0];
            String endDate = dates[1];

            String startTime = times[0];
            String endTime = times[1];

            taskToAdd = new Event(taskDescription, startDate, endDate, startTime, endTime);
            break;

        case DEADLINE:
            String deadlineDate = dates[0];
            String deadlineTime = times[0];
            taskToAdd = new Deadline(taskDescription, deadlineDate, deadlineTime);
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
    public static void updateTask(LocalDate date, int taskIndex, String newTaskDescription, Scanner scanner)
            throws IndexOutOfBoundsException {
        try {
            List<Task> dayTasks = tasks.get(date);
            boolean dayHasTasks = dayTasks != null;
            boolean taskIndexExists = taskIndex >= 0 && taskIndex < Objects.requireNonNull(dayTasks).size();
            assert dayHasTasks;
            assert taskIndexExists;

            String oldDescription = dayTasks.get(taskIndex).getName();
            String currentTaskType = dayTasks.get(taskIndex).getTaskType();
            boolean startDateChanged = false;

            Task task;
            switch (currentTaskType) {
            case "T":
                task = new Todo(newTaskDescription);
                logger.log(Level.INFO, "Updating task description from " +
                        oldDescription + " to: " + newTaskDescription);
                break;
            case "E":
                Event oldEvent = (Event) dayTasks.get(taskIndex);
                System.out.println("Do you want to update the start and end dates and times? (yes/no)");
                String eventResponse = scanner.nextLine().trim().toLowerCase();
                if (eventResponse.equals("yes")) {
                    System.out.println("Enter the new start date, end date, start time and end time, " +
                            "separated by spaces:");
                    String[] newDatesAndTimes = scanner.nextLine().trim().split(" ");
                    String oldStartDate = oldEvent.getStartDate();

                    task = new Event(newTaskDescription, newDatesAndTimes[0], newDatesAndTimes[1], newDatesAndTimes[2],
                            newDatesAndTimes[3]);

                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate newEventStartDate;
                    try {
                        newEventStartDate = LocalDate.parse(newDatesAndTimes[0], dateFormatter);
                    } catch (DateTimeParseException e) {
                        throw new DateTimeParseException("Invalid date format. Please use the format dd/MM/yyyy.",
                                newDatesAndTimes[0], 0);
                    }

                    if (!newDatesAndTimes[0].equals(oldStartDate)) {
                        startDateChanged = true;
                        tasks.computeIfAbsent(newEventStartDate, k -> new ArrayList<>()).add(task);
                        tasks.get(LocalDate.parse(oldStartDate, dateFormatter)).remove(taskIndex);
                    }

                    logger.log(Level.INFO, "Updating task description from " +
                            oldDescription + " to: " + newTaskDescription);
                    logger.log(Level.INFO, "Updating task start date from " +
                            oldEvent.getStartDate() + " to: " + newDatesAndTimes[0]);
                    logger.log(Level.INFO, "Updating task end date from " + oldEvent.getEndDate() + " to: "
                            + newDatesAndTimes[1]);
                    logger.log(Level.INFO, "Updating task start time from " + oldEvent.getStartTime() + " to: "
                            + newDatesAndTimes[2]);
                    logger.log(Level.INFO, "Updating task end time from " + oldEvent.getEndTime() + " to: "
                            + newDatesAndTimes[3]);
                } else {
                    task = new Event(newTaskDescription, oldEvent.getStartDate(), oldEvent.getEndDate(),
                            oldEvent.getStartTime(), oldEvent.getEndTime());

                    logger.log(Level.INFO, "Updating task description from " +
                            oldDescription + " to: " + newTaskDescription);
                }

                break;
            case "D":
                Deadline oldDeadline = (Deadline) dayTasks.get(taskIndex);
                System.out.println("Do you want to update the deadline date and time? (yes/no)");
                String deadlineResponse = scanner.nextLine().trim().toLowerCase();
                if (deadlineResponse.equals("yes")) {
                    System.out.println("Enter the new deadline date and time, separated by a space:");
                    String[] newDateAndTime = scanner.nextLine().trim().split(" ");
                    task = new Deadline(newTaskDescription, newDateAndTime[0], newDateAndTime[1]);

                    logger.log(Level.INFO, "Updating task description from " +
                            oldDescription + " to: " + newTaskDescription);
                    logger.log(Level.INFO, "Updating task deadline date from " + oldDeadline.getByDate() + " to: "
                            + newDateAndTime[0]);
                    logger.log(Level.INFO, "Updating task deadline time from " + oldDeadline.getByTime() + " to: "
                            + newDateAndTime[1]);
                } else {
                    task = new Deadline(newTaskDescription, oldDeadline.getByDate(), oldDeadline.getByTime());

                    logger.log(Level.INFO, "Updating task description from " +
                            oldDescription + " to: " + newTaskDescription);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid task type");
            }

            if (!startDateChanged) {
                dayTasks.set(taskIndex, task);
            }

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
    public void addManager(Scanner scanner, WeekView weekView, MonthView monthView,boolean inMonthView, String action,
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
        String typeName = taskType.equals(TODO) ? "Todo" : taskType.equals(DEADLINE) ? "Deadline" : "Event";

        // vvv Below methods should be recreated when console inputs are streamlined
        if (taskType == null) {
            throw new TaskManagerException("Invalid task type. Please provide valid task type: " +
                    "T for Todo, E for event, D for deadline.");
        } else if (taskType == DEADLINE) {
            System.out.println("Enter the deadline date and time of this task, separated by a space:");
            String[] deadlineDateAndTime = scanner.nextLine().trim().split(" ");
            String[] deadlineDate = new String[]{deadlineDateAndTime[0]};
            String[] deadlineTime = new String[]{deadlineDateAndTime[1]};

            addTask(date, taskDescription, taskType, deadlineDate, deadlineTime);
        } else if (taskType == EVENT) {
            System.out.println("Enter the start date of this task, along with the start time separated by a space:");
            String[] startDateAndTime = scanner.nextLine().trim().split(" ");
            String startDate = startDateAndTime[0];
            String startTime = startDateAndTime[1];

            System.out.println("Enter the end date of this task, along with the end time separated by a space:");
            String[] endDateAndTime = scanner.nextLine().trim().split(" ");
            String endDate = endDateAndTime[0];
            String endTime = endDateAndTime[1];

            String [] startAndEndDates = new String[]{startDate, endDate};
            String [] startAndEndTimes = new String[]{startTime, endTime};

            addTask(date, taskDescription, taskType, startAndEndDates, startAndEndTimes);
        } else {
            String[] dummyDates = {null}; // dummy String array to pass into function call
            String[] dummyTimes = {null}; // dummy String array to pass into function call

            addTask(date, taskDescription, taskType, dummyDates, dummyTimes);
        }

        // Save tasks to file
        saveTasksToFile(tasks, Storage.FILE_PATH); // Update tasks.txt file
        System.out.println(typeName + " added.");
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

        String currentTaskType = taskManager.getTasksForDate(date).get(taskIndex - 1).getTaskType();
        String typeName = currentTaskType.equals("T") ? "Todo" : currentTaskType.equals("D") ? "Deadline" : "Event";

        updateTask(date, taskIndex - 1, newDescription, scanner);
        saveTasksToFile(tasks,Storage.FILE_PATH); //Update tasks.txt file
        System.out.println(typeName + " updated.");

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
                String[] times = new String[]{null, null};

                switch (taskType) {
                case TODO:
                    dates[0] = taskDescription;
                    break;

                case EVENT:
                    String startDate = task.getStartDate();
                    String endDate = task.getEndDate();

                    String startTime = task.getStartTime();
                    String endTime = task.getEndTime();

                    dates[0] = startDate;
                    dates[1] = endDate;

                    times[0] = startTime;
                    times[1] = endTime;
                    break;

                case DEADLINE:
                    String deadlineDate = task.getByDate();
                    String deadlineTime = task.getByTime();

                    dates[0] = deadlineDate;
                    times[0] = deadlineTime;
                    break;

                default:
                    logger.log(Level.INFO, "Task to add was invalid. Task in question: " + taskDescription);
                }

                addTask(date, taskDescription, taskType, dates, times);
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
                                     TaskManager taskManager,String day, int taskIndex) throws TaskManagerException,
            DateTimeParseException {

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

