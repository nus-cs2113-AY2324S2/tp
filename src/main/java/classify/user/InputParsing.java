package classify.user;

import classify.datacommands.DataHandler;
import classify.student.Student;
import classify.student.StudentAttributes;

import classify.student.StudentComparators;
import classify.student.StudentList;
import classify.student.SubjectGrade;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Scanner;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InputParsing {
    private static final String EARLIER_POSSIBLE_DATE = "1970-01-01";
    private static final String DEFAULT_STRING_VALUE = "Unknown";
    private static final int LOWER_LIMIT_PHONE_NUMBER = 8;
    private static final String NOTEMPTY = "THIS STRING IS NOT EMPTY";
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String ADD = "add";
    private static final String VIEW = "view";
    private static final String DELETE = "delete";
    private static final String UNDO = "undo";
    private static final String RESTORE = "restore";
    private static final String EDIT = "edit";
    private static final String HELP = "help";
    private static final String SORT_NAME = "sort_name";
    private static final String VIEW_SUBJECT = "view_subject";
    private static final String NO_STUDENTS_IN_THE_LIST_CAN_T_SORT_BY_NAME =
            "No students in the list, can't sort by name!";
    //private static final String STUDENTS_WITH_THE_SUBJECT = "Students with the subject \"";
    //private static final String NO_STUDENTS_FOUND_WITH_THE_SUBJECT = "No students found with the subject: ";
    private static final String ENTER_THE_SUBJECT_NAME_TYPE_EXIT_TO_GO_BACK =
            "Enter the subject name (type 'exit' to go back):";
    private static final String EXIT = "exit";
    private static final String EXITED_THE_COMMAND = "Exited the command.";
    private static final Logger logger = Logger.getLogger(InputParsing.class.getName());

    public static void parseUserCommand(String[] userCommand, ArrayList<Student> masterStudentList,
                                        ArrayList<Student> recentlyDeletedList, Scanner in) {
        // @@author blackmirag3
        if (masterStudentList == null) {
            System.out.println("Student list is null.");
        }
        // @@author tayponghee
        switch (userCommand[0]) {
        case ADD:
            addStudent(masterStudentList, in, userCommand[1]);
            // @@author ParthGandhiNUS
            DataHandler.writeStudentInfo(masterStudentList);
            // @@author tayponghee
            break;

        case VIEW:
            viewStudent(masterStudentList, in, userCommand[1]);
            Ui.printDivider();
            break;

        //@@author alalal47
        case DELETE:
            deleteStudent(masterStudentList, recentlyDeletedList, in, userCommand[1]);
            // @@author ParthGandhiNUS
            DataHandler.writeStudentInfo(masterStudentList);
            //@@author alalal47
            break;

        case RESTORE:
            restoreStudent(masterStudentList, recentlyDeletedList, in, userCommand[1]);
            break;

        case UNDO:
            undoDelete(masterStudentList, recentlyDeletedList);
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
        case SORT_NAME:
            listStudentsByName(masterStudentList);
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

    /**
     * Lists students in the provided list by name in ascending order.
     * If the list is empty, it prints a message indicating that there are no students in the list.
     *
     * @param students The list of students to be listed by name.
     */
    public static void listStudentsByName(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println(NO_STUDENTS_IN_THE_LIST_CAN_T_SORT_BY_NAME);
            return;
        }
        Collections.sort(students, StudentComparators.nameComparator);
        listStudents(students);
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
        showAttributes(attributes);

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
                addSubject(in, attributes);
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

    //@@author alalal47
    /**
     * Removes a student from the list.
     * Adds the removed student to a recently deleted list, where the student's information can be recovered
     *
     * @param masterStudentList   The list of all students
     * @param recentlyDeletedList The list of recently deleted students
     * @param in                  The scanner object to read user input
     * @param studentName         The name of the student if the user had entered it
     *                            before being prompted
     */
    private static void deleteStudent(ArrayList<Student> masterStudentList, ArrayList<Student> recentlyDeletedList,
                                      Scanner in, String studentName) {
        String name;
        if (studentName == null) {
            Ui.printStudentNamePrompt();
            name = in.nextLine().trim();
        } else {
            name = studentName;
        }
        
        Student foundStudent = findStudentByName(masterStudentList, name);
        
        if (foundStudent != null) {
            Ui.printStudentDeleted();
        } else {
            Ui.printStudentNotFound();
        }
        
        Ui.printDivider();
        recentlyDeletedList.add(foundStudent);
        masterStudentList.remove(foundStudent);
        //assert foundStudent == null : "Student should be deleted";
    }

    /**
     * Removes a student from the list.
     * Adds the removed student to a recently deleted list, where the student's information can be recovered
     *
     * @param masterStudentList   The list of all students.
     * @param recentlyDeletedList The list of recently deleted students
     * @param in                  The scanner object to read user input.
     * @param studentName         The name of the student if the user had entered it
     *                            before being prompted
     */
    private static void restoreStudent(ArrayList<Student> masterStudentList, ArrayList<Student> recentlyDeletedList,
                                       Scanner in, String studentName) {
        String name;
        if (studentName == null) {
            Ui.printStudentNamePrompt();
            name = in.nextLine().trim();
        } else {
            name = studentName;
        }

        Student foundStudent = findStudentByName(recentlyDeletedList, name);

        if (foundStudent != null) {
            Ui.printRestoreMessage();
        } else {
            Ui.printStudentNotFound();
        }

        Ui.printDivider();
        masterStudentList.add(foundStudent);
        recentlyDeletedList.remove(foundStudent);
    }

    /**
     * Restores the latest deleted student that has not yet been restored
     *
     * @param masterStudentList   The list of all students
     * @param recentlyDeletedList The list of recently deleted students
     */
    private static void undoDelete(ArrayList<Student> masterStudentList, ArrayList<Student> recentlyDeletedList) {
        if (recentlyDeletedList.isEmpty()) {
            Ui.printNoDeleteFound();
            Ui.printDivider();
            return;
        }
        Student student = recentlyDeletedList.get(recentlyDeletedList.size() - 1);
        masterStudentList.add(student);
        recentlyDeletedList.remove(student);
        Ui.printDeleteUndone();
        Ui.printDivider();
    }

    //@@author tayponghee
    /**
     * Displays the details of a specific student.
     *
     * @param masterStudentList The list of all students.
     * @param in                The scanner object to read user input.
     * @param studentName       The name of the student if the user had entered it
     *                          before being prompted
     */
    private static void viewStudent(ArrayList<Student> masterStudentList, Scanner in, String studentName) {
        
        String name;

        //@@author alalal47
        if (studentName == null) {
            Ui.printStudentNamePrompt();
            name = in.nextLine();
        } else {
            name = studentName;
        }

        //@@author blackmirag3
        assert name != null : "Student name cannot be null";
        //@@author tayponghee
        Student foundStudent = findStudentByName(masterStudentList, name);

        if (foundStudent != null) {
            logger.log(Level.INFO, "Viewing student details: " + name);
            Ui.printStudentDetails();
            Ui.printStudentName(name);
            Ui.printStudentDetails(foundStudent);
            StudentAttributes attributes = foundStudent.getAttributes();
            showAttributes(attributes);
        } else {
            logger.log(Level.WARNING, "Student not found: " + name);
            Ui.printStudentNotFound();
        }
    }

    /**
     * Displays the attributes of a student.
     *
     * @param attributes The attributes of the student to display.
     */
    private static void showAttributes(StudentAttributes attributes) {
        if (attributes != null) {

            List<SubjectGrade> subjectGrades = attributes.getSubjectGrades();

            if (!subjectGrades.isEmpty()) {

                for (SubjectGrade subjectGrade : subjectGrades) {
                    assert subjectGrade != null : "subjectGrade cannot be null";
                    Ui.printSubjectName(subjectGrade.getSubject());
                    Ui.printStudentGrades(subjectGrade.getGrade());
                    Ui.printClassesAttended(subjectGrade.getClassesAttended());
                    Ui.printDivider();
                }

            } else {
                Ui.printEmptySubjectError();
            }

        } else {
            Ui.printNullAttributeError();
        }
    }

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
    private static void addStudent(ArrayList<Student> masterStudentList, Scanner in, String studentName) {
        String name;

        name = checkForEmptyName(masterStudentList, in, studentName);

        if (findStudentByName(masterStudentList, name) != null) {
            assert findStudentByName(masterStudentList, name) != null;
            logger.log(Level.WARNING, "Student with the same name already exists.");
            
            Ui.printSameNameError();
            Ui.printDivider();
            return;
        }

        Student student = new Student(name);
        addSubject(in, student.getAttributes());

        // @@author Cryolian
        int number = promptForPhoneNumber(in);

        while (number < 0) {
            number = promptForPhoneNumber(in);
        }
        //@@ author ParthGandhiNUS
        assert number > 0 && number < 100000000: "Number is outside the acceptable range.";

        //@@author Cryolian
        student.getAttributes().setPhoneNumber(number);

        Ui.promptForGender();
        student.getAttributes().setGender(readInString(in));

        Ui.promptForLastPaymentDate();
        student.getAttributes().setLastPaymentDate(readInDate(in));

        Ui.promptForRemarks();
        student.getAttributes().setRemarks(readInString(in));

        //@@author tayponghee
        masterStudentList.add(student);
        logger.log(Level.INFO, "Student added successfully.");
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
                
                int newClassesAttended = promptForClassesAttended(in);
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

    // @@author tayponghee
    /**
     * Prompts the user to enter attributes for a student, including subject, grade,
     * and classes attended.
     * Continues to prompt the user until user enters blank to exit.
     *
     * @param in         The scanner object to read user input.
     * @param attributes The StudentAttributes object to store the attributes of the
     *                   student.
     */
    private static void addSubject(Scanner in, StudentAttributes attributes) {
        while (true) {
            // @@author blackmirag3
            System.out.print("Subject (enter nothing to skip): ");
            String subject = in.nextLine().trim();
            
            if (subject.isBlank()) {
                System.out.println("No subjects added.");
                break;

            } else if (attributes.findSubject(subject) != null) {
                // rejects subject if existing subject of same name exists in students'
                // attributes
                System.out.println("Subject already exists.");
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
     * @param in The scanner object to read user input.
     * @param attributes The attributes of the student.
     * @param subject The subject to be added.
     * @return True if the user chooses not to add another subject and grade, false otherwise.
     */
    private static boolean checkForValidSubjectResponse(Scanner in, StudentAttributes attributes, String subject) {
        double grade = Math.max(promptForGrade(in), 0);
        int classesAttended = promptForClassesAttended(in);
        SubjectGrade subjectGrade = new SubjectGrade(subject, grade, classesAttended);
        attributes.addSubjectGrade(subjectGrade);
        while (true) {
            System.out.println("Do you want to add another subject and grade? (yes/no)");
            String response = in.nextLine().trim().toLowerCase();
            if (response.equals("yes")) {
                break;
            } else if (response.equals("no")) {
                return true;
            } else {
                System.out.println("Invalid response. Please type 'yes' or 'no'.");
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
    private static int promptForClassesAttended(Scanner in) {
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
                System.out.println("Wrong number format! Please try again! e.g. 12 ");
                Ui.printDivider();
                classesAttended = promptForClassesAttended(in);
            }
            
            if (isValidClassesAttended(classesAttended)) {
                return classesAttended;
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
    private static double promptForGrade(Scanner in) {
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
    private static boolean isValidClassesAttended(int classesAttended) {
        try {
            if (classesAttended < 0) {
                System.out.println("Classes attended must be 0 or more.");
                Ui.printDivider();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Invalid input for classes attended: " + classesAttended, e);
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
            logger.log(Level.WARNING, "Invalid input for grade: " + grade, e);
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
    private static int promptForPhoneNumber(Scanner in) {

        int number = 0;
        
        try {
            
            do  {
                Ui.printPhoneNumberPrompt();
                number = readInPhoneNumber(in);
            } while (!checkNumberValidity(number));
            
            logger.log(Level.INFO, "Storing number: " + number);
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
    private static String readInString(Scanner in) {
        
        String string = in.nextLine();
        if (string.isBlank()) {
            return DEFAULT_STRING_VALUE;
        } 
        return string.trim();
        
    }

    protected static LocalDate readInDate(Scanner in) {

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
            logger.log(Level.INFO, "Storing today as the last payment date.");
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
        logger.log(Level.WARNING, "Invalid date format entered.");
        Ui.printInvalidDateError();
        return LocalDate.now().plusDays(2);
    }

    private static boolean isDateValid(LocalDate paymentDate) {

        return paymentDate.isBefore(LocalDate.now().plusDays(1))
                && paymentDate.isAfter(LocalDate.parse(EARLIER_POSSIBLE_DATE));
    }

}
