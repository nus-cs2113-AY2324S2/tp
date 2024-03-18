package seedu.duke;

public class Task {
    protected String description;
    protected String day;
    protected String from;
    protected String to;

    public Task(String description, String day, String from, String to){
        this.description = description;
        this.day = day;
        this.from = from;
        this.to = to;
    }

    public String getDay() {
        return day;
    }
    @Override
    public String toString() {
        return description + " (" + day + " from " + from + " to " + to + ")";
    }
}
