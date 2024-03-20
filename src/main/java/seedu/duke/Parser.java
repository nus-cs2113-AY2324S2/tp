package seedu.duke;

import seedu.duke.ui.UI;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public Parser() {}

    /**
     * Parses User Input and Identifies the command used.
     *
     * @param command The users text input.
     */
    public void parseCommand(String command) {
        if (command.toLowerCase().equals("list")) {
            UI.printListingUsers();
            UserList.ListAll();
        } else if (command.toLowerCase().equals("bye")) {
            UI.printBye();
            Duke.setFinished(true);
        } else if (command.toLowerCase().equals("current")) {
            UI.printActiveUser(UserList.GetActiveUser().getName());
        } else if (command.toLowerCase().equals("view")) {
            UserList.GetActiveUser().viewTimetable();
        } else if (command.toLowerCase().startsWith("adduser")) {
            try {
                InputValidator.validateAddUserInput(command);
                String[] parts = command.split("\\s+");
                String userName = parts[1];
                User newUser = new User(userName);
                UI.printNewUser(newUser.getName());
                UserList.AddUser(newUser);
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            }
        } else if (command.toLowerCase().startsWith("switch")) {
            try {
                InputValidator.validateSwitchInput(command);
                String[] parts = command.split("\\s+");
                String userName = parts[1];
                UserList.SetActiveUser(UserList.FindUser(userName));
                UI.printActiveUser(UserList.GetActiveUser().getName());
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            }
        } else if (command.toLowerCase().startsWith("addtask")) {
            try {
                InputValidator.validateAddTaskInput(command);
                String[] parts = command.split("\\s+");
                List<String> wordList = Arrays.asList(parts);
                String day = parts[2];
                String description = parseDescription(wordList);
                String startTime = parts[wordList.indexOf("/from") + 1];
                String endTime = parts[wordList.indexOf("/to") + 1];
                InputValidator.validateDay(day);
                Task task = new Task(description, day, startTime, endTime);
                UserList.GetActiveUser().getTimetable().addUserTask(day, task);
            } catch (InvalidFormatException | InvalidDayException e) {
                System.out.println(e.getMessage());
            }
        } else if (command.toLowerCase().startsWith("deletetask")) {
            try {
                InputValidator.validateDeleteTaskInput(command);
                String[] parts = command.split("\\s+");
                String day = parts[2];
                int index = Integer.parseInt(parts[4]);
                InputValidator.validateDay(day);
                UserList.GetActiveUser().getTimetable().deleteUserTask(day, index);
            } catch (InvalidFormatException | InvalidDayException e) {
                System.out.println(e.getMessage());
            }
        } else if (command.toLowerCase().startsWith("compare")) {
            try {
                InputValidator.validateCompareInput(command);
                String[] parts = command.split("\\s+");
                String user1 = parts[1];
                String user2 = parts[2];
                InputValidator.validateUserInput(user1);
                InputValidator.validateUserInput(user2);
                Timetable.compareTimetable(UserList.FindUser(user1).getTimetable(), UserList.FindUser(user2).getTimetable());

            } catch (InvalidFormatException | InvalidUserException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } else {
            UI.printInvalidCommand();
        }
    }

    private String parseDescription(List<String> words) {
        int startIndex = words.indexOf("/task") + 1;
        int endIndex = words.indexOf("/from") - 1;
        StringBuilder description = new StringBuilder();
        for (int i = startIndex; i <= endIndex; i++) {
            description.append(words.get(i));
            if (i < endIndex) {
                description.append(" ");
            }
        }
        return description.toString();
    }
}
