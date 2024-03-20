package seedu.duke;
import java.time.LocalTime;

public class Task {
    protected String description;
    protected String day;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Task(String description, String day, String from, String to){
        this.description = description;
        this.day = day;
        this.startTime = LocalTime.parse(from);
        this.endTime = LocalTime.parse(to);
    }
    @Override
    public String toString() {
        return description + " (" + day + " from " + startTime + " to " + endTime + ")";
    }
}
