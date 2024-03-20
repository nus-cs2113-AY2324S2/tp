package seedu.duke;

import seedu.duke.ui.UI;

public class Parser {
    private static User user;
    private static final int DAY_INDEX = 2;
    private static final int DESCRIPTION_INDEX = 3;
    private static final int FROM_INDEX = 4;
    private static final int TO_INDEX = 5;

    public Parser(User user) {
        Parser.user = user;
    }

    public void parseCommand(String command) {
        String[] parts = command.split("/"); // Splitting command into parts using slash

        if (parts.length != 6 || !parts[0].trim().startsWith("add")) {
            System.out.println("Invalid command format.");
            return;
        }

        //Parse the compare command and split them into parts "compare user1 user2"
        if (command.toLowerCase().startsWith("compare")) {
            String[] parts = command.split("\\s+");
            try {
                InputValidator.validateCompareInput(command);
                User user1 = parts[1];
                User user2 = parts[2];
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        // Extracting description, day, start time, and end time from the parts
        String description = parts[DESCRIPTION_INDEX].trim().substring(5).trim();
        String day = parts[DAY_INDEX].trim().substring(3).trim(); // Removing "on" from day
        String from = parts[FROM_INDEX].trim().substring(5).trim(); // Removing "from" from start time
        String to = parts[TO_INDEX].trim().substring(3).trim(); // Removing "to" from end time

        Task task = new Task(description, day, from, to);

        // Adding task to the User's task list
        try {
            Timetable.checkDay(day);
            user.timetable.addUserTask(day, task);
            UI.printAddTask(description);
        } catch (InvalidDayException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(String command) {
        String[] parts = command.split("/"); // Splitting command into parts using space

        if (parts.length != 2 || !parts[0].trim().equalsIgnoreCase("delete")) {
            System.out.println("Invalid delete command format.");
            return;
        }

        try {
            int index = Integer.parseInt(parts[1].trim());
            int dayOfWeek = 0; // Assume we are always deleting from Sunday for now
            user.timetable.deleteUserTask("sunday", index - 1); // Adjust index by -1 to match array index
            System.out.println("Task deleted successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid task index.");
        }
    }
}
