package seedu.duke;

import java.util.ArrayList;

public class Timetable {
    //todo
    private ArrayList<String>[] daysOfWeek;
    public Timetable() {
        daysOfWeek = new ArrayList[7];
        initializeTimetable();
    }

    private void initializeTimetable() {
        for (int i = 0; i < 7; i++) {
            daysOfWeek[i] = new ArrayList<>();
        }
    }
    protected String name;
}
