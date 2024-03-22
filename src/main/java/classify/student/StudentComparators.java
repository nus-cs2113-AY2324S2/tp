package classify.student;

import java.util.Comparator;

public class StudentComparators {
    public static Comparator<Student> nameComparator = Comparator.comparing(Student::getName);
}
