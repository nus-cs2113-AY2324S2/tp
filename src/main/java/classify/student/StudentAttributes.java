package classify.student;

import java.util.ArrayList;
import java.util.List;

public class StudentAttributes extends Details {
    private Student student;
    private List<SubjectGrade> subjectGrades;

    public StudentAttributes(Student student) {
        this.student = student;
        this.subjectGrades = new ArrayList<>();
    }

    public void addSubjectGrade(SubjectGrade subjectGrade) {
        subjectGrades.add(subjectGrade);
    }

    public List<SubjectGrade> getSubjectGrades() {
        return subjectGrades;
    }

    public String getName() {
        return student.getName();
    }

    public void setName(String name) {
        student.setName(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("StudentAttributes{" +
                "name: '" + student.getName() + '\'' +
                ", H/P: " + String.valueOf(phoneNumber) + '\'' +
                ", Gender: " + gender + '\'' +
                ", Remarks: " + remarks + '\'' +
                ", subjectGrades=[");

        for (SubjectGrade subjectGrade : subjectGrades) {
            sb.append(subjectGrade.toString()).append(", ");
        }
        
        sb.append("]}");
        return sb.toString();
    }

    //@@author blackmirag3
    public SubjectGrade findSubject(String subjectName) {

        for (SubjectGrade subjectGrade : subjectGrades) {
            String currentSubjectName = subjectGrade.getSubject();

            if (currentSubjectName.equals(subjectName)) {
                return subjectGrade;
            }
        }
        return null;
    }

    public void deleteSubject(String subjectName) {

        for (SubjectGrade subjectGrade : subjectGrades) {
            String currentSubjectName = subjectGrade.getSubject();

            if (currentSubjectName.equals(subjectName)) {
                subjectGrades.remove(subjectGrade);
                return;
            }
        }
    }

}
