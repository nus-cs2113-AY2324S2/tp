package classify.student;

import classify.user.Ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentSorter {

    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String INVALID_CHOICE = "Invalid choice! Type either 1 or 2 to sort the list by the selected attribute";

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
        case TWO:
            listStudentsByTotalClasses(masterStudentList);
            break;
        default:
            Ui.println(INVALID_CHOICE);
            break;
        }
    }

    /**
     * Checks if the choice is valid (either "1" or "2").
     *
     * @param choice The input choice.
     * @return True if the choice is valid, false otherwise.
     */
    public static boolean isValidChoice(String choice) {
        return ONE.equals(choice) || TWO.equals(choice);
    }

    /**
     * Lists students in the provided list by name in ascending order.
     *
     * @param masterStudentList The list of students to be listed by name.
     */
    private static void listStudentsByName(ArrayList<Student> masterStudentList) {
        masterStudentList.sort(Comparator.comparing(Student::getName));
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
        masterStudentList.sort(Comparator.comparingInt(Student::getTotalClassesAttended));
        listStudentsWithTotalClasses(masterStudentList);

        Ui.printDivider();
    }

    /**
     * Lists students in the provided list.
     *
     * @param students The list of students to be listed.
     */
    private static void listStudents(ArrayList<Student> students) {
        for (Student student : students) {
            System.out.println(student.getName());
        }
    }

    /**
     * Lists students in the provided list along with their total classes attended.
     *
     * @param students The list of students to be listed.
     */
    private static void listStudentsWithTotalClasses(ArrayList<Student> students) {
        for (Student student : students) {
            System.out.println(student.getName() + " - Total Classes Attended: " + student.getTotalClassesAttended());
        }
    }
}
