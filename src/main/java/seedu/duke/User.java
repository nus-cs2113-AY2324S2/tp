package seedu.duke;

public class User {
    private Timetable timetable;
    private final String name;

    public User(String name) {
        this.name = name;
        this.timetable = new Timetable();
    }

    public String getName() {
        return name;
    }

    public void viewTimetable() {
        for (String day : Timetable.DAYS) {
            timetable.printTasksOfTheDay(day);
        }
    }

    public Timetable getTimetable() {
        return timetable;
    }
}
