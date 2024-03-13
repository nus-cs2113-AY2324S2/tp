package seedu.duke;

import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";
    public Ui() {
        this.in = new Scanner(System.in);
    }
    public String getUserCommand() {
        String currentLine =  in.nextLine();

        while(shouldIgnore(currentLine)) {
            currentLine = in.nextLine();
        }
        return currentLine;
    }
    private boolean shouldIgnore(String currentLine) {
        return currentLine.isBlank() || currentLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }
    public static void printGreeting() {
        printHyphens();
        System.out.println("Hello! This is your CEG Future Academic Planner!");
        System.out.println("What can I do for you?");
    }
    public static void printHyphens() {
        System.out.println("__________________________________________________");
    }
    public static void printExit() {
        System.out.println("Bye. Enjoy your studies!");
    }

}
