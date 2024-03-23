package classify.student;

import java.time.LocalDate;

/**
 * Meant to represent a particular student of the tuition centre.
 * The student can first be declared with only a name, and the subsequent details
 * added later.
 */
public class Student {

    //@@author Cryolian
    public String name;

    //@@author tayponghee
    private StudentAttributes attributes;

    private int totalClassesAttended;

    public int getTotalClassesAttended() {
        return totalClassesAttended;
    }

    public void setTotalClassesAttended(int totalClassesAttended) {
        this.totalClassesAttended = totalClassesAttended;
    }

    //@@author Cryolian

    public Student(String name) {
        this.name = name;
        this.attributes = new StudentAttributes(this);
    }

    public void setLastPaymentDate(LocalDate lastPaymenDate) {
        this.attributes.setLastPaymentDate(lastPaymenDate);
    }

    //@@author tayponghee
    public StudentAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(StudentAttributes attributes) {
        this.attributes = attributes;
    }

    public String getGender() {
        return attributes.getGender();
    }

    public int getPhoneNumber() {
        return attributes.getPhoneNumber();
    }

    public LocalDate getLastPaymentDate() {
        return attributes.getLastPaymentDate();
    }
    public boolean hasSubject(String subject) {
        for (SubjectGrade subjectGrade : attributes.getSubjectGrades()) {
            if (subjectGrade.getSubject().equalsIgnoreCase(subject)) {
                return true;
            }
        }
        return false;
    }

    //@@author ParthGandhiNUS
    public String textFileInputString() {
        return String.format("%s",getName().trim());
    }

    //@@author Cryolian
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
