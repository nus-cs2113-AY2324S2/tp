package workouts;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public abstract class Workout {
    protected LocalDate date = null;
    String header = "Type\tTime\t\tDistance\tPace\n";

    public Workout(String stringDate) {
        this.date = parseDate(stringDate);
    }

    // overloaded constructor for optional date parameter
    public Workout() {
    }

    public LocalDate getDate() {
        return date;
    }

    public static LocalDate parseDate(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate formattedDate = null;
        try {
            formattedDate = LocalDate.parse(dateTime, formatter);
        }
        catch (Exception e) {
            System.err.println("Error parsing date: " + e.getMessage());
            System.exit(1);
        }
        return formattedDate;
    }


    @Override
    public String toString(){
        return getDate().toString();
    }





}
