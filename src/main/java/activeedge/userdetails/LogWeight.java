package activeedge.userdetails;

import java.time.LocalDateTime;

/**
 * The LogWeight class represents a log of weight details for a user.
 * It extends the UserDetails class and includes a timestamp for when the weight was recorded.
 */
public class LogWeight extends UserDetails {

    /**
     * The date and time when the weight was recorded.
     */
    private LocalDateTime dateTime;

    /**
     * Constructs a LogWeight object with the specified weight value and timestamp.
     *
     * @param value    The weight value.
     * @param dateTime The date and time when the weight was recorded.
     */
    public LogWeight(Integer value, LocalDateTime dateTime) {
        super(value);
        this.dateTime = dateTime;
    }

    /**
     * Gets the date and time when the weight was recorded.
     *
     * @return The date and time when the weight was recorded.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns a string representation of the LogWeight object.
     * This includes the weight value and the timestamp.
     *
     * @return A string representation of the LogWeight object.
     */
    @Override
    public String toString() {
        return "Weight " + this.getValue() + " kg" + " (Recorded on: " + dateTime + ")";
    }
}

