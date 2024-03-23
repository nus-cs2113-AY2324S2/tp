package date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormat {
    private static final DateTimeFormatter FORMAT =  DateTimeFormatter.ofPattern("dd_MM_yyyy");

    public static LocalDateTime convertStringToDate(String s) {
        return LocalDateTime.parse(s , FORMAT);
    }

    public static String convertDateToString(LocalDateTime date) {
        return date.format(FORMAT);
    }
}
