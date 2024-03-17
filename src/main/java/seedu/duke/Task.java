package seedu.duke;

public class Task {
    protected String description;
    protected String from;
    protected String to;

    public Task(String description, String from, String to){
        this.description = description;
        this.from = from;
        this.to = to;
    }
}
