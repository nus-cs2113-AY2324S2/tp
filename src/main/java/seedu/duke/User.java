package seedu.duke;
public class User {
    private final String name;
    public Timetable timetable = new Timetable();
    public User(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void viewTimetable() {
        for (int i = 0; i < 7; i++) {
            timetable.printTasksOfTheDay(i);
        }
    }
}
