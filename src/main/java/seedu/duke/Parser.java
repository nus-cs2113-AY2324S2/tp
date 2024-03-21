package seedu.duke;

import seedu.duke.ui.UI;

import java.util.Arrays;
import java.util.List;

public class Parser {

    /**
     * Parses User Input and Identifies the command used.
     *
     * @param command The users text input.
     */
    public void parseCommand(String command, UserList userList) {
        if (command.equalsIgnoreCase("list")) {
            UI.printListingUsers();
            userList.listAll();
        } else if (command.equalsIgnoreCase("help")) {
            UI.printHelp();
        } else if (command.equalsIgnoreCase("bye")) {
            UI.printBye();
            Duke.setIsFinished(true);
        } else if (command.equalsIgnoreCase("current")) {
            UI.printActiveUser(userList.getActiveUser().getName());
        } else if (command.equalsIgnoreCase("view")) {
            userList.getActiveUser().viewTimetable();
        } else if (command.toLowerCase().startsWith("adduser")) {
            try {
                InputValidator.validateAddUserInput(command);
                String[] parts = command.split("\\s+");
                String userName = parts[1];
                User newUser = new User(userName);
                UI.printNewUser(newUser.getName());
                userList.addUser(newUser);
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            }
        } else if (command.toLowerCase().startsWith("switch")) {
            try {
                InputValidator.validateSwitchInput(command);
                String[] parts = command.split("\\s+");
                String userName = parts[1];
                userList.setActiveUser(userList.findUser(userName));
                UI.printActiveUser(userList.getActiveUser().getName());
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
                String type = parts[wordList.indexOf("/type") + 1];
                InputValidator.validateDay(day);
                Task task = new Task(description, day, startTime, endTime, type);
                userList.getActiveUser().getTimetable().addUserTask(day, task);
                UI.printAddTask(task);
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
                userList.getActiveUser().getTimetable().deleteUserTask(day, index);
            } catch (InvalidFormatException | InvalidDayException e) {
                System.out.println(e.getMessage());
            }
        } else if (command.toLowerCase().startsWith("compare")) {
            try {
                InputValidator.validateCompareInput(command);
                String[] parts = command.split("\\s+");
                String user1 = parts[1];
                String user2 = parts[2];
                InputValidator.validateUserInput(user1, userList);
                InputValidator.validateUserInput(user2, userList);
                Timetable.compareTimetable(userList.findUser(user1).getTimetable(),
                        userList.findUser(user2).getTimetable());

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
