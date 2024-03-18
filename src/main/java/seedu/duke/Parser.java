package seedu.duke;
import java.util.Scanner;
import java.util.ArrayList;
public class Parser {
    private final User user;

    public Parser(User user) {
        this.user = user;
    }
    public void parseCommand(String command) {
        String[] parts = command.split("/"); // Splitting command into parts using slash

        if (parts.length != 4 || !parts[0].trim().startsWith("add")) {
            System.out.println("Invalid command format.");
            return;
        }

        // Extracting description, day, start time, and end time from the parts
        String description = parts[0].trim().substring(4).trim();
        String day = parts[1].trim().substring(3).trim(); // Removing "on" from day
        String from = parts[2].trim().substring(5).trim(); // Removing "from" from start time
        String to = parts[3].trim().substring(3).trim(); // Removing "to" from end time

        Task task = new Task(description, day, from, to);

        // Adding task to the User's task list
        int dayOfWeek = parseDayOfWeek(day);
        if (dayOfWeek != -1) {
            user.addUserTask(dayOfWeek, task);
            System.out.println("Task added successfully.");
        } else {
            System.out.println("Invalid day of the week.");
        }
    }

    private int parseDayOfWeek(String day) {
        // Mapping day of the week to index (0-6)
        switch (day.toLowerCase()) {
        case "sunday":
            return 0;
        case "monday":
            return 1;
        case "tuesday":
            return 2;
        case "wednesday":
            return 3;
        case "thursday":
            return 4;
        case "friday":
            return 5;
        case "saturday":
            return 6;
        default:
            return -1; // Invalid day
        }
    }

    public void deleteTask(String command) {
        String[] parts = command.split(" "); // Splitting command into parts using space

        if (parts.length != 2 || !parts[0].trim().equalsIgnoreCase("delete")) {
            System.out.println("Invalid delete command format.");
            return;
        }

        try {
            int index = Integer.parseInt(parts[1].trim());
            int dayOfWeek = 0; // Assume we are always deleting from Sunday for now
            user.deleteUserTask(dayOfWeek, index - 1); // Adjust index by -1 to match array index
            System.out.println("Task deleted successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid task index.");
        }
    }
}

}
