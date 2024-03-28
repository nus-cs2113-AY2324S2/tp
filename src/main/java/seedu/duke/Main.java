package seedu.duke;

import data.Task;
import net.fortuna.ical4j.data.ParserException;
import storage.Storage;
import time.DateUtils;
import time.MonthView;
import time.WeekView;
import data.TaskManager;
import data.TaskManagerException;
import log.FileLogger;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static storage.Storage.createNewFile;
import static data.TaskManager.deleteManager;
import static ui.UiRenderer.printHelp;


public class Main {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) throws IOException, TaskManagerException, ParserException {
        FileLogger.setupLogger();
        Scanner scanner = new Scanner(System.in);
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = DateUtils.getStartOfWeek(today);
        WeekView weekView = new WeekView(startOfWeek, dateFormatter);
        TaskManager taskManager = new TaskManager();
        LocalDate startOfMonth = today.withDayOfMonth(1);
        MonthView monthView = new MonthView(startOfMonth, dateFormatter);

        boolean printWeek = true; // Flag to control printing of the week view
        boolean inMonthView = false; // Flag to indicate if we are in month view mode

        createNewFile(Storage.FILE_PATH); //Creates directory and tasks.txt file if it does not exist
        Map<LocalDate, List<Task>> tasksFromFile =
                Storage.loadTasksFromFile(Storage.FILE_PATH); //Reads tasks from txt file
        taskManager.addTasksFromFile(tasksFromFile); //Loads tasks from txt file

        //IcsHandler.generateICS(); //uncomment when developed

        while (true) {
            if (printWeek) {
                if (!inMonthView) {
                    weekView.printView(taskManager);
                } else {
                    monthView.printView(taskManager);
                }
            }
            printWeek = true; // Reset flag for the next iteration
            System.out.println("Enter 'next' for next week, 'prev' for previous week, " +
                    "'add' to add a task, " +
                    "'update' to edit a task, " +
                    "'delete' to delete a task, " +
                    "'month' to display the month view, " +
                    "or 'quit' to quit:");
            System.out.println("Enter help to learn commands");
            String input = scanner.nextLine().trim().toLowerCase();
            String command = input.split(",")[0];
            switch (command) {
            case "next":
                if (inMonthView) {
                    monthView.next();
                } else {
                    weekView.next();
                }
                break;
            case "prev":
                if (inMonthView) {
                    monthView.previous();
                } else {
                    weekView.previous();
                }
                break;
            case "update":
                try {
                    String[] parts = input.split(",\\s*");
                    if (parts.length < 4) {
                        throw new TaskManagerException("Invalid input format. Please provide input in the format: " +
                                "update, <day>, <taskIndex>, <newDescription>");
                    }
                    int day = Integer.parseInt(parts[1].trim());
                    int taskIndex = Integer.parseInt(parts[2].trim());
                    String newDescription = parts[3].trim();
                    taskManager.updateManager(scanner, weekView, monthView, inMonthView, taskManager, day,
                            taskIndex, newDescription);
                } catch (TaskManagerException | DateTimeParseException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "add":
                try {
                    String[] parts = input.split(",\\s*");
                    if (parts.length < 4) {
                        throw new TaskManagerException("Invalid input format. Please provide input in the format: " +
                                "add, <day>, <taskType>, <taskDescription>");
                    }
                    String action = parts[0];
                    //int day = Integer.parseInt(parts[1].trim());
                    String day = parts[1].trim();
                    String taskTypeString = parts[2].trim();
                    String taskDescription = parts[3].trim();
                    taskManager.addManager(scanner, weekView,monthView, inMonthView, action, day,
                            taskTypeString, taskDescription);
                } catch (TaskManagerException | DateTimeParseException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "delete":
                try {
                    String[] parts = input.split(",\\s*");
                    if (parts.length != 3) {
                        throw new TaskManagerException("Invalid input format. Please provide input in the format: " +
                                "delete, <day>, <taskIndex>");
                    }
                    String day = parts[1].trim();
                    int taskIndex = Integer.parseInt(parts[2].trim());
                    deleteManager(weekView, monthView, inMonthView, taskManager, day, taskIndex);
                } catch (TaskManagerException | DateTimeParseException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "month":
                monthView.printView(taskManager);
                inMonthView = !inMonthView; // Toggle month view mode
                printWeek = false;
                break;
            case "week":
                inMonthView = false;
                break;
            case "help":
                printHelp();
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
}
