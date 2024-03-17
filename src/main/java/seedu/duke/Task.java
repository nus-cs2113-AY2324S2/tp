package seedu.duke;

public class Task {
    protected String description;
    protected String date;
    protected String from;
    protected String to;

    public Task(String description, String date, String from, String to){
        this.description = description;
        this.date = date;
        this.from = from;
        this.to = to;
    }
}
