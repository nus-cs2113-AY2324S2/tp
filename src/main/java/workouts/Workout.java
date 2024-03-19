package workouts;
import java.time.LocalDate;
import utility.Parser;

/**
 * Represents a Workout object for PulsePilot.
 */
public abstract class Workout {
    protected LocalDate date = null;

    /**
     * Overloaded constructor that uses the optional date parameter from user input.
     *
     * @param stringDate String representing the date of the workout.
     */
    public Workout(String stringDate) {
        this.date = Parser.parseDate(stringDate);
    }

    /**
     * Constructor that builds a new Workout object.
     */
    public Workout() {
    }

    /**
     * Returns the date of the workout.
     *
     * @return LocalDate variable representing the date of the workout.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Retrieves the string representation of a Workout object.
     *
     * @return A formatted string representing a Workout object.
     */
    @Override
    public String toString(){
        return getDate().toString();
    }
}
