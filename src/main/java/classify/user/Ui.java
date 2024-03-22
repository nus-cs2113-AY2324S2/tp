package classify.user;

import classify.student.Student;

import java.util.ArrayList;

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
    private static final String STUDENT_PHONE_MESSAGE = "Phone Number: ";
    private static final String STUDENT_GENDER_MESSAGE = "Gender: ";
    private static final String STUDENT_PAYMENT_MESSAGE = "Last Payment Date: ";
    private static final String STUDENT_REMARKS_MESSAGE = "Remarks: ";

    //@@author alalal47
    /**
     * Displays the help message to teach users how to use Classify.
     */
    public static void printHelp() {
        printAddHelpMessage();
        printEditHelpMessage();
        printViewHelpMessage();
        printDeleteHelpMessage();
        printListHelpMessage();
        printByeHelpMessage();
        printSortNameMessage();
        System.out.println("help                        Prints this help message");
    }

    //@@ author tayponghee
    private static void printSortNameMessage() {
        System.out.println("sort_name                   sorts the students by name lexicographically");
    }

    //@@ author Cryolian
    private static void printByeHelpMessage() {
        System.out.println("bye                         Exits Classify");
    }

    private static void printListHelpMessage() {
        System.out.println("list                        Displays the list of all students");
    }

    private static void printDeleteHelpMessage() {
        System.out.println("delete                      Deletes a student from the student list, expects a name" +
                ", can be used directly with a name e.g. add [name]");
    }

    private static void printViewHelpMessage() {
        System.out.println("view                        Views a students details, expects a name" +
                ", can be used directly with a name e.g. add [name]");

    }

    private static void printEditHelpMessage() {
        System.out.println("edit                        Edits a students details, expects a name" +
                ", can be used directly with a name e.g. edit [name]");
    }

    private static void printAddHelpMessage() {
        System.out.println("add                         Adds a student to the student list, " +
                "expects a name, grade and lessons attended" +
                ", can be used directly with a name e.g. add [name]");
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
    }

    /**
     * Prints out the message to end conversation with the user
     */
    public static void printEndConversation() {
        System.out.println(CLASSIFY_GOODBYE_MESSAGE);
        printDivider();
    }

    //@@author blackmirag3
    public static void printUserPrompt() {
        System.out.println(WHAT_CAN_I_DO_FOR_YOU_TODAY);
        printDivider();
    }
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

    public static void printStudentDetails(Student student) {
        System.out.println(STUDENT_PHONE_MESSAGE + student.getAttributes().getPhoneNumber());
        System.out.println(STUDENT_GENDER_MESSAGE + student.getAttributes().getGender());
        System.out.println(STUDENT_PAYMENT_MESSAGE + student.getAttributes().getLastPaymentDate());
        System.out.println(STUDENT_REMARKS_MESSAGE + student.getAttributes().getRemarks());
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

    //@@ author tayponghee
    public static void printStudentList(ArrayList<Student> students) {
        System.out.println("List of Students:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).getName());
        }
        System.out.println("Currently, there are " + students.size() + " students in the list.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
