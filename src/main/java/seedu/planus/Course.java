package seedu.planus;

/**
 * Represents a course taken or planned by the user
 */
public class Course {
    private static final int TERM_PER_YEAR = 4;
    private static final int MAX_CANDIDATURE_YEAR = 6;

    private String courseCode;
    private String courseName;
    private int modularCredit;
    private int year;
    private int term;
    private Grade grade;

    /**
     * Constructor to initialise all the attributes
     *
     * @param courseCode Code of the course
     * @param courseName Name of the course
     * @param modularCredit Number of modular credits that the course awards
     * @param year Year of study that the user taken or plans to take the course
     * @param term Term that the user taken or plans to take the course
     */
    public Course(String courseCode, String courseName, int modularCredit, int year, int term) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.modularCredit = modularCredit;
        this.year = year;
        this.term = term;
        this.grade = new Grade();
    }

    /**
     * Overloaded constructor that sets the modular credit to be 4 by default when none is specified
     *
     * @param courseCode Code of the course
     * @param courseName Name of the course
     * @param year Year of study that the user taken or plans to take the course
     * @param term Term that the user taken or plans to take the course
     */
    public Course(String courseCode, String courseName, int year, int term) {
        this(courseCode, courseName, 4, year, term);
    }

    public void setGrade(String letterGrade) {
        grade.setLetterGrade(letterGrade);
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getModularCredit() {
        return modularCredit;
    }

    public int getYear() {
        return year;
    }

    public int getTerm() {
        return term;
    }

    public double getNumberGrade() {
        return grade.getNumberGrade();
    }

    public String getLetterGrade() {
        return grade.getLetterGrade();
    }

    /**
     * Returns a string containing details of the course
     *
     * @return A string that contains the course code, course name, and the number of modular credits
     */
    public String getDetails() {
        return courseCode + " " + courseName + " (MC: " + modularCredit + ")";
    }

    /**
     * Returns a string containing the grade in letter form of the course
     *
     * @return A string that contains the course code and the grade
     */
    public String getGrade() {
        return courseCode + ": " + getLetterGrade();
    }

    /**
     * Returns a string specifying the year, the term,
     * and also whether the term is a normal semester or a special term
     *
     * @return A formatted string that contains the year and term that the user taken or plans to take the course
     */
    public String getYearAndTerm() {
        assert (term >= 1 || term <= TERM_PER_YEAR): "Term is not from 1 to 4";
        assert (year >= 1 || year <= MAX_CANDIDATURE_YEAR): "Year is not from 1 to 6";

        String s = "";
        s += "Year " + year;
        if (term >= 3) {
            s += " Special Term ";
            s += term - 2;
        } else {
            s += " Semester ";
            s += term;
        }
        return s;
    }

    @Override
    public String toString() {
        return courseCode + "," + courseName + "," + modularCredit + "," + year + "," + term + "," + getLetterGrade();
    }
}
