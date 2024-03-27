package activeedge.userdetails;
import java.time.LocalDateTime;

public class LogHeight extends UserDetails {

    private LocalDateTime dateTime;

    public LogHeight(Integer value, LocalDateTime dateTime) {
        super(value);
        this.dateTime = dateTime;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String toString() {
        return "Height " + this.getValue() + " cm" + " (Recorded on: " + dateTime + ")";
    }

}
