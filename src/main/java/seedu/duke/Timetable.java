package seedu.duke;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the Timetable object consisting of Arraylist of Tasks for each day of the week.
 */
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

    /**
     * Compares and prints overlapping free time between two Timetables.
     *
     * @param table1 first Timetable.
     * @param table2 second Timetable
     *
     * Prints the overlapping free time for each day in the format:
     *      ----------------------
     *      Shared Free Time on [day]
     *      HH:mm - HH:mm: Overlapping Free Time
     */
    public static void compareTimetable(Timetable table1, Timetable table2){
        for (String day : Timetable.days) {
            System.out.println("----------------------\n" +
                    "Shared Free Time on " + day + ":");
            // Merge tasks from both timetables and sort them by start time
            ArrayList<Task> mergedTasks = mergeAndSortTasks(table1.getWeeklyTasks().get(day), table2.getWeeklyTasks().get(day));
            // Calculate overlapping free time intervals then print them
            calculateAndPrintOverlappingFreeTime(mergedTasks, day);
        }
    }

    private static ArrayList<Task> mergeAndSortTasks(ArrayList<Task> taskList1, ArrayList<Task> taskList2) {
        ArrayList<Task> mergedTasks = new ArrayList<>(taskList1);
        mergedTasks.addAll(taskList2);
        mergedTasks.sort(Comparator.comparing(Task::getStartTime));
        return mergedTasks;
    }

    private static void calculateAndPrintOverlappingFreeTime(ArrayList<Task> tasks, String day) {
        if (!tasks.isEmpty()) {
            LocalTime previousEndTime = LocalTime.MIN;
            for (Task task : tasks) {
                if (task.getStartTime().isAfter(previousEndTime)) {
                    System.out.println(previousEndTime + " - " + task.getStartTime() + ": Overlapping Free Time");
                }
                previousEndTime = task.getEndTime();
            }
            if (previousEndTime.isBefore(LocalTime.MAX)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                System.out.println(previousEndTime + " - " + LocalTime.MAX.format(formatter) + ": Overlapping Free Time");
            }
        } else {
            System.out.println("** Whole day is free on " + day);
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
