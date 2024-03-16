package seedu.duke;

import java.util.Scanner;

java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */


private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


Scanner scanner = new Scanner(System.in);
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = DateUtils.getStartOfWeek(today);
        WeekView weekView = new WeekView(startOfWeek, dateFormatter);
        TaskManager taskManager = new TaskManager();

        while (true) {
            weekView.printWeekView(taskManager);
            System.out.println("Enter 'next' for next week, 'prev' for previous week, 'add' to add a task, " +
                    "'delete' to delete a task, or 'quit' to quit:");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
            case "next":
                weekView.nextWeek();
                break;
            case "prev":
                weekView.previousWeek();
                break;
            case "add":
                addTask(scanner, weekView, taskManager);
                break;
            case "delete":
                deleteTask(scanner, weekView, taskManager);
                break;
            case "quit":
                System.out.println("Exiting Calendar...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void addTask(Scanner scanner, WeekView weekView, TaskManager taskManager) {
        System.out.println("Enter the date for the task (dd/MM/yyyy):");
        String dateString = scanner.nextLine().trim();
        LocalDate date;
        try {
            date = LocalDate.parse(dateString, dateFormatter);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please try again.");
            return;
        }

        if (date.isBefore(weekView.getStartOfWeek()) || date.isAfter(weekView.getStartOfWeek().plusDays(6))) {
            System.out.println("The date must be within the current week. Please try again.");
            return;
        }

        System.out.println("Enter the task description:");
        String task = scanner.nextLine().trim();

        taskManager.addTask(date, task);
        System.out.println("Task added.");
    }

    private static void deleteTask(Scanner scanner, WeekView weekView, TaskManager taskManager) {
        System.out.println("Enter the date for the task to delete (dd/MM/yyyy):");
        String dateString = scanner.nextLine().trim();
        LocalDate date;
        try {
            date = LocalDate.parse(dateString, dateFormatter);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please try again.");
            return;
        }

        if (date.isBefore(weekView.getStartOfWeek()) || date.isAfter(weekView.getStartOfWeek().plusDays(6))) {
            System.out.println("The date must be within the current week. Please try again.");
            return;
        }

        List<String> dayTasks = taskManager.getTasksForDate(date);
        if (dayTasks.isEmpty()) {
            System.out.println("No tasks to delete on this date.");
            return;
        }

        System.out.println("Enter the task number to delete:");
        for (int i = 0; i < dayTasks.size(); i++) {
            System.out.println((i + 1) + ". " + dayTasks.get(i));
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(scanner.nextLine().trim());
            taskManager.deleteTask(date, taskNumber - 1);
            System.out.println("Task deleted.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number. Please try again.");
        }

     }
}
