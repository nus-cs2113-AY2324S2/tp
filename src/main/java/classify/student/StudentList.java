package classify.student;

import java.util.ArrayList;

/**
 * Class to represent a list of Students.
 */
public class StudentList {
    //@@ParthGandhiNUS
    public static final String DOT = ".";
    public static ArrayList<Student> masterStudentList = new ArrayList<>();

    //@@author Cryolian
    public ArrayList<Student> studentList;

    //@@author Cryolian
    public StudentList() {
        this.studentList = new ArrayList<>();
    }

    //@@author ParthGandhiNUS
    /**
     * Used in the "list" command to print all the Current Tasks in the proper format
     */
    public static void printCurrentArrayList(ArrayList<Student>currentList){
        for (int i = 1; i <= currentList.size(); i++){
            System.out.println(i + DOT + currentList.get(i-1));
        }
    }

    //@@author Cryolian
    /**
     * Adds in an instance of a student to the student list.
     * @param s The student to add to the list.
     */
    public void addStudent(Student s) {
        this.studentList.add(s);
    }

    /**
     * Removes a student of a certain id from the student list.
     * @param id The id of the student to remove.
     */
    public void removeStudent(int id) {
        this.studentList.remove(id);
    }


    //@@author Cryolian
    /**
     * Removes the first student in the list with a name matching
     * the given string.
     * @param name The string to search among the names of the student to remove.
     */
    public void removeStudent(String name) {
        for (Student s: studentList) {
            if (s.getName().equals(name)) {
                studentList.remove(s);
                return;
            }
        }
    }

}
