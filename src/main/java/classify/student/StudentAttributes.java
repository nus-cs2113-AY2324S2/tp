package classify.student;

public class StudentAttributes {
    private String name;
    private double grade;
    private int classesAttended;

    public StudentAttributes(String name, double grade, int classesAttended) {
        this.name = name;
        this.grade = grade;
        this.classesAttended = classesAttended;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getClassesAttended() {
        return classesAttended;
    }

    public void setClassesAttended(int classesAttended) {
        this.classesAttended = classesAttended;
    }


    @Override
    public String toString() {
        return "StudentAttributes{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                ", classesAttended=" + classesAttended +
                '}';
    }
}

