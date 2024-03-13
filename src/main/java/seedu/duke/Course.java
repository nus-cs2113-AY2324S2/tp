package seedu.duke;

public class Course {
    protected String courseCode;
    protected String courseName;
    protected int modularCredit;
    protected int year;
    protected int term;

    public Course(String courseCode, String courseName, int modularCredit, int year, int term) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.modularCredit = modularCredit;
        this.year = year;
        this.term = term;
    }
}
