package seedu.duke;

import seedu.duke.exceptions.InvalidDayException;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.exceptions.InvalidUserException;
import seedu.duke.ui.UI;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class Parser {

    protected static final String[] DAYS = new String[]
        {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    /**
     * Parses User Input and Identifies the command used.
     *
     * @param command The users text input.
     */
    public static void parseCommand(String command, UserList userList) throws
            InvalidFormatException, InvalidDayException, InvalidUserException {
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
            InputValidator.validateAddUserInput(command);
            String[] parts = command.split("\\s+");
            String userName = parts[1];
            User newUser = new User(userName);
            UI.printNewUser(newUser.getName());
            userList.addUser(newUser);
            Storage.addUserInFolder();
        } else if (command.toLowerCase().startsWith("switch")) {
            InputValidator.validateSwitchInput(command);
            String[] parts = command.split("\\s+");
            String userName = parts[1];
            userList.setActiveUser(userList.findUser(userName));
            UI.printActiveUser(userList.getActiveUser().getName());
        } else if (command.toLowerCase().startsWith("addtask")) {
            addTask(command, userList);
            User currentUser = userList.getActiveUser();
            currentUser.getStorage().writeTaskInFile(currentUser);
        } else if (command.toLowerCase().startsWith("deletetask")) {
            deleteTask(command, userList);
        } else if (command.toLowerCase().startsWith("changetasktiming")) {
            changeTaskTiming(command, userList);
        } else if (command.toLowerCase().startsWith("addrepeattask")) {
            addRepeatTask(command, userList);
        } else if (command.toLowerCase().startsWith("changetasktype")) {
            changeTaskType(command, userList);
        } else if (command.toLowerCase().startsWith("compareall")) {
            UI.printComparingAll();
            UI.printSharedTime(Timetable.compareAllTimetables(userList));
        } else if (command.toLowerCase().startsWith("compare")) {
            try {
                InputValidator.validateCompareInput(command);
                String[] parts = command.split("\\s+");
                String user1 = parts[1];
                String user2 = parts[2];
                InputValidator.validateUserInput(user1, userList);
                InputValidator.validateUserInput(user2, userList);
                UI.printSharedTime(Timetable.compareTimetable(userList.findUser(user1).getTimetable(),
                        userList.findUser(user2).getTimetable()));

            } catch (InvalidFormatException | InvalidUserException | NullPointerException e) {
                System.out.println(e.getMessage());
            }

        } else if (command.toLowerCase().startsWith("addforall")) {
            addTaskForAll(command, userList);
        } else if (command.toLowerCase().startsWith("viewcommonevents")) {
            printConfirmedEvent(userList);
        } else {
            UI.printInvalidCommand();
        }
    }

    private static void changeTaskType(String command, UserList userList) throws InvalidFormatException {
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

    private static void addTask(String command, UserList userList) {
        try {
            InputValidator.validateAddTaskInput(command);
            Task task = parseTask(command);
            userList.getActiveUser().getTimetable().addUserTask(task.day, task);
            UI.printAddTask(task);
        } catch (InvalidFormatException | InvalidDayException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Task parseTask(String command) throws InvalidDayException {
        String[] parts = command.split("\\s+");
        List<String> wordList = Arrays.asList(parts);
        String day = parts[2];
        String description = parseDescription(wordList);
        String startTime = parts[wordList.indexOf("/from") + 1];
        String endTime = parts[wordList.indexOf("/to") + 1];

        String type;
        if (wordList.contains("/type")) {
            type = parts[wordList.indexOf("/type") + 1];
        } else {
            type = "common";
        }

        InputValidator.validateDay(day);
        return new Task(description, day, startTime, endTime, type);
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
            userList.getActiveUser().getTimetable().changeFlexibleTaskTiming(day,
                    index - 1, newStartTime, newEndTime);
            System.out.println("Flexible task timing changed successfully.");
        } catch (InvalidDayException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addRepeatTask(String command, UserList userList) {
        try {
            InputValidator.validateAddRepeatTask(command);
            String[] parts = command.split("\\s+");
            List<String> wordlist = Arrays.asList(parts);
            int taskIndex = wordlist.indexOf("/task");
            if (taskIndex == -1 || taskIndex + 1 >= wordlist.size()) {
                throw new InvalidFormatException(("Please enter a task name!"));
            }
            String description = wordlist.get(taskIndex + 1);
            int daysIndex = wordlist.indexOf("/on") + 1;
            int endDaysIndex = wordlist.indexOf("/from");
            String[] days = Arrays.copyOfRange(parts, daysIndex, endDaysIndex);
            if (days.length < 2) {
                throw new InvalidFormatException("Please enter at least 2 days, or you want to use addtask command!");
            }
            String startTime = parts[wordlist.indexOf("/from") + 1];
            String endTime = parts[wordlist.indexOf("/to") + 1];
            String type = parts[wordlist.indexOf("/type") + 1];
            for (String day : days) {
                Task task = new Task(description, day, startTime, endTime, type);
                userList.getActiveUser().getTimetable().addUserTask(day, task);
            }
            System.out.println("Repeated task added successfully!");
        } catch (InvalidFormatException e) {
            System.out.println("Please enter at least 2 days, or you want to use addtask command!");
        }
    }

    private static String parseDescription(List<String> words) {
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

    private static void addTaskForAll(String command, UserList userList)
            throws InvalidFormatException, InvalidDayException {
        InputValidator.validateAddTaskForAll(command);
        Task task = parseTask(command);
        assert !userList.getUsers().isEmpty() : "There is no user added.";
        for (User user : userList.getUsers()) {
            user.getTimetable().addUserTask(task.day, task);
        }
        UI.printAddForAll(task);
    }

    private static void printConfirmedEvent(UserList userList) {
        assert !userList.getUsers().isEmpty() : "There is no user added.";
        int taskCount = 1;
        for (String day : DAYS) {
            for (Task task : userList.getActiveUser().getTimetable().getWeeklyTasks().get(day)) {
                if (task.type.equals("common")) {
                    System.out.println(taskCount + ". " + task);
                }
            }
        }
    }
}
