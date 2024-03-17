package classify.student;

import java.util.ArrayList;
import java.util.List;

public class StudentAttributes {
    private String name;
    private List<SubjectGrade> subjectGrades;

    public StudentAttributes(String name) {
        this.name = name;
        this.subjectGrades = new ArrayList<>();
    }

    public void addSubjectGrade(SubjectGrade subjectGrade) {
        subjectGrades.add(subjectGrade);
    }

    public List<SubjectGrade> getSubjectGrades() {
        return subjectGrades;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StudentAttributes{" +
                "name='" + name + '\'' +
                ", subjectGrades=[");
        for (SubjectGrade subjectGrade : subjectGrades) {
            sb.append(subjectGrade.toString()).append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }
}
