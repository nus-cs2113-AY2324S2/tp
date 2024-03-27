package activeedge.userdetails;

import java.time.LocalDateTime;


public class LogBMI extends UserDetails {

    private LocalDateTime dateTime;

    public LogBMI(Integer value, LocalDateTime dateTime) {
        super(value);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "BMI " + getValue() + " (Recorded on: " + dateTime + ")";
    }
}

