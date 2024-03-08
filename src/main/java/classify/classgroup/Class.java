package classify.classgroup;

import classify.student.StudentList;
import classify.student.Student;

public class Class {
    
    //@@author Cryolian
    public int year;
    public String className;
    public StudentList studentList;

    public Class(int year, String className) {
        this.year = year;
        this.className = className;
    }

    public void addStudent(Student s) {
        this.studentList.addStudent(s);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return this.className;
    }
}
