package seedu.fitnus;

import java.text.SimpleDateFormat;

public class Date {
    // reference: https://www.javatpoint.com/java-get-current-date
    private static String currentDate;

    public Date() {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = DATE_FORMAT.format(date);

        this.currentDate = formattedDate;
    }

    public static String getDate() {
        return currentDate;
    }
}
