package classify.student;

import java.util.ArrayList;

public class StudentList {

    //@@author Cryolian
    public ArrayList<Student> studentList;

    public StudentList() {
        this.studentList = new ArrayList<>();
    } 

    public void addStudent(Student s) {
        this.studentList.add(s);
    }

    public void removeStudent(int id) {
        this.studentList.remove(id);
    }

    public void removeStudent(String name) {
        for (Student s: studentList) {
            if (s.getName().equals(name)) {
                studentList.remove(s);
                return;
            }
        }
    }

}
