package classify.user;

import classify.student.Student;

import java.util.ArrayList;

//@@author Cryolian
public class Ui {
    public static final String TOTAL_CLASSES_ATTENDED_ACROSS_ALL_SUBJECTS = "Total classes " +
            "attended across all subjects: ";
    private static final String EMPTY_SUBJECT_ERROR = "No subjects and grades found for this student.";
    private static final String NULL_ATTRIBUTE_ERROR = "No attributes found for this student.";
    private static final String EDIT_PROMPT = "How would you like to update a student's subject? (enter blank to exit)";
    private static final String EDIT_TYPES_PROMPT = "Add, Edit or Delete: ";
    private static final String EMPTY_LIST_ERROR = "Currently no students in list.";
    private static final String VALID_NUMBER_ERROR = "A valid number was not entered. Please input a valid number. ";
    private static final String PHONE_NUMBER_PROMPT = "Please input a valid phone number: ";
    private static final String STUDENT_PHONE_MESSAGE = "Phone Number: ";
    private static final String STUDENT_GENDER_MESSAGE = "Gender: ";
    private static final String STUDENT_PAYMENT_MESSAGE = "Last Payment Date: ";
    private static final String STUDENT_REMARKS_MESSAGE = "Remarks: ";
    private static final String SAME_NAME_ERROR = "Student with the same name already exists. " + 
            "Please enter a different name.";
    private static final String EMPTY_NAME_ERROR = "Student name cannot be empty. Please enter a valid name.";
    private static final String GENDER_PROMPT = "Please input the student's gender: ";
    private static final String PAYMENT_PROMPT = "Please input their last payment date in the format of YYYY-MM-DD. " +
            "Enter blank to input today's date.";
    private static final String REMARKS_PROMPT = "Please input any remarks: ";
    private static final String INVALID_DATE_ERROR = "Please input a valid date in the format YYYY-MM-DD"
            + ", or blank for today.";
    private static final String INVALID_DATE_RANGE = "Please input a date with a reasonable value. \n" +
            "(eg. from 2010 until today.)";
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
    //@@author alalal47
    private static final String DELETE_UNDONE_MESSAGE = "Last delete undone!";
    private static final String STUDENT_RESTORED_MESSAGE = "Student has been restored!";
    private static final String NO_RECENT_DELETES = "No recent deletes found!";

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
        System.out.println(STUDENT_PHONE_MESSAGE + student.getAttributes().getPhoneNumber() + "\n");
        System.out.println(STUDENT_GENDER_MESSAGE + student.getAttributes().getGender() + "\n");
        System.out.println(STUDENT_PAYMENT_MESSAGE + student.getAttributes().getLastPaymentDate() + "\n");
        System.out.println(STUDENT_REMARKS_MESSAGE + student.getAttributes().getRemarks() + "\n");
    }

    public static void printStudentName(String name) {
        System.out.println(STUDENT_NAME_MESSAGE + name + "\n");
    }

    //@@author Cryolian
    public static void printStudentName(Student student) {
        System.out.println(STUDENT_NAME_MESSAGE + student.getName() + "\n");
    }

    //@@author blackmirag3
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

    //@@author Cryolian
    public static void printSameNameError() {
        System.out.println(SAME_NAME_ERROR);
    }

    public static void promptForRemarks() {
        System.out.println(REMARKS_PROMPT);
    }

    public static void promptForLastPaymentDate() {
        System.out.println(PAYMENT_PROMPT);
    }

    public static void promptForGender() {
        System.out.println(GENDER_PROMPT);
    }

    public static void printEmptyNameMessage() {
        System.out.println(EMPTY_NAME_ERROR);
    }

    public static void printPhoneNumberPrompt() {
        System.out.println(PHONE_NUMBER_PROMPT);
    }
    
    public static void printValidNumberError() {
        System.out.println(VALID_NUMBER_ERROR);
    }

    public static void printEmptyListError() {
        System.out.println(EMPTY_LIST_ERROR);
    }

    public static void printEditPrompt() {
        System.out.println(EDIT_PROMPT);
        System.out.println(EDIT_TYPES_PROMPT);
    }

    public static void printEmptySubjectError() {
        System.out.println(EMPTY_SUBJECT_ERROR);
    }

    public static void printNullAttributeError() {
        System.out.println(NULL_ATTRIBUTE_ERROR);
    }

    public static void printInvalidDateFormatError() {
        System.out.println(INVALID_DATE_ERROR);
    }

    public static void printInvalidDateRangeError() {
        System.out.println(INVALID_DATE_RANGE);
        printDivider();
    }

    //@@author alalal47
    public static void printDeleteUndone() {
        System.out.println(DELETE_UNDONE_MESSAGE);
    }

    public static void printRestoreMessage() {
        System.out.println(STUDENT_RESTORED_MESSAGE);
    }

    public static void printNoDeleteFound() {
        System.out.println(NO_RECENT_DELETES);
    }

    //@@ author tayponghee
    public static void printTotalClassesAttended(int classes) {
        System.out.println(TOTAL_CLASSES_ATTENDED_ACROSS_ALL_SUBJECTS + classes);
    }

    public static void println (String text) {
        System.out.println(text);
    }
}
