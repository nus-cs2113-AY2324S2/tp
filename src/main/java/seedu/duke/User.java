package seedu.duke;

public class User {
    private final String name;
    private Timetable timetable = new Timetable();
    public User(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
