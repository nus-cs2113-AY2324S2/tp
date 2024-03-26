package activeedge.userdetails;
import java.time.LocalDateTime;

public class LogWeight extends UserDetails {

    private LocalDateTime dateTime;

    public LogWeight(Integer value, LocalDateTime dateTime) {
        super(value);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String toString() {
        return "Weight " + this.getValue() + " kg" + " (Recorded on: " + dateTime + ")";
    }
}
