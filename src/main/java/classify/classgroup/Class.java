package classify.classgroup;

// import classify.student.StudentList;
// import classify.student.Student;

/**
 * Class meant to represent a class for students. 
 */
public class Class {
    
    //@@author Cryolian
    public int ageOfStudents;
    public String className;
    public String subject;
    // public StudentList studentList = new StudentList();

    /**
     * Constructor for a class of Students.
     * @param ageOfStudents Age of the students in the class.
     * @param className Name of the class.
     */
    public Class(int ageOfStudents, String className) {
        this.ageOfStudents = ageOfStudents;
        this.className = className;
        assert this.ageOfStudents == ageOfStudents : "class attribute should be equal to constructor";
        assert this.className == className : "class attribute should be equal to constructor"; 
    }

    /**
     * Get the age of the students attending the class. 
     * @return the age of the students as an integer.
     */
    public int getAgeOfStudents() {
        return ageOfStudents;
    }

    public void setAgeOfStudents(int ageOfStudents) {
        this.ageOfStudents = ageOfStudents;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return this.className;
    }
}
