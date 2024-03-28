package classify.user;

import classify.student.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class DeleteCommands {
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
    static void deleteStudent(ArrayList<Student> masterStudentList, ArrayList<Student> recentlyDeletedList,
                              Scanner in, String studentName) {
        String name;
        if (studentName == null) {
            Ui.printStudentNamePrompt();
            name = in.nextLine().trim();
        } else {
            name = studentName;
        }

        Student foundStudent = InputParsing.findStudentByName(masterStudentList, name);

        if (foundStudent != null) {
            Ui.printStudentDeleted();
        } else {
            Ui.printStudentNotFound();
        }

        Ui.printDivider();
        recentlyDeletedList.add(foundStudent);
        masterStudentList.remove(foundStudent);
        assert InputParsing.findStudentByName(masterStudentList, name) == null : "Student should be deleted";
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
    static void restoreStudent(ArrayList<Student> masterStudentList, ArrayList<Student> recentlyDeletedList,
                               Scanner in, String studentName) {
        String name;
        if (studentName == null) {
            Ui.printStudentNamePrompt();
            name = in.nextLine().trim();
        } else {
            name = studentName;
        }

        Student foundStudent = InputParsing.findStudentByName(recentlyDeletedList, name);

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
    static void undoDelete(ArrayList<Student> masterStudentList, ArrayList<Student> recentlyDeletedList) {
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
}
