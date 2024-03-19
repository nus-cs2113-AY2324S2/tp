package utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the parser used for PulsePilot
 */
public class Parser {

    /**
     * Parses and converts String date to a LocalDate variable.
     * @param dateTime
     * @return
     *
     * @throws DateTimeParseException
     */
    public static LocalDate parseDate(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate formattedDate = null;
        try {
            formattedDate = LocalDate.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }
        return formattedDate;
    }
}
