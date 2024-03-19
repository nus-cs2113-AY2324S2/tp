package workouts;
import java.time.LocalDate;
import utility.Parser;

public abstract class Workout {
    protected LocalDate date = null;

    public Workout(String stringDate) {
        this.date = Parser.parseDate(stringDate);
    }

    // overloaded constructor for optional date parameter
    public Workout() {
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString(){
        return getDate().toString();
    }





}
