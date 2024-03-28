package classify.student;

import classify.user.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentSorter {

    public static final String NAME = "name";
    public static final String PAYMENT_DATE = "payment";
    public static final String TOTAL_CLASSES = "classes";
    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String THREE = "3";
    public static final String INVALID_CHOICE = "Invalid choice! " +
            "Type either 1, 2 or 3 to sort the list by the selected attribute";

    //@@author tayponghee
    /**
     * Sorts the list of students based on the specified choice.
     *
     * @param masterStudentList The list of students to be sorted.
     * @param choice            The sorting criterion ("1" for name, "2" for total classes).
     * @param in                The Scanner object for user input.
     */
    public static void sortByChoice(ArrayList<Student> masterStudentList, String choice, Scanner in) {
        while (!isValidChoice(choice)) {
            Ui.println(INVALID_CHOICE);
            choice = in.nextLine().trim();
        }

        switch (choice) {
        case ONE:
            listStudentsByName(masterStudentList);
            break;
        case NAME:
            listStudentsByName(masterStudentList);
            break;
        case TWO:
            listStudentsByTotalClasses(masterStudentList);
            break;
        case TOTAL_CLASSES:
            listStudentsByTotalClasses(masterStudentList);
            break;
        //@@author alalal47
        case THREE:
            listStudentsByLastPaidDate(masterStudentList);
            break;
        case PAYMENT_DATE:
            listStudentsByLastPaidDate(masterStudentList);
            break;
        //@@author tayponghee
        default:
            Ui.println(INVALID_CHOICE);
            break;
        }
    }

    /**
     * Checks if the choice is valid (either "1", "2" or "3").
     *
     * @param choice The input choice.
     * @return True if the choice is valid, false otherwise.
     */
    public static boolean isValidChoice(String choice) {
        return (ONE.equals(choice) || TWO.equals(choice) || THREE.equals(choice)) ||
                (NAME.equals(choice) || PAYMENT_DATE.equals(choice) || TOTAL_CLASSES.equals(choice));
    }

    /**
     * Lists students in the provided list by name in ascending order.
     *
     * @param masterStudentList The list of students to be listed by name.
     */
    private static void listStudentsByName(ArrayList<Student> masterStudentList) {
        masterStudentList.sort(StudentComparators.nameComparator);
        listStudents(masterStudentList);
        Ui.printDivider();
    }

    /**
     * Lists students in the provided list by total classes attended in ascending order.
     *
     * @param masterStudentList The list of students to be listed by total classes attended.
     */
    private static void listStudentsByTotalClasses(ArrayList<Student> masterStudentList) {
        for (Student student : masterStudentList) {
            int totalClassesAttended = 0;

            StudentAttributes attributes = student.getAttributes();
            List<SubjectGrade> subjectGrades = attributes.getSubjectGrades();

            for (SubjectGrade subjectGrade : subjectGrades) {
                totalClassesAttended += subjectGrade.getClassesAttended();
            }

            student.setTotalClassesAttended(totalClassesAttended);
        }
        masterStudentList.sort(StudentComparators.classesAttendedComparator);
        listStudentsWithTotalClasses(masterStudentList);

        Ui.printDivider();
    }

    //@@author alalal47
    /**
     * Lists students in the provided list by last paid date attended in ascending order, with the most recent first.
     *
     * @param masterStudentList The list of students to be listed by total classes attended.
     */
    private static void listStudentsByLastPaidDate(ArrayList<Student> masterStudentList) {
        masterStudentList.sort(StudentComparators.lastPaidDateComparator);
        listStudentsWithLastPaidDate(masterStudentList);
        Ui.printDivider();
    }

    //@@author tayponghee
    /**
     * Lists students in the provided list.
     *
     * @param students The list of students to be listed.
     */
    private static void listStudents(ArrayList<Student> students) {
        Ui.printStudentList(students);
    }

    /**
     * Lists students in the provided list along with their total classes attended.
     *
     * @param students The list of students to be listed.
     */
    private static void listStudentsWithTotalClasses(ArrayList<Student> students) {
        int i = 1;
        for (Student student : students) {
            System.out.println(i + "." + student.getName() + " - Total Classes Attended: " +
                    student.getTotalClassesAttended());
            i ++;
        }
    }

    //@@author alalal47
    private static void listStudentsWithLastPaidDate(ArrayList<Student> students) {
        int i = 1;
        for (Student student : students) {
            System.out.println(i + "." + student.getName() + " - Date of last payment: " +
                    student.getLastPaymentDate());
            i ++;
        }
    }
}
