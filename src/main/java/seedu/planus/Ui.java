package seedu.planus;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static void printLogo() {
        System.out.println(" ________  ___       ________  ________   ___  ___  ________      \n" +
                "|\\   __  \\|\\  \\     |\\   __  \\|\\   ___  \\|\\  \\|\\  \\|\\   ____\\     \n" +
                "\\ \\  \\|\\  \\ \\  \\    \\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\ \\  \\___|_    \n" +
                " \\ \\   ____\\ \\  \\    \\ \\   __  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\ \\_____  \\   \n" +
                "  \\ \\  \\___|\\ \\  \\____\\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\|____|\\  \\  \n" +
                "   \\ \\__\\    \\ \\_______\\ \\__\\ \\__\\ \\__\\\\ \\__\\ \\_______\\____\\_\\  \\ \n" +
                "    \\|__|     \\|_______|\\|__|\\|__|\\|__| \\|__|\\|_______|\\_________\\\n" +
                "                                                      \\|_________|\n" +
                "                                                                  \n" +
                "                                                                  ");
    }

    public static void printExit() {
        System.out.println("Goodbye!");
    }

    public static void printCourseNotFound(){
        System.out.println("Course does not exist");
    }

    public static void printCourseAdded() {
        System.out.println("Course added to your schedule");
    }

    public static void printFileCreated() {
        System.out.println("File creation successful.");
    }

    public static void printFileFailedToCreate() {
        System.out.println("File creation failed.");
    }

    public static void printFailedLoadingFile() {
        System.out.println("Loading file failed.");
    }

    public static void printWrongTerm() {
        System.out.println("Term is not from 1 to 4");
    }

    public static void printWrongYear() {
        System.out.println("Year is not from 1 to 6");
    }

    public static void printFailedToAddGrade() {
        System.out.println("The course does not exist in the current timetable! You may want to add the course first.");
    }

    public static void printSuccessToAddGrade(String courseCode) {
        System.out.println("The grade has been added to " + courseCode);
    }

    public static void printFailedToRemoveGrade() {
        System.out.println("The course does not exist in the current timetable!");
    }

    public static void printSuccessToRemoveGrade(String courseCode) {
        System.out.println("The grade has been removed for " + courseCode);
    }

    public static void printFailedToWrite() {
        System.out.println("Failed writing timetable to file");
    }

    public static String getUserCommand() {
        System.out.print("Command: ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}


