package exercise;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
public class Workout {
    protected LocalDateTime date = null;

    public Workout(String stringDate) {
        this.date = parseDate(stringDate);
    }

    // overloaded constructor for optional date parameter
    public Workout() {
    }

    public LocalDateTime getDate() {
        return date;
    }

    public static LocalDateTime parseDate(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime formattedDateTime = null;
        try {
            formattedDateTime = LocalDateTime.parse(dateTime, formatter);
        }
        catch (Exception e) {
            System.err.println("Error parsing date: " + e.getMessage());
            System.exit(1);
        }
        return formattedDateTime;
    }

}
