package seedu.duke;
import java.util.ArrayList;

public class Timetable {
    //todo
    protected ArrayList<ArrayList<Task>> daysOfWeek = new ArrayList<>(7);
    public Timetable() {
        initializeTimetable();
    }
    private void initializeTimetable() {
        for (int i = 0; i < 7; i++) {
            daysOfWeek.add(new ArrayList<>());
        }
    }
    public void addUserTask(int dayOfWeek, Task task){
        assert dayOfWeek >= 0 && dayOfWeek <= 6 : "Invalid day of a week, please try again!";
        daysOfWeek.get(dayOfWeek - 1).add(task);

    }

    public void deleteUserTask(int dayOfWeek, int index){
        assert dayOfWeek >= 0 && dayOfWeek <= 6 : "Invalid day of a week, please try again!";
        assert index >= 0 && index < daysOfWeek.get(dayOfWeek - 1).size() : "Invalid task index, please try again!";

        ArrayList<Task> tasks = daysOfWeek.get(dayOfWeek - 1);
        if (index < tasks.size()){
            Task taskDeleted = tasks.get(index);
            tasks.remove(index);
            System.out.println("Task " + taskDeleted.description + "is deleted from " + dayOfWeek);
        } else{
            System.out.println("Invalid task index. Please try again");
        }
    }

    public void printTasksOfTheDay(int dayOfWeek) {
        String day;
        switch (dayOfWeek) {
        case (0):
            day = "Monday";
            break;
        case (1):
            day = "Tuesday";
            break;
        case (2):
            day = "Wednesday";
            break;
        case (3):
            day = "Thursday";
            break;
        case (4):
            day = "Friday";
            break;
        case (5):
            day = "Saturday";
            break;
        case (6):
            day = "Sunday";
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + dayOfWeek);
        }
        System.out.println(day + ": ");
        for (Task task : daysOfWeek.get(dayOfWeek)) {
            System.out.println(task.toString());
        }
    }
}
