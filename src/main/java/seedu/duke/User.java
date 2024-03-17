package seedu.duke;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private String name;
    private ArrayList<TaskList> taskLists; //THIS COULD BE TIMETABLE
    public Timetable timetable = new Timetable();
    public User(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }



}
