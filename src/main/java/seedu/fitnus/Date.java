package seedu.fitnus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {
    // reference: https://www.javatpoint.com/java-get-current-date
    private static java.sql.Date currentDate;

    public Date() {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        this.currentDate = date;
    }

    public static java.sql.Date getCurrentDate() {
        return currentDate;
    }
}
