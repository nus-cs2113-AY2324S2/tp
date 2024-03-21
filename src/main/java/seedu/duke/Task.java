package seedu.duke;

import java.time.LocalTime;

public class Task {
    protected String description;
    protected String day;
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected String type;

    public Task(String description, String day, String from, String to, String type) {
        this.description = description;
        this.day = day;
        String fromHour = from.split(":")[0];
        String fromMinute = from.split(":")[1];
        String toHour = to.split(":")[0];
        String toMinute = to.split(":")[1];
        String formattedFrom = formatDates(fromHour) + ":" + formatDates(fromMinute);
        String formattedTo = formatDates(toHour) + ":" + formatDates(toMinute);
        this.startTime = LocalTime.parse(formattedFrom);
        this.endTime = LocalTime.parse(formattedTo);
        this.type = type;
    }
    public LocalTime getStartTime() {
        return startTime;
    }


    public LocalTime getEndTime() {
        return endTime;
    }
    public String getType(){
        return type;
    }


    private String formatDates(String time) {
        return time.length() == 1 ? "0" + time : time;
    }

    @Override
    public String toString() {
        return description + " (" + day + " from " + startTime + " to " + endTime + ")" + "flexibility: " + type;
    }
}
