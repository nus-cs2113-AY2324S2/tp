package date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {
    private static final DateTimeFormatter FORMAT =  DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate convertStringToDate(String s) {
        return LocalDate.parse(s , FORMAT);
    }

    public static String convertDateToString(LocalDate date) {
        return date.format(FORMAT);
    }
}
