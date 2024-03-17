package data;

import Time.WeekView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static data.TaskManagerException.checkIfDateHasTasks;
import static data.TaskManagerException.checkIfDateInCurrentWeek;


public class TaskManager {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Map<LocalDate, List<String>> tasks = new HashMap<>();

    public static void addTask(LocalDate date, String task) {
        tasks.computeIfAbsent(date, k -> new ArrayList<>()).add(task);
    }

    public void deleteTask(LocalDate date, int taskIndex) {
        List<String> dayTasks = tasks.get(date);
        if (dayTasks != null && taskIndex >= 0 && taskIndex < dayTasks.size()) {
            dayTasks.remove(taskIndex);
            if (dayTasks.isEmpty()) {
                tasks.remove(date);
            }
        }
    }

    public static void updateTask(LocalDate date, int taskIndex, String updatedTask) throws TaskManagerException {
        try {
            List<String> dayTasks = tasks.get(date);
            boolean dayHasTasks = dayTasks != null;
            boolean taskIndexExists = taskIndex >= 0 && taskIndex < Objects.requireNonNull(dayTasks).size();
            if (dayHasTasks && taskIndexExists) {
                dayTasks.set(taskIndex, updatedTask);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
    }


    public List<String> getTasksForDate(LocalDate date) {
        return tasks.getOrDefault(date, new ArrayList<>());
    }

    public static void addManager(Scanner scanner, WeekView weekView, TaskManager taskManager)
            throws TaskManagerException {
        System.out.println("Enter the date for the task (dd/MM/yyyy):");
        LocalDate date = parseInputDate(scanner);

        checkIfDateInCurrentWeek(date, weekView);

        System.out.println("Enter the task description:");
        String task = scanner.nextLine().trim();

        addTask(date, task);
        System.out.println("Task added.");
    }

    public static void updateManager(Scanner scanner, WeekView weekView, TaskManager taskManager) throws TaskManagerException {
        System.out.println("Enter the date for the task you wish to update (dd/MM/yyyy):");
        LocalDate date = parseInputDate(scanner);

        checkIfDateInCurrentWeek(date, weekView);

        listTasksAtDate(taskManager, date, "Enter the task number of the task you wish to update:");

        int taskNumber;
        String updatedDescription;

        try {
            taskNumber = Integer.parseInt(scanner.nextLine().trim());

            System.out.println("Enter the updated task description:");
            updatedDescription = scanner.nextLine().trim();

            updateTask(date, taskNumber - 1, updatedDescription);
            System.out.println("Task updated.");
        } catch (NumberFormatException e) {
            System.out.println("Task number should be an integer value. Please try again.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task number you have entered does not exist. Please try again.");
        }

    }

    private static void listTasksAtDate(TaskManager taskManager, LocalDate date, String message) throws TaskManagerException {
        List<String> dayTasks = taskManager.getTasksForDate(date);
        checkIfDateHasTasks(dayTasks);

        System.out.println(message);
        for (int i = 0; i < dayTasks.size(); i++) {
            System.out.println((i + 1) + ". " + dayTasks.get(i));
        }
    }

    public static void deleteManager(Scanner scanner, WeekView weekView, TaskManager taskManager)
            throws DateTimeParseException, TaskManagerException {

        System.out.println("Enter the date for the task to delete (dd/MM/yyyy):");
        LocalDate date = parseInputDate(scanner);

        checkIfDateInCurrentWeek(date, weekView);

        listTasksAtDate(taskManager, date, "Enter the task number to delete:");

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(scanner.nextLine().trim());
            taskManager.deleteTask(date, taskNumber - 1);
            System.out.println("Task deleted.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number. Please try again.");
        }
    }

    // to abstract as Parser/UI function
    private static LocalDate parseInputDate(Scanner scanner) throws DateTimeParseException {
        String dateString = scanner.nextLine().trim();
        LocalDate date;
        date = LocalDate.parse(dateString, dateFormatter);
        return date;
    }
}
