package classify.user;

import classify.datacommands.DataHandler;

import classify.student.AddStudent;
import classify.student.Student;
import classify.student.StudentAttributes;
import classify.student.StudentList;
import classify.student.StudentSorter;
import classify.student.SubjectGrade;
import classify.student.ViewStudent;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputParsing {
    public static final Logger LOGGER = Logger.getLogger(InputParsing.class.getName());
    private static final String EARLIER_POSSIBLE_DATE = "1970-01-01";
    private static final String DEFAULT_STRING_VALUE = "Unknown";
    private static final int LOWER_LIMIT_PHONE_NUMBER = 8;
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String ADD = "add";
    private static final String VIEW = "view";
    private static final String DELETE = "delete";
    private static final String UNDO = "undo";
    private static final String RESTORE = "restore";
    private static final String EDIT = "edit";
    private static final String HELP = "help";
    private static final String SORT = "sort";
    private static final String VIEW_SUBJECT = "view_subject";
    private static final String ENTER_THE_SUBJECT_NAME_TYPE_EXIT_TO_GO_BACK =
            "Enter the subject name (type 'exit' to go back):";
    private static final String EXIT = "exit";
    private static final String EXITED_THE_COMMAND = "Exited the command.";
    private static final String LIST_SORTED = "Sort complete!";
    private static final String SORT_BY_CHOOSE_INDEX = "Sort by: (Choose index)";
    private static final String NAME_A_TO_Z = "1. Name (A to Z)";
    private static final String TOTAL_NUMBER_OF_CLASSES_ATTENDED = "2. Total number of classes attended:";
    private static final String LAST_PAID_DATE = "3. Date of last fee payment: ";

    public static void parseUserCommand(String[] userCommand, ArrayList<Student> masterStudentList,
                                        ArrayList<Student> recentlyDeletedList, Scanner in) {
        // @@author blackmirag3
        if (masterStudentList == null) {
            System.out.println("Student list is null.");
        }
        // @@author tayponghee
        switch (userCommand[0]) {
        case ADD:
            AddStudent.addStudent(masterStudentList, in, userCommand[1]);
            // @@author ParthGandhiNUS
            assert masterStudentList != null;
            DataHandler.writeStudentInfo(masterStudentList);
            // @@author tayponghee
            break;

        case VIEW:
            ViewStudent.viewStudent(masterStudentList, in, userCommand[1]);
            Ui.printDivider();
            break;

        //@@author alalal47
        case DELETE:
            DeleteCommands.deleteStudent(masterStudentList, recentlyDeletedList, in, userCommand[1]);
            // @@author ParthGandhiNUS
            DataHandler.writeStudentInfo(masterStudentList);
            //@@author alalal47
            break;

        case RESTORE:
            DeleteCommands.restoreStudent(masterStudentList, recentlyDeletedList, in, userCommand[1]);
            break;

        case UNDO:
            DeleteCommands.undoDelete(masterStudentList, recentlyDeletedList);
            break;

        case HELP:
            Ui.printHelp();
            Ui.printDivider();
            break;

        // @@author ParthGandhiNUS
        case BYE:
            Ui.printEndConversation();
            DataHandler.writeStudentInfo(masterStudentList);
            break;

        case EDIT:
            editStudent(masterStudentList, in, userCommand[1]);
            break;

        case LIST:
            listStudents(masterStudentList);
            break;

        //@@ author tayponghee
        case SORT:
            sortStudents(masterStudentList, in, userCommand[1]);
            break;

        case VIEW_SUBJECT:
            handleViewSubjectCommand(masterStudentList, in, userCommand[1]);
            break;

        default:
            Ui.printWrongInput();
            break;
        }
    }

    /**
     * Sorts the list of students based on user input.
     *
     * @param masterStudentList The list of students to be sorted.
     * @param in                The Scanner object to read user input.
     */
    private static void sortStudents(ArrayList<Student> masterStudentList, Scanner in, String sortType) {
        String input;
        while (true) {
            //@@author alalal47
            if (sortType == null) {
                Ui.println(SORT_BY_CHOOSE_INDEX);
                Ui.println(NAME_A_TO_Z);
                Ui.println(TOTAL_NUMBER_OF_CLASSES_ATTENDED);
                Ui.println(LAST_PAID_DATE);
                input = in.nextLine().trim();
            } else {
                input = sortType.trim().toLowerCase();
            }
            //@@author tayponghee
            //input = in.nextLine().trim();

            if (input.equalsIgnoreCase(EXIT)) {
                System.out.println(EXITED_THE_COMMAND);
                Ui.printDivider();
                return;
            }

            if (StudentSorter.isValidChoice(input)) {
                StudentSorter.sortByChoice(masterStudentList, input, in);
                Ui.println(LIST_SORTED);
                break;
            } else {
                sortType = null;
                Ui.println(StudentSorter.INVALID_CHOICE);
            }
        }
    }

    /**
     * Lets the user check view a list of students with that corresponding subject.
     * If the user types view_subject [subject], it will only generate the list of students with that subject,
     * then exit.
     * If the user types view_subject, the user can continuously view all students that
     * have that subject, until they exit the command.
     *
     * @param masterStudentList The list of all students.
     * @param in                The user's input.
     */
    private static void handleViewSubjectCommand(ArrayList<Student> masterStudentList, Scanner in, String subject) {
        if (subject != null && !subject.isEmpty()) {
            viewStudentsBySubject(masterStudentList, subject);
        } else {
            findStudentsWithSubject(masterStudentList, in);
        }
    }

    /**
     * Finds students with the specified subject and displays them to the user.
     * Continuously prompts the user to enter a subject name until they choose to exit.
     *
     * @param masterStudentList The list of all students.
     * @param in                The scanner object to read user input.
     */
    private static void findStudentsWithSubject(ArrayList<Student> masterStudentList, Scanner in) {
        while (true) {
            System.out.println(ENTER_THE_SUBJECT_NAME_TYPE_EXIT_TO_GO_BACK);
            String input = in.nextLine().trim();

            if (input.equalsIgnoreCase(EXIT)) {
                System.out.println(EXITED_THE_COMMAND);
                Ui.printDivider();
                return;
            }

            if (!input.isEmpty()) {
                viewStudentsBySubject(masterStudentList, input);
                return;
            } else {
                System.out.println("Please enter a valid subject name.");
            }
        }
    }

    /**
     * Views all students who have the specified subject.
     *
     * @param masterStudentList The list of all students.
     * @param subject           The subject to search for among students.
     */
    private static void viewStudentsBySubject(ArrayList<Student> masterStudentList, String subject) {
        boolean found = false;
        System.out.println("Students with the subject \"" + subject + "\":");

        for (Student student : masterStudentList) {
            if (student.hasSubject(subject)) {
                System.out.println("- " + student.getName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students found with the subject: " + subject);
        }

        Ui.printDivider();
    }

    // @@author blackmirag3
    private static void listStudents(ArrayList<Student> list) {
        StudentList.printCurrentArrayList(list);
        // @@author ParthGandhiNUS
        StudentList.printCurrentArrayMessage(list);
        Ui.printDivider();
    }

    private static void editStudent(ArrayList<Student> list, Scanner in, String name) {
        if (list.isEmpty()) {
            Ui.printEmptyListError();
            return;
        }
        
        Student student = null;
        
        if (name != null) {
            student = findStudentByName(list, name);
            if (student == null) {
                Ui.printStudentNotFound();
            }
        }
        
        while (student == null) {

            System.out.println("Name of student to edit (enter blank to exit):");
            name = in.nextLine().trim();

            if (name.isBlank()) {
                System.out.println("Exiting edit.");
                return;
            }

            student = findStudentByName(list, name);

            if (student != null) {
                break;
            } else {
                Ui.printStudentNotFound();
            }
        }
        
        editStudentAttributes(in, student);
    }

    private static void editStudentAttributes(Scanner in, Student student) {
        StudentAttributes attributes = student.getAttributes();
        ViewStudent.showAttributes(attributes);

        while (true) {
            Ui.printEditPrompt();
            String command = in.nextLine().trim();
            if (command.isBlank()) {
                System.out.println("Exiting edit");
                Ui.printDivider();
                return;
            }

            switch (command) {

            case ADD:
                AddStudent.addSubject(in, attributes);
                student.setAttributes(attributes);
                break;

            case EDIT:
                editAttribute(in, attributes);
                break;

            case DELETE:
                deleteAttribute(in, attributes);
                break;

            default:
                break;
            }

        }
    }

    // @@author blackmirag3
    /**
     * Prompts the user to edit subject, grade, and classes attended for student.
     * Continues to prompt the user until user enters blank to exit.
     *
     * @param in         The scanner object to read user input.
     * @param attributes The StudentAttributes object to store the attributes of the
     *                   student.
     */
    private static void editAttribute(Scanner in, StudentAttributes attributes) {

        while (true) {

            System.out.print("Subject to edit (enter nothing to exit): ");
            String subjectToFind = in.nextLine().trim();

            if (subjectToFind.isBlank()) {
                System.out.println("No subject edited.");
                return;
            }

            SubjectGrade currentSubject = attributes.findSubject(subjectToFind);

            if (currentSubject != null) {
                System.out.print("New subject name (enter nothing to skip): ");
                String newName = in.nextLine().trim();

                if (!newName.isBlank()) {
                    currentSubject.setSubject(newName);
                }

                double newGrade = promptForGrade(in);
                
                if (newGrade != -1) {
                    currentSubject.setGrade(newGrade);
                }
                
                int newClassesAttended = AddStudent.promptForClassesAttended(in);
                currentSubject.setClassesAttended(newClassesAttended);
                System.out.println("Subject updated.");

            } else {
                System.out.println("Subject not found.");
            }

        }
    }

    private static void deleteAttribute(Scanner in, StudentAttributes attributes) {
        while (true) {
            
            System.out.println("Subject to delete (enter blank to exit)");
            String subjectToDelete = in.nextLine().trim();
            
            if (subjectToDelete.isBlank()) {
                System.out.println("no subject deleted");
                return;
            }
            
            if (attributes.findSubject(subjectToDelete) != null) {
                attributes.deleteSubject(subjectToDelete);
                System.out.println("Subject deleted");
            } else {
                System.out.println("subject not found");
            }
        }
    }

    /**
     * Prompts for grade from user input and checks format
     * Prompts the user to enter a valid double within the range [0, 100] until one
     * is provided.
     *
     * @param in The scanner object to read user input.
     * @return The valid grade.
     */
    public static double promptForGrade(Scanner in) {
        while (true) {
            Ui.printStudentGradesPrompt();
            String gradeInput = in.nextLine();
            
            if (gradeInput.isBlank()) {
                return -1;
            }
            
            // @@author ParthGandhiNUS
            double grade;
            try {
                grade = Double.parseDouble(gradeInput);
            } catch (NumberFormatException e) {
                System.out.println("Wrong number format! Please try again! e.g. 75 ");
                Ui.printDivider();
                grade = promptForGrade(in);
            }
            
            if (isValidGrade(grade)) {
                return grade;
            }
        }
    }

    /**
     * Finds a student in the list by their name.
     *
     * @param masterStudentList The list of all students.
     * @param name              The name of the student to search for.
     * @return The student object if found, null otherwise.
     */
    public static Student findStudentByName(ArrayList<Student> masterStudentList, String name) {
        for (Student student : masterStudentList) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }

    // @@author blackmirag3
    public static boolean isValidClassesAttended(int classesAttended) {
        try {
            if (classesAttended < 0) {
                System.out.println("Classes attended must be 0 or more.");
                Ui.printDivider();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Invalid input for classes attended: " + classesAttended, e);
            System.out.println("Invalid input for classes attended. Please enter a valid whole number.");
            Ui.printDivider();
            return false;
        }
    }

    private static boolean isValidGrade(double grade) {
        try {
            if (grade < 0 || grade > 100) {
                System.out.println("Grade must be between 0 and 100. Please enter a valid number.");
                Ui.printDivider();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Invalid input for grade: " + grade, e);
            Ui.printValidNumberError();
            Ui.printDivider();
            return false;
        }
    }

    //@@author Cryolian
    /**
     * Creates a looping prompt asking for a phone number.
     * Only exits the loop when either an exception is thrown,
     * or when the number is either 8 or 10 digits to account for
     * the length of a Singaporean number, with or without
     * the country code.
     * 
     * @param in    The scanner class to read inputs from.
     * @return      -1 if an exception was thrown. An 8
     *              or 10-digit number if not.
     */
    public static int promptForPhoneNumber(Scanner in) {

        int number = 0;
        
        try {
            
            do  {
                Ui.printPhoneNumberPrompt();
                number = readInPhoneNumber(in);
            } while (!checkNumberValidity(number));
            
            LOGGER.log(Level.INFO, "Storing number: " + number);
            return number;

        } catch (NumberFormatException e) {
            Ui.printValidNumberError();
        }

        return -1;
    }

    private static int readInPhoneNumber(Scanner in) throws NumberFormatException{

        String input = in.nextLine().trim();
        
        return Integer.parseInt(input);
    }

    private static boolean checkNumberValidity(int number) {
        return number > 0 &&
                String.valueOf(number).length() == LOWER_LIMIT_PHONE_NUMBER;
    }

    /**
     * A prompting input to scan in a string from the user input.
     * 
     * @param in    The scanner class to scan inputs from.
     * @return      "Unknown" if blank was inputted, or the
     *              trimmed string inputted by the user.
     */
    public static String readInString(Scanner in) {
        
        String string = in.nextLine();
        if (string.isBlank()) {
            return DEFAULT_STRING_VALUE;
        } 
        return string.trim();
        
    }

    public static LocalDate readInDate(Scanner in) {

        String userInput;
        LocalDate paymentDate;

        do {

            userInput = in.nextLine();
            paymentDate = parseDateFromString(userInput);

        } while(!isDateValid(paymentDate));

        return paymentDate;
    }

    protected static LocalDate parseDateFromString(String string) {

        if (string.isBlank()) {
            LOGGER.log(Level.INFO, "Storing today as the last payment date." + '\n');
            return LocalDate.now();
        }

        LocalDate paymentDate;

        try {

            paymentDate = LocalDate.parse(string);

        } catch (DateTimeParseException e) {
            return invalidDatePath();
        }

        return paymentDate;
    }

    private static LocalDate invalidDatePath() {
        LOGGER.log(Level.WARNING, "Invalid date format entered." + '\n');
        Ui.printInvalidDateFormatError();
        return LocalDate.now().plusDays(2);
    }

    private static boolean isDateValid(LocalDate paymentDate) {

        if (paymentDate.isAfter(LocalDate.now().plusDays(1)) || 
                paymentDate.isBefore(LocalDate.parse(EARLIER_POSSIBLE_DATE))) {
            
            Ui.printInvalidDateRangeError();
            return false;
        }

        return true;
                
    }

}
