
package classify.student;

import classify.user.Ui;
import classify.user.InputParsing;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;

import static classify.user.InputParsing.LOGGER;

public class AddStudent {
    private static final String NOTEMPTY = "THIS STRING IS NOT EMPTY";
    private static final String YES = "yes";
    private static final String NO = "no";
    private static final String SUBJECT_ENTER_NOTHING_TO_SKIP = "Subject (enter nothing to skip): ";
    private static final String STUDENT_ADDED_SUCCESSFULLY = "Student added successfully.";
    private static final String NUMBER_IS_OUTSIDE_THE_ACCEPTABLE_RANGE = "Number is outside the acceptable range.";
    private static final String STUDENT_WITH_THE_SAME_NAME_ALREADY_EXISTS =
            "Student with the same name already exists.";

    /**
     * Adds a new student to the list of students.
     * Does not allow for students of the same name to be added.
     * Checks if attributes added are in the correct format.
     * Allows for multiple subjects to be added, along with their grades and classes
     * attended.
     *
     * @param masterStudentList The list of all students.
     * @param in                The scanner object to read user input.
     * @param studentName       The name of the student if the user had entered it
     *                          before being prompted
     */
    public static void addStudent(ArrayList<Student> masterStudentList, Scanner in, String studentName) {
        String name;

        name = checkForEmptyName(masterStudentList, in, studentName);

        if (InputParsing.findStudentByName(masterStudentList, name) != null) {
            assert InputParsing.findStudentByName(masterStudentList, name) != null;
            LOGGER.log(Level.WARNING, STUDENT_WITH_THE_SAME_NAME_ALREADY_EXISTS);

            Ui.printSameNameError();
            Ui.printDivider();
            return;
        }

        Student student = new Student(name);
        addSubject(in, student.getAttributes());

        // @@author Cryolian
        int number = InputParsing.promptForPhoneNumber(in);

        while (number < 0) {
            number = InputParsing.promptForPhoneNumber(in);
        }
        //@@ author ParthGandhiNUS
        assert number > 0 && number < 100000000 : NUMBER_IS_OUTSIDE_THE_ACCEPTABLE_RANGE;

        //@@author Cryolian
        student.getAttributes().setPhoneNumber(number);

        Ui.promptForGender();
        student.getAttributes().setGender(InputParsing.readInString(in));

        Ui.promptForLastPaymentDate();
        student.getAttributes().setLastPaymentDate(InputParsing.readInDate(in));

        Ui.promptForRemarks();
        student.getAttributes().setRemarks(InputParsing.readInString(in));

        //@@author tayponghee
        masterStudentList.add(student);
        LOGGER.log(Level.INFO, STUDENT_ADDED_SUCCESSFULLY);
        Ui.printStudentAdded();
        Ui.printDivider();
    }

    /**
     * Prompts the user to enter a non-empty name for a student and checks if it
     * already exists in the list.
     * If the name is empty or already exists, prompts the user again until valid
     * input is provided.
     *
     * @param masterStudentList The list of all students.
     * @param in                The scanner object to read user input.
     * @param studentName       The name of the student if the user had entered it
     *                          before being prompted
     * @return The valid non-empty name entered by the user.
     */
    private static String checkForEmptyName(ArrayList<Student> masterStudentList, Scanner in, String studentName) {
        String name;
        while (true) {

            //@@author alalal47
            if (studentName == null) {
                Ui.printStudentNamePrompt();
                name = in.nextLine().trim();
            } else {
                name = studentName.trim();
                studentName = NOTEMPTY;
            }

            //@@author tayponghee
            assert studentName != null;

            if (name.isEmpty()) {
                Ui.printEmptyNameMessage();
                Ui.printDivider();
            } else {
                break;
            }
        }
        return name;
    }

    /**
     * Prompts the user to enter attributes for a student, including subject, grade,
     * and classes attended.
     * Continues to prompt the user until user enters blank to exit.
     *
     * @param in         The scanner object to read user input.
     * @param attributes The StudentAttributes object to store the attributes of the
     *                   student.
     */
    public static void addSubject(Scanner in, StudentAttributes attributes) {
        while (true) {
            //@@ author blackmirag3
            System.out.print(SUBJECT_ENTER_NOTHING_TO_SKIP);
            String subject = in.nextLine().trim();

            if (subject.isBlank()) {
                Ui.printNoSubjectsAdded();
                break;

            } else if (attributes.findSubject(subject) != null) {
                // rejects subject if existing subject of same name exists in students'
                // attributes
                Ui.printSubjectAlreadyExists();
                break;

            } else if (checkForValidSubjectResponse(in, attributes, subject)) {
                //@@author tayponghee
                return;
            }
        }
    }

    /**
     * Checks for a valid response from the user when prompted to add another subject and grade.
     *
     * @param in         The scanner object to read user input.
     * @param attributes The attributes of the student.
     * @param subject    The subject to be added.
     * @return True if the user chooses not to add another subject and grade, false otherwise.
     */
    private static boolean checkForValidSubjectResponse(Scanner in, StudentAttributes attributes, String subject) {
        double grade = Math.max(InputParsing.promptForGrade(in), 0);
        int classesAttended = promptForClassesAttended(in);

        SubjectGrade subjectGrade = new SubjectGrade(subject, grade, classesAttended);
        attributes.addSubjectGrade(subjectGrade);

        while (true) {
            Ui.printAddSubject();
            String response = in.nextLine().trim().toLowerCase();

            if (response.equals(YES)) {
                break;

            } else if (response.equals(NO)) {
                return true;

            } else {
                Ui.printInvalidResponse();
            }

        }
        return false;
    }

    /**
     * Checks the format of the input for the number of classes attended.
     * Prompts the user to enter a valid integer until one is provided.
     *
     * @param in The scanner object to read user input.
     * @return The valid number of classes attended.
     */
    public static int promptForClassesAttended(Scanner in) {
        while (true) {
            Ui.printClassesAttendedPrompt();
            String classesAttendedInput = in.nextLine();

            if (classesAttendedInput.isBlank()) {
                return -1;
            }

            // @@author ParthGandhiNUS
            int classesAttended;
            try {
                classesAttended = Integer.parseInt(classesAttendedInput);
            } catch (NumberFormatException e) {
                //@@author tayponghee
                Ui.printWrongNumberFormat();
                //@@author ParthGandhiNus
                Ui.printDivider();
                classesAttended = promptForClassesAttended(in);
            }

            if (InputParsing.isValidClassesAttended(classesAttended)) {
                return classesAttended;
            }
        }
    }
}
