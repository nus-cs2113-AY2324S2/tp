package seedu.duke;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Timetable {
    private Map<String, ArrayList<Task>> weeklyTasks; // Map to store tasks for each day
    protected static final String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public Map<String, ArrayList<Task>> getWeeklyTasks() {
        return weeklyTasks;
    }

    public Timetable() {
        weeklyTasks = new HashMap<>();
        //Initialize the map with empty lists for each day
        for (String day: days) {
            weeklyTasks.put(day, new ArrayList<>());
        }
    }

    public void addUserTask(String dayOfWeek, Task task){
        try {
            checkDay(dayOfWeek);
            String capitalizedDay = dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1);
            weeklyTasks.get(capitalizedDay).add(task);
            System.out.println("Added: " + task + " to " + capitalizedDay);
        } catch (InvalidDayException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUserTask(String dayOfWeek, int index){
        try {
            checkDay(dayOfWeek);
            //check if index is a valid number within a day's tasklist
            if (index >= 0 && index < weeklyTasks.get(dayOfWeek).size()) {
                Task taskDeleted = weeklyTasks.get(dayOfWeek).get(index);
                weeklyTasks.get(dayOfWeek).remove(index);
                System.out.println("Task " + taskDeleted.description + "is deleted from " + dayOfWeek);
            }
            else {
                System.out.println("Invalid task index. Please try again.");
            }
        } catch (InvalidDayException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printTasksOfTheDay(String day) {
        String capitalizedDay = day.substring(0, 1).toUpperCase() + day.substring(1);
        if (weeklyTasks.get(capitalizedDay) == null) {
            System.out.println("NO TASK FOR " + day);
        }
        System.out.println(capitalizedDay + ":");
        for (Task task : weeklyTasks.get(capitalizedDay)) {
            System.out.println(task.toString());
        }
    }

    public static void checkDay(String input) throws InvalidDayException {
        boolean validDay = false;
        for (String day : days) {
            if (day.equalsIgnoreCase(input)) {
                validDay = true;
                 break;
            }
        }
        if (!validDay) {
            throw new InvalidDayException();
        }
    }
}
