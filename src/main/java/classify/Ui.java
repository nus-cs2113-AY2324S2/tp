package classify;

public class Ui {
    //@@author ParthGandhiNUS
    private static final String DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String WELCOME_TO_CLASSIFY = "Welcome to Classify!";
    private static final String WHAT_CAN_I_DO_FOR_YOU_TODAY = "What can I do for you today?";
    private static final String CLASSIFY_GOODBYE_MESSAGE = "Hope you've had a productive day. See you again! Goodbye!";
    private static final String WRONG_INPUT_MESSAGE = "No such command, type \"help\" to view all commands";
    //@@author blackmirag3
    private static final String STUDENT_DETAILS_PROMPT = "Enter student details: ";
    private static final String STUDENT_NAME_PROMPT = "Enter student name: ";
    private static final String STUDENT_DETAILS_MESSAGE = "Student details: ";
    private static final String STUDENT_NAME_MESSAGE = "Name: ";
    private static final String CLASSES_ATTENDED_PROMPT = "Enter Classes Attended (blank to skip): ";
    private static final String CLASSES_ATTENDED_MESSAGE = "Classes Attended: ";
    private static final String STUDENT_NOT_FOUND_MESSAGE = "Student not found!";
    private static final String STUDENT_ADDED_MESSAGE = "Student added successfully!";
    private static final String STUDENT_DELETED_MESSAGE = "Student removed successfully!";
    private static final String SUBJECT_MESSAGE = "Subject: ";
    private static final String STUDENT_GRADES_PROMPT = "Current marks out of 100 (blank to skip) : ";
    private static final String STUDENT_GRADES_MESSAGE = "Current marks out of 100: ";

    //@author alalal47
    /**
     * Displays the help message to teach users how to use Classify.
     */
    public static void printHelp() {
        System.out.println("add                         Adds a student to the student list, " +
                "expects a name, grade and lessons attended" +
                ", can be used directly with a name e.g. add [name]");
        System.out.println("edit                        Edits a students details, expects a name" +
                " , can be used directly with a name e.g. edit [name]");
        System.out.println("view                        Views a students details, expects a name" +
                ", can be used directly with a name e.g. add [name]");
        System.out.println("delete                      Deletes a student from the student list, expects a name" +
                ", can be used directly with a name e.g. add [name]");
        System.out.println("list                        Displays the list of all students");
        System.out.println("bye                         Exits Classify");
        System.out.println("help                        Prints this help message");
    }

    //@@author ParthGandhiNUS
    /**
     * Prints a dividing line between statements for added clarity
     */
    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints out the welcome message to introduce the user to Classify
     */
    public static void printWelcomeMessage() {
        printDivider();
        System.out.println(WELCOME_TO_CLASSIFY);
        System.out.println(WHAT_CAN_I_DO_FOR_YOU_TODAY);
        printDivider();
    }

    /**
     * Prints out the message to end conversation with the user
     */
    public static void printEndConversation() {
        System.out.println(CLASSIFY_GOODBYE_MESSAGE);
        printDivider();
    }

    //@@author blackmirag3
    public static void printWrongInput() {
        System.out.println(WRONG_INPUT_MESSAGE);
        printDivider();
    }

    public static void printStudentDetailsPrompt() {
        System.out.println(STUDENT_DETAILS_PROMPT);
    }

    public static void printStudentNamePrompt() {
        System.out.println(STUDENT_NAME_PROMPT);
    }

    public static void printStudentDetails() {
        System.out.println(STUDENT_DETAILS_MESSAGE);
    }

    public static void printStudentName(String name) {
        System.out.println(STUDENT_NAME_MESSAGE + name);
    }

    public static void printClassesAttendedPrompt() {
        System.out.println(CLASSES_ATTENDED_PROMPT);
    }

    public static void printClassesAttended(int attendance) {
        System.out.println(CLASSES_ATTENDED_MESSAGE + attendance);
    }

    public static void printStudentNotFound() {
        System.out.println(STUDENT_NOT_FOUND_MESSAGE);
    }

    public static void printStudentAdded() {
        System.out.println(STUDENT_ADDED_MESSAGE);
    }

    public static void printStudentDeleted() {
        System.out.println(STUDENT_DELETED_MESSAGE);
    }

    public static void printSubjectName(String name) {
        System.out.println(SUBJECT_MESSAGE + name);
    }

    public static void printStudentGrades(Double grades) {
        System.out.println(STUDENT_GRADES_MESSAGE + grades);
    }

    public static void printStudentGradesPrompt() {
        System.out.println(STUDENT_GRADES_PROMPT);
    }
}
