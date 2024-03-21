package seedu.planus;
import java.util.Scanner;

public class Ui {

    public static final String MISSING_MAJOR = "Please retry with a major code.\ne.g. init CEG";
    public static final String INVALID_COMMAND = "Command entered is invalid.\nPlease enter: \"help\" " +
            "for available commands.";
    public static final String INVALID_ADD_COURSE = "To add course, please enter: add course " +
            "[course code]y/[year]t/[term]\n    e.g. add course CS1010y/1t/1";
    public static final String INVALID_ADD_GRADE = "To add grade, please enter: add grade [course code] " +
            "[letter grade]\n    e.g. add grade CS1010 A";
    public static final String INVALID_ADD = "To add course, please enter: add course [course code]y/[year]t/[term]\n"
            + "    e.g. add course CS1010y/1t/1\nTo add grade, please enter: add grade [course code] [letter grade]\n"
            + "    e.g. add grade CS1010 A";
    public static final String INVALID_REMOVE_COURSE = "To remove course, please enter: rm course [course code]\n" +
            "    e.g. rm course CS1010";
    public static final String INVALID_REMOVE_GRADE = "To remove grade, please enter: rm grade [course code]\n" +
            "    e.g. rm grade CS1010";
    public static final String INVALID_REMOVE = "To remove course, please enter: rm course [course code]\n" +
            "    e.g. rm course CS1010\nTo remove grade, please enter: rm grade [course code]\n" +
            "    e.g. rm grade CS1010";
    public static final String INVALID_CHANGE_GRADE = "To change grade, please enter: change grade " +
            "[course code] [letter grade]\n    e.g. change grade CS1010 A";
    public static final String INVALID_CHECK_YEAR_GRADE = "To check grade of a year, please enter: check y/[year]\n" +
            "    e.g. check y/1";
    public static final String INVALID_CHECK_TERM_GRADE = "To check grade of a term, please enter: " +
            "check y/[year] t/[term]\n    e.g. check y/1 t/1";
    public static final String INVALID_VIEW_YEAR_PLAN = "To view course plan of a year, please enter: " +
            "view y/[year]\n    e.g. view y/1";
    public static final String INVALID_VIEW_TERM_PLAN = "To view course plan of a term, please enter: " +
            "view y/[year] t/[term]\n    e.g. view y/1 t/1";

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

    public static void printHelp() {
        System.out.println("Listed below are all available commands:\n" +
                "    add course [course code]y/[year]t/[term]\n        e.g. add course CS1010y/1t/1\n" +
                "    add grade [course code] [letter grade]\n        e.g. add grade CS1010 A\n" +
                "    rm course [course code]\n        e.g. rm course CS1010\n" +
                "    rm grade [course code]\n        e.g. rm grade CS1010\n" +
                "    change grade [course code] [letter grade]\n        e.g. change grade CS1010 A\n" +
                "    check y/[year]\n        e.g. check y/1\n" +
                "    check y/[year] t/[term]\n        e.g. check y/1 t/1\n" +
                "    view y/[year]\n        e.g. view y/1\n" +
                "    view y/[year] t/[term]\n        e.g. view y/1 t/1\n" +
                "    help\n" +
                "    bye\n\n");
    }

    public static void printExit() {
        System.out.println("Goodbye!");
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printCourseNotFound(){
        System.out.println("Course does not exist");
    }

    public static void printCourseAdded() {
        System.out.println("Course added to your schedule");
    }

    public static void printFileNotFound(String filePathName) {
        System.out.println("File at " + filePathName + " is not found. Trying to create one.");
    }

    public static void printCorruptedData(int lineNumber, String filePathName) {
        System.out.println("Data corrupted at line " + lineNumber + " of file at " + filePathName);
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

    public static void printInvalidAddGrade() {
        System.out.println("To add grade, please enter: add grade [course code] [letter grade]\n    e.g. add grade CS1010 A");
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

    public static void printInvalidInputGrade() {
        System.out.println("The letter grade entered is invalid!\nPlease enter: A+/A/A-/B+/B/B-/C+/C/D+/D/F");
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


