package seedu.duke;
import java.time.LocalDate;

public class Time {
    static LocalDate currentDate = LocalDate.now();

    public static void printStuff() {
        System.out.println(currentDate);
    }
}
