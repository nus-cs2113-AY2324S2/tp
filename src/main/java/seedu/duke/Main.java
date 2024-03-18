package seedu.duke;

import Storage.Storage;
import Time.DateUtils;
import Time.WeekView;
import data.TaskManager;
import data.TaskManagerException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Storage.Storage.createNewFile;
import static Storage.Storage.loadTasksFromFile;
import static data.TaskManager.addManager;
import static data.TaskManager.deleteManager;
import static data.TaskManager.updateManager;


public class Main {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = DateUtils.getStartOfWeek(today);
        WeekView weekView = new WeekView(startOfWeek, dateFormatter);
        TaskManager taskManager = new TaskManager();

        boolean printWeek = true; // Flag to control printing of the week view
        boolean inMonthView = false; // Flag to indicate if we are in month view mode

        createNewFile(); //Creates directory and tasks.txt file if it does not exist
        Map<LocalDate, List<String>> tasksFromFile = Storage.loadTasksFromFile(); //Reads tasks from txt file
        taskManager.addTasksFromFile(tasksFromFile); //Loads tasks from txt file

        while (true) {
            if (printWeek) {
                if (!inMonthView) {
                    weekView.printWeekView(taskManager);
                } else {
                    weekView.printMonthView(taskManager);
                }
            }
            printWeek = true; // Reset flag for the next iteration
            System.out.println("Enter 'next' for next week, 'prev' for previous week, 'add' to add a task, " +
                    "'update' to edit a task, " +
                    "'delete' to delete a task, " + 
                    "'month' to display the month view, " +
                    "or 'quit' to quit:");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
            case "next":
                if (inMonthView) {
                    weekView.nextMonth();
                } else {
                    weekView.nextWeek();
                }
                break;
            case "prev":
                if (inMonthView) {
                    weekView.previousMonth();
                } else {
                    weekView.previousWeek();
                } 
                break;
            case "update":
                updateManager(scanner, weekView, taskManager);
                break;
            case "add":
                try {
                    addManager(scanner, weekView, taskManager, inMonthView);
                } catch (TaskManagerException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "delete":
                try {
                    deleteManager(scanner, weekView, taskManager);
                } catch (TaskManagerException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "month":
                weekView.printMonthView(taskManager);
                inMonthView = !inMonthView; // Toggle month view mode
                printWeek = false;
                break;
            case "week":
                inMonthView = false;
                //weekView.printWeekView(taskManager);
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
