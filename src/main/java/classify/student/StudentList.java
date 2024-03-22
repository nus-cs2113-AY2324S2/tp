package classify.student;

import java.util.ArrayList;
import java.util.Comparator;


/**
 * Class to represent a list of Students.
 */
public class StudentList {
    //@@authorParthGandhiNUS
    public static final String DOT = ".";
    public static final String ZERO_STUDENT_MESSAGE = "Currently, there are 0 students in the list.";
    public static final String ONE_STUDENT_MESSAGE = "Currently, there is 1 student in the list.";
    public static final String CURRENTLY_THERE_ARE = "Currently, there are ";
    public static final String STUDENTS_IN_THE_LIST = " students in the list.";
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
     * @param currentList takes in an array to print out the name and other attributes of the Student
     */
    public static void printCurrentArrayList(ArrayList<Student>currentList){
        for (int i = 1; i <= currentList.size(); i++){
            System.out.println(i + DOT + currentList.get(i-1));
        }
    }

    //@@author ParthGandhiNUS
    /**
     * Prints a statement displaying the number of students in the  Student List
     * @param currentList takes in a Student list to get its size
     */
    public static void printCurrentArrayMessage(ArrayList<Student>currentList){
        int numberOfStudents = currentList.size();

        switch (numberOfStudents){
        case 0:
            System.out.println(ZERO_STUDENT_MESSAGE);
            break;

        case 1:
            System.out.println(ONE_STUDENT_MESSAGE);
            break;

        default:
            System.out.println(CURRENTLY_THERE_ARE + numberOfStudents + STUDENTS_IN_THE_LIST);
            break;
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
