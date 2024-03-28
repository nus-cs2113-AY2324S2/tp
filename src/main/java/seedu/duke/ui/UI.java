package seedu.duke.ui;

import seedu.duke.Task;
import seedu.duke.Timetable;

import static seedu.duke.Timetable.findOverlappingFreeTime;

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
                //"\n" +
                "list: list all users \n" +
                "bye: exit the app \n" +
                "current: view current user \n" +
                "view: view timetable of current user \n" +
                "adduser: add new user \n" +
                "switch <username>: switch to user \n" +
                "addtask /on <day> /from <start time> /to <end time>: add task for current user\n" +
                "deletetask: delete task \n" +
                "changetasktiming: \n" +
                "changetasktype <f/c>: change the type of a task (flexible/confirmed)\n" +
                "compareall: compare timetables of all users \n" +
                "compare <user1> <user2>: compare timetables of specified users \n" +
                "addforall /on <day> /from <start time> /to <end time>: add task for all users\n" +
                "viewcommonevents: view common events \n");
    }

    public static void printAddForAll(Task task) {
        System.out.println("The following task is added for all users: " + task.toString());
    }

    public static void printEmptyDirectory() {
        System.out.println("Directory is empty.");

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * <p>
     * Prints the overlapping free time for each day in the format:
     * ----------------------
     * Shared Free Time on [day]
     * HH:mm - HH:mm: Overlapping Free Time
     */
    public static void printSharedTime(Timetable merged) {
        for (String day : Timetable.DAYS) {
            System.out.println("Shared free time on " + day + ":");
            findOverlappingFreeTime(merged.getWeeklyTasks().get(day), day);

        }
    }

    public static void printComparingAll() {
        System.out.println("Comparing all timetables: ");
    }
}
