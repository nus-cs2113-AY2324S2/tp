package seedu.duke;
public class User {
    public Timetable timetable = new Timetable();
    private final String name;

    public User(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void viewTimetable() {
        for (String day : Timetable.days) {
            timetable.printTasksOfTheDay(day);
        }
    }
}
