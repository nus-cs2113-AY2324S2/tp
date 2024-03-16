package seedu.duke;

import Time.DateUtils;
import Time.WeekView;
import data.TaskManager;
import data.TaskManagerException;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static data.TaskManager.addManager;
import static data.TaskManager.deleteManager;

public class Main {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
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

            try {
                switch (input) {
                case "next":
                    weekView.nextWeek();
                    break;
                case "prev":
                    weekView.previousWeek();
                    break;
                case "add":
                    addManager(scanner, weekView, taskManager);
                    break;
                case "delete":
                    deleteManager(scanner, weekView, taskManager);
                    break;
                case "quit":
                    System.out.println("Exiting Calendar...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            } catch (TaskManagerException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
