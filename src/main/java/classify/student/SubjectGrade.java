package classify.student;

public class SubjectGrade {
    private String subject;
    private double grade;
    private int classesAttended;

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

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setClassesAttended(int classesAttended) {
        this.classesAttended = classesAttended;
    }
}
