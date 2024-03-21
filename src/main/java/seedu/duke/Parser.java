package seedu.duke;

import seedu.duke.ui.UI;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class Parser {

    /**
     * Parses User Input and Identifies the command used.
     *
     * @param command The users text input.
     */
    public void parseCommand(String command, UserList userList) throws InvalidFormatException {
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
            addTask(command, userList);
        } else if (command.toLowerCase().startsWith("deletetask")) {
            deleteTask(command, userList);
        } else if(command.toLowerCase().startsWith("changetasktiming")){
            changeTaskTiming(command, userList);
        } else if(command.toLowerCase().startsWith("changetasktype")){
            try {
                InputValidator.validateChangeTaskType(command);
                String[] parts = command.split("\\s+");
                List<String> wordList = Arrays.asList(parts);
                String day = wordList.get(2);
                int index = Integer.parseInt(wordList.get(wordList.indexOf("/index") + 1));
                String newType = wordList.get(wordList.indexOf("/type") + 1);
                InputValidator.validateDay(day);
                userList.getActiveUser().getTimetable().changeTaskType(day, index - 1, newType);
                System.out.println("Task type changed successfully.");
            } catch (InvalidDayException | IndexOutOfBoundsException | NumberFormatException e) {
                throw new RuntimeException(e);
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

    private static void deleteTask(String command, UserList userList) {
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
    }

    private void addTask(String command, UserList userList) {
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
    }

    private static void changeTaskTiming(String command, UserList userList) throws InvalidFormatException {
        try {
            InputValidator.validateChangeTaskTiming(command);
            String[] parts = command.split("\\s+");
            List<String> wordList = Arrays.asList(parts);
            String day = parts[2];
            int index = Integer.parseInt(parts[wordList.indexOf("/index") + 1]);
            LocalTime newStartTime = LocalTime.parse(parts[wordList.indexOf("/from") + 1]);
            LocalTime newEndTime = LocalTime.parse(parts[wordList.indexOf("/to") + 1]);
            InputValidator.validateDay(day);
            userList.getActiveUser().getTimetable().changeFlexibleTaskTiming(day, index - 1, newStartTime, newEndTime);
            System.out.println("Flexible task timing changed successfully.");
        } catch (InvalidDayException e) {
            throw new RuntimeException(e);
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
