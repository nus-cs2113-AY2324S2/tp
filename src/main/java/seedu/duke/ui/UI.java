package seedu.duke.ui;

import seedu.duke.Task;

public class UI {
    public static void printGreeting() {
        System.out.println("Timetable comparison app opened. ");
    }

    public static void printBye() {
        System.out.println("Bye. ");
    }

    public static void printListingUsers() {
        System.out.println("The current users are: ");
    }

    public static void printNewUser(String name) {
        System.out.println("New user added: " + name);
    }

    public static void printActiveUser(String description) {
        System.out.println("The active user is: " + description);
    }

    public static void printInvalidCommand() {
        System.out.println("Invalid command. ");
    }

    public static void printAddTask(Task task) {
        System.out.println("The following task is added: " + task);
    }

    public static void printHelp() {
        System.out.println("List of available commands: \n" +
                "adduser: add new user \n" +
                "switch <username>: switch to user \n" +
                "list: list all users \n" +
                "bye: exit the app \n" +
                "current: view current user \n" +
                "addtask /on <day> /from <start time> /to <end time>: add task \n" +
                "delete: delete task \n" +
                "compare <user1> <user2>: compare timetables ");
    }

    public static void printAddForAll(Task task) {
        System.out.println("The following task is added for all users: " + task.toString());
    }
}
