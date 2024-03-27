package seedu.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Record {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private LocalDateTime dateTime;

    private double speed;
    private double accuracy;

    public Record(LocalDateTime dateTime, double speed, double accuracy) {
        setSpeed(speed);
        setAccuracy(accuracy);
        setDateTime(dateTime);
    }

    String writeLine() {
        return getDateTime().format(formatter) + " " + getSpeed() + " " + getAccuracy();
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
