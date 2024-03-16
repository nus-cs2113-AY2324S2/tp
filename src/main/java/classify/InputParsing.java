package classify;

import classify.student.Student;
import classify.student.StudentAttributes;
import classify.student.StudentList;

import java.util.ArrayList;
import java.util.Scanner;

public class InputParsing {
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String ADD = "add";
    private static final String VIEW = "view";
    private static final String DELETE = "delete";
    private static final String WRONG_INPUT_MESSAGE = "Wrong Input! Please try again!";
    //@@author tayponghee
    private static final String ENTER_STUDENT_NAME = "Enter student name: ";
    private static final String STUDENT_DETAILS = "Student details:";
    private static final String NAME = "Name: ";
    private static final String GRADE = "Grade: ";
    private static final String CLASSES_ATTENDED = "Classes Attended: ";
    private static final String STUDENT_NOT_FOUND = "Student not found!";
    private static final String STUDENT_ADDED_SUCCESSFULLY = "Student added successfully!";
    private static final String STUDENT_DELETED_SUCCESSFULLY = "Student removed successfully!";
    private static final String HELP = "help";

    public static void parseUserCommand(String userCommand, ArrayList<Student> masterStudentList, Scanner in){
        switch (userCommand) {
        case ADD:
            in.nextLine();
            addStudent(masterStudentList, in);
            break;

        case VIEW:
            in.nextLine();
            viewStudent(masterStudentList, in);
            Ui.printDivider();
            break;

        //@@author alalal47
        case DELETE:
            in.nextLine();
            deleteStudent(masterStudentList, in);
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
            if (masterStudentList != null) {
                StudentList.printCurrentArrayList(masterStudentList);
            } else {
                System.out.println("Student list is null.");
            }
            Ui.printDivider();
            break;

        default:
            System.out.println(WRONG_INPUT_MESSAGE);
            Ui.printDivider();
            break;
        }
    }

    //@@author alalal47
    private static void deleteStudent(ArrayList<Student> masterStudentList, Scanner in) {
        System.out.print(ENTER_STUDENT_NAME);
        String studentName = in.nextLine().trim();
        Student foundStudent = findStudentByName(masterStudentList, studentName);
        if (foundStudent != null) {
            System.out.println(NAME + foundStudent.getName());
            System.out.println(STUDENT_DELETED_SUCCESSFULLY);
        } else {
            System.out.println(STUDENT_NOT_FOUND);
        }
        Ui.printDivider();
        masterStudentList.remove(foundStudent);
    }

    private static void printHelpMessage() {
        System.out.println("add                         Adds a student to the student list, " +
                                                        "expects a name, grade and lessons attended");
        System.out.println("view                        Views a students details, expects a name");
        System.out.println("delete                      Deletes a student from the student list, expects a name");
        System.out.println("list                        Displays the list of all students");
        System.out.println("bye                         Exits Classify");
        System.out.println("help                        Prints this help message");
    }

    //@@author tayponghee
    private static void viewStudent(ArrayList<Student> masterStudentList, Scanner in) {
        System.out.print(ENTER_STUDENT_NAME);
        String studentName = in.nextLine();
        Student foundStudent = findStudentByName(masterStudentList, studentName);
        if (foundStudent != null) {
            System.out.println(STUDENT_DETAILS);
            System.out.println(NAME + foundStudent.getName());
            System.out.println(GRADE + foundStudent.getAttributes().getGrade());
            System.out.println(CLASSES_ATTENDED + foundStudent.getAttributes().getClassesAttended());
        } else {
            System.out.println(STUDENT_NOT_FOUND);
        }
    }

    private static void addStudent(ArrayList<Student> masterStudentList, Scanner in) {
        System.out.println(ENTER_STUDENT_NAME);

        System.out.print(NAME);
        String name = in.nextLine().trim();

        System.out.print(GRADE);
        double grade = parseDoubleInput(in.nextLine());

        System.out.print(CLASSES_ATTENDED);
        int classesAttended = parseIntegerInput(in.nextLine());

        StudentAttributes attributes = new StudentAttributes(name, grade, classesAttended);
        Student student = new Student(name, attributes);
        masterStudentList.add(student);

        System.out.println(STUDENT_ADDED_SUCCESSFULLY);
        Ui.printDivider();
    }

    private static double parseDoubleInput(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for grade. Please enter a valid number.");
            return 0;
        }
    }

    private static int parseIntegerInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for classes attended. Please enter a valid number.");
            return 0;
        }
    }

    private static Student findStudentByName(ArrayList<Student> masterStudentList, String name) {
        for (Student student : masterStudentList) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }
}
