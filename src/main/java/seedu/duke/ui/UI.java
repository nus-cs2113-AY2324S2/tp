package seedu.duke.ui;

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

    public static void printAddTask(String description) {
        System.out.println("The following task is added: ");
        System.out.println(description);
    }
}
