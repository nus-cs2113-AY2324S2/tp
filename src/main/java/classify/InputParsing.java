package classify;

import classify.student.Student;
import classify.student.StudentAttributes;
import classify.student.StudentList;
import classify.student.SubjectGrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputParsing {
    private static final String NOTEMPTY = "THIS STRING IS NOT EMPTY";
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String ADD = "add";
    private static final String VIEW = "view";
    private static final String DELETE = "delete";
    //@@author blackmirag3
    private static final String EDIT = "edit";
    private static final String WRONG_INPUT_MESSAGE = "No such command, type \"help\" to view all commands";
    //@@author tayponghee
    private static final String ENTER_STUDENT_DETAILS = "Enter student details: ";
    private static final String ENTER_STUDENT_NAME = "Enter student name: ";
    private static final String STUDENT_DETAILS = "Student details: ";
    private static final String NAME = "Name: ";
    private static final String CLASSES_ATTENDED = "Classes Attended (blank to skip): ";
    private static final String STUDENT_NOT_FOUND = "Student not found!";
    private static final String STUDENT_ADDED_SUCCESSFULLY = "Student added successfully!";
    private static final String STUDENT_DELETED_SUCCESSFULLY = "Student removed successfully!";
    private static final String HELP = "help";
    private static final Logger logger = Logger.getLogger(InputParsing.class.getName());
    private static final String SUBJECT = "Subject: ";
    private static final String CURRENT_MARKS_OUT_OF_100 = "Current marks out of 100 (blank to skip) : ";


    public static void parseUserCommand(String[] userCommand, ArrayList<Student> masterStudentList, Scanner in){
        //@@author blackmirag3
        if (masterStudentList == null) {
            System.out.println("Student list is null.");
        }
        //@@author tayponghee
        switch (userCommand[0]) {
        case ADD:
            addStudent(masterStudentList, in, userCommand[1]);
            break;

        case VIEW:
            viewStudent(masterStudentList, in, userCommand[1]);
            Ui.printDivider();
            break;

        //@@author alalal47
        case DELETE:
            deleteStudent(masterStudentList, in, userCommand[1]);
            break;

        case HELP:
            printHelpMessage();
            Ui.printDivider();
            break;

        //@@author ParthGandhiNUS
        case BYE:
            Ui.printEndConversation();
            break;

        case LIST:
            listStudents(masterStudentList);
            break;

        case EDIT:
            editStudent(masterStudentList, in, userCommand[1]);
            break;

        default:
            System.out.println(WRONG_INPUT_MESSAGE);
            Ui.printDivider();
            break;
        }
    }
    //@@author blackmirag3
    private static void listStudents(ArrayList<Student> list) {
        StudentList.printCurrentArrayList(list);
        //@@author ParthGandhiNUS
        StudentList.printCurrentArrayMessage(list);
        Ui.printDivider();
    }

    private static void editStudent(ArrayList<Student> list, Scanner in, String name) {
        if (list.isEmpty()) {
            System.out.println("Currently no students in list.");
            return;
        }
        Student student = null;
        if (name != null) {
            student = findStudentByName(list, name);
            if (student == null) {
                System.out.println(STUDENT_NOT_FOUND);
            }
        }
        while (student == null) {
            System.out.println("Name of student to edit (enter blank to exit):");
            name = in.nextLine().trim();
            if (name.isBlank()) {
                return;
            }
            student = findStudentByName(list, name);
            if (student == null) {
                System.out.println(STUDENT_NOT_FOUND);
            } else {
                break;
            }
        }
        StudentAttributes attributes = student.getAttributes();
        showAttributes(attributes);
        //edits only StudentAttribute. (Not Name or Details)
        while (true) {
            System.out.println("How would you like to update student's subject? (enter blank to exit)");
            String command = in.nextLine().trim();
            if (command.isBlank()) {
                return;
            }
            switch (command) {
            case ADD:
                addAttribute(in, attributes);
                student.setAttributes(attributes);
                break;

            case EDIT:
                editAttribute(in, attributes);
                break;

            case DELETE:
                deleteAttribute(in, attributes);
                break;

            default:
                System.out.println("invalid input");
                break;
            }
        }
        //if done overwrite student
    }

    //@@author alalal47
    /**
     * Removes a student from the list.
     *
     * @param masterStudentList The list of all students.
     * @param in                The scanner object to read user input.
     * @param studentName       The name of the student if the user had entered it before being prompted
     */
    private static void deleteStudent(ArrayList<Student> masterStudentList, Scanner in, String studentName) {
        String name;
        if (studentName == null) {
            System.out.print(ENTER_STUDENT_NAME);
            name = in.nextLine().trim();
        } else {
            name = studentName;
        }

        Student foundStudent = findStudentByName(masterStudentList, name);
        if (foundStudent != null) {
            System.out.println(NAME + foundStudent.getName());
            System.out.println(STUDENT_DELETED_SUCCESSFULLY);
        } else {
            System.out.println(STUDENT_NOT_FOUND);
        }
        Ui.printDivider();
        masterStudentList.remove(foundStudent);
    }

    /**
     * Displays the help message to teach users how to use Classify.
     */
    private static void printHelpMessage() {
        System.out.println("add                         Adds a student to the student list, " +
                                                        "expects a name, grade and lessons attended" +
                                                        ", can be used directly with a name e.g. add [name]");
        System.out.println("view                        Views a students details, expects a name" +
                                                        ", can be used directly with a name e.g. add [name]");
        System.out.println("delete                      Deletes a student from the student list, expects a name" +
                                                        ", can be used directly with a name e.g. add [name]");
        System.out.println("list                        Displays the list of all students");
        System.out.println("bye                         Exits Classify");
        System.out.println("help                        Prints this help message");
    }

    //@@author tayponghee
    /**
     * Displays the details of a specific student.
     *
     * @param masterStudentList The list of all students.
     * @param in                The scanner object to read user input.
     * @param studentName       The name of the student if the user had entered it before being prompted
     */
    private static void viewStudent(ArrayList<Student> masterStudentList, Scanner in, String studentName) {
        String name;
        if (studentName == null) {
            System.out.print(ENTER_STUDENT_NAME);
            name = in.nextLine();
        } else {
            name = studentName;
        }

        assert name != null : "Student name cannot be null";

        Student foundStudent = findStudentByName(masterStudentList, name);
        if (foundStudent != null) {
            logger.log(Level.INFO, "Viewing student details: " + name);
            System.out.println(STUDENT_DETAILS);
            System.out.println(NAME + foundStudent.getName());
            StudentAttributes attributes = foundStudent.getAttributes();
            showAttributes(attributes);
        } else {
            logger.log(Level.WARNING, "Student not found: " + name);
            System.out.println(STUDENT_NOT_FOUND);
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
                    System.out.println(SUBJECT + subjectGrade.getSubject());
                    System.out.println(CURRENT_MARKS_OUT_OF_100 + subjectGrade.getGrade());
                    System.out.println("Classes Attended: " + subjectGrade.getClassesAttended());
                    System.out.println();
                }
            } else {
                System.out.println("No subjects and grades found for this student.");
            }
        } else {
            System.out.println("No attributes found for this student.");
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
     * @param studentName       The name of the student if the user had entered it before being prompted
     */
    private static void addStudent(ArrayList<Student> masterStudentList, Scanner in, String studentName) {
        String name;
        if (studentName == null) {
            System.out.println(ENTER_STUDENT_DETAILS);
        }
        name = checkForEmptyName(masterStudentList, in, studentName);
        assert name != null : "Student name cannot be empty";
        StudentAttributes attributes = new StudentAttributes(name);
        addAttribute(in, attributes);
        Student student = new Student(name, attributes);
        masterStudentList.add(student);
        logger.log(Level.INFO, "Student added successfully.");
        System.out.println(STUDENT_ADDED_SUCCESSFULLY);
        Ui.printDivider();
    }

    /**
     * Prompts the user to enter a non-empty name for a student and checks if it already exists in the list.
     * If the name is empty or already exists, prompts the user again until valid input is provided.
     *
     * @param masterStudentList The list of all students.
     * @param in                The scanner object to read user input.
     * @param studentName       The name of the student if the user had entered it before being prompted
     * @return The valid non-empty name entered by the user.
     */
    private static String checkForEmptyName(ArrayList<Student> masterStudentList, Scanner in, String studentName) {
        String name;
        while (true) {
            if (studentName == null) {
                System.out.print(NAME);
                name = in.nextLine().trim();
            } else {
                name = studentName.trim();
                studentName = NOTEMPTY;
            }
            if (name.isEmpty()) {
                System.out.println("Student name cannot be empty. Please enter a valid name.");
                Ui.printDivider();
            } else if (findStudentByName(masterStudentList, name) != null) {
                logger.log(Level.WARNING, "Student with the same name already exists.");
                System.out.println("Student with the same name already exists. Please enter a different name.");
                Ui.printDivider();
            } else {
                break;
            }
        }
        return name;
    }

    //@@author blackmirag3
    /**
     * Prompts the user to edit subject, grade, and classes attended for student.
     * Continues to prompt the user until user enters blank to exit.
     *
     * @param in         The scanner object to read user input.
     * @param attributes The StudentAttributes object to store the attributes of the student.
     */
    private static void editAttribute(Scanner in, StudentAttributes attributes) {
        while (true) {
            System.out.print("Subject to edit (enter nothing to exit): ");
            String subjectToFind = in.nextLine().trim();
            if (subjectToFind.isBlank()) {
                System.out.println("no subject edited");
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
                currentSubject.setGrade(newGrade);
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

    //@@author tayponghee
    /**
     * Prompts the user to enter attributes for a student, including subject, grade, and classes attended.
     * Continues to prompt the user until user enters blank to exit.
     *
     * @param in         The scanner object to read user input.
     * @param attributes The StudentAttributes object to store the attributes of the student.
     */
    private static void addAttribute(Scanner in, StudentAttributes attributes) {
        while (true) {
            //@@author blackmirag3
            System.out.print("Subject (enter nothing to skip): ");
            String subject = in.nextLine().trim();
            if (subject.isBlank()) {
                System.out.println("No subjects added.");
                break;
            } else if (attributes.findSubject(subject) != null) {
                //rejects subject if existing subject of same name exists in students' attributes
                System.out.println("Subject already exists.");
                break;
            } else {
                //@@author tayponghee
                double grade = promptForGrade(in);
                int classesAttended = promptForClassesAttended(in);
                SubjectGrade subjectGrade = new SubjectGrade(subject, grade, classesAttended);
                attributes.addSubjectGrade(subjectGrade);
                System.out.println("Do you want to add another subject and grade? (yes/no)");
                String response = in.nextLine().trim().toLowerCase();
                if (!response.equals("yes")) {
                    break;
                }
            }
        }
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
            System.out.print(CLASSES_ATTENDED);
            String classesAttendedInput = in.nextLine();
            if (classesAttendedInput.isBlank()) {
                return 0;
            }
            int classesAttended = Integer.parseInt(classesAttendedInput);
            if (isValidClassesAttended(classesAttended)) {
                return classesAttended;
            }
        }
    }

    /**
     * Prompts for grade from user input and checks format
     * Prompts the user to enter a valid double within the range [0, 100] until one is provided.
     *
     * @param in The scanner object to read user input.
     * @return The valid grade.
     */
    private static double promptForGrade(Scanner in) {
        while (true) {
            System.out.print(CURRENT_MARKS_OUT_OF_100);
            String gradeInput = in.nextLine();
            if (gradeInput.isBlank()) {
                return 0;
            }
            //@@author ParthGandhiNUS
            double grade = 0;
            try {
                grade = Double.parseDouble(gradeInput);
            } catch (NumberFormatException e) {
                System.out.println("Wrong number format! Please try again! ");
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
    private static Student findStudentByName(ArrayList<Student> masterStudentList, String name) {
        for (Student student : masterStudentList) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }

    //@@author blackmirag3
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
            System.out.println("Invalid input for grade. Please enter a valid number.");
            Ui.printDivider();
            return false;
        }
    }
}
