package classify.student;

public class SubjectGrade {
    private String subject;
    private double grade;
    private int classesAttended;

    private final String EMPTY = "unspecified";

    public SubjectGrade() {
        this.subject = EMPTY;
    }

    public SubjectGrade(String subject, double grade, int classesAttended) {
        this.subject = subject;
        this.grade = grade;
        this.classesAttended = classesAttended;
    }

    public String getSubject() {
        return subject;
    }

    public double getGrade() {
        return grade;
    }

    public int getClassesAttended() {
        return classesAttended;
    }
}
