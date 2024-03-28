package classify.student;

import classify.user.Ui;
import classify.user.InputParsing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewStudent {

    private static final Logger logger = Logger.getLogger(ViewStudent.class.getName());

    //@@author tayponghee
    /**
     * Displays the details of a specific student.
     *
     * @param masterStudentList The list of all students.
     * @param in                The scanner object to read user input.
     * @param studentName       The name of the student if the user had entered it
     *                          before being prompted
     */
    public static void viewStudent(ArrayList<Student> masterStudentList, Scanner in, String studentName) {

        String name;

        //@@ author alalal47
        if (studentName == null) {
            Ui.printStudentNamePrompt();
            name = in.nextLine();
        } else {
            name = studentName;
        }

        //@@author blackmirag3
        assert name != null : "Student name cannot be null";

        //@@author tayponghee
        Student foundStudent = InputParsing.findStudentByName(masterStudentList, name);

        if (foundStudent != null) {
            logger.log(Level.INFO, "Viewing student details: " + name);
            Ui.printDivider();
            Ui.printStudentDetails();
            Ui.printStudentName(name);
            Ui.printStudentDetails(foundStudent);
            StudentAttributes attributes = foundStudent.getAttributes();
            showAttributes(attributes);

            int totalClassesAttended = getTotalClassesAttended(foundStudent);
            Ui.printTotalClassesAttended(totalClassesAttended);

        } else {
            logger.log(Level.WARNING, "Student not found: " + name);
            Ui.printStudentNotFound();
        }
    }

    /**
     * Calculates the total number of classes attended by the student across all subjects.
     *
     * @param student The student whose total classes attended are to be calculated.
     * @return The total number of classes attended by the student.
     */
    private static int getTotalClassesAttended(Student student) {
        int totalClassesAttended = 0;
        for (classify.student.SubjectGrade subjectGrade : student.getAttributes().getSubjectGrades()) {
            totalClassesAttended += subjectGrade.getClassesAttended();
        }
        return totalClassesAttended;
    }

    /**
     * Displays the attributes of a student.
     *
     * @param attributes The attributes of the student to display.
     */
    public static void showAttributes(StudentAttributes attributes) {
        if (attributes != null) {
            List<SubjectGrade> subjectGrades = attributes.getSubjectGrades();
            checkIfSubjectGradesIsEmpty(subjectGrades);
        } else {
            Ui.printNullAttributeError();
        }
    }

    private static void checkIfSubjectGradesIsEmpty(List<SubjectGrade> subjectGrades) {
        if (!subjectGrades.isEmpty()) {
            printSubjectAttributes(subjectGrades);
        } else {
            Ui.printEmptySubjectError();
        }
    }

    private static void printSubjectAttributes(List<SubjectGrade> subjectGrades) {
        for (SubjectGrade subjectGrade : subjectGrades) {
            assert subjectGrade != null : "subjectGrade cannot be null";
            Ui.printSubjectName(subjectGrade.getSubject());
            Ui.printStudentGrades(subjectGrade.getGrade());
            Ui.printClassesAttended(subjectGrade.getClassesAttended());
            Ui.printDivider();
        }
    }

}
