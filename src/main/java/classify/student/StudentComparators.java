package classify.student;

import java.util.Comparator;

public class StudentComparators {
    //@@author tayponghee
    public static Comparator<Student> nameComparator = Comparator.comparing(Student::getName);
    public static Comparator<Student> classesAttendedComparator =
            Comparator.comparing(Student::getTotalClassesAttended);

    //@@author alalal47
    public static Comparator<Student> lastPaidDateComparator = Comparator.comparing(Student::getLastPaymentDate);
}
