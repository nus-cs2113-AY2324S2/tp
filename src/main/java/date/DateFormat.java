package date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a class to convert string to date and vice versa.
 * Ensures that only one type of format for date is to be used.
 */
public class DateFormat {
    private static final DateTimeFormatter FORMAT =  DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate convertStringToDate(String s) {
        return LocalDate.parse(s , FORMAT);
    }

    public static String convertDateToString(LocalDate date) {
        return date.format(FORMAT);
    }
}
