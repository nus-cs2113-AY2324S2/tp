package seedu.duke;
import java.util.Scanner;
import java.util.ArrayList;
public class Parser {
    private static void addTask(Scanner scanner, ArrayList<Task> tasks) {
        System.out.println("Enter task details:");
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("From: ");
        String from = scanner.nextLine();
        System.out.print("To: ");
        String to = scanner.nextLine();

        // Creating Task object from user input
        Task task = new Task(description, from, to);

        // Adding task to the ArrayList
        tasks.add(task);

        System.out.println("Task added successfully.");
    }
    private static void deleteTask(Scanner scanner, ArrayList<Task> tasks) {
        // Printing tasks
        System.out.println("\nAll tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println("Index: " + (i + 1));
            System.out.println("Description: " + t.description);
            System.out.println("From: " + t.from);
            System.out.println("To: " + t.to);
            System.out.println();
        }

        // Option to delete a task
        System.out.print("Enter the index of the task to delete: ");
        String deleteInput = scanner.nextLine();
        try {
            int index = Integer.parseInt(deleteInput);
            if (index >= 1 && index <= tasks.size()) {
                tasks.remove(index - 1);
                System.out.println("Task at index " + index + " deleted successfully.");
            } else {
                System.out.println("Invalid index.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid index.");
        }
    }
}
