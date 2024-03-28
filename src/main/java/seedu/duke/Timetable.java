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
    public static final String[] DAYS = new String[]
        {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private Map<String, ArrayList<Task>> weeklyTasks; // Map to store tasks for each day

    public Timetable() {
        weeklyTasks = new HashMap<>();
        //Initialize the map with empty lists for each day
        for (String day : DAYS) {
            weeklyTasks.put(day, new ArrayList<>());
        }
    }

    public Map<String, ArrayList<Task>> getWeeklyTasks() {
        return weeklyTasks;
    }

    /**
     * Prints tasks of the day specified.
     *
     * @param day day of the week the task is on.
     */
    public void printTasksOfTheDay(String day) {
        String capitalizedDay = day.substring(0, 1).toUpperCase() + day.substring(1);
        if (weeklyTasks.get(capitalizedDay).isEmpty()) {
            System.out.println("NO TASK FOR " + day);
            return;
        }
        System.out.println(capitalizedDay + ":");
        int count = 1;
        for (Task task : weeklyTasks.get(capitalizedDay)) {
            System.out.println(count + ". " + task.toString());
            count++;
        }
    }

    /**
     * Adds task on dayOfWeek at an index
     *
     * @param dayOfWeek first Timetable.
     * @param task      task to add.
     */
    public void addUserTask(String dayOfWeek, Task task) {
        String capitalizedDay = dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1);
        weeklyTasks.get(capitalizedDay).add(task);
    }

    /**
     * Deletes task on dayOfWeek at an index
     *
     * @param dayOfWeek first Timetable.
     * @param index     index of task within task list
     */
    public void deleteUserTask(String dayOfWeek, int index) {
        String capitalizedDay = dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1);
        //check if index is a valid number within a day's tasklist
        if (index >= 0 && index < weeklyTasks.get(capitalizedDay).size()) {
            Task taskDeleted = weeklyTasks.get(capitalizedDay).get(index);
            weeklyTasks.get(capitalizedDay).remove(index);
            System.out.println("Task " + taskDeleted.description + " is deleted from " + dayOfWeek);
            System.out.println("New task list for " + capitalizedDay + ":");
            printTasksOfTheDay(dayOfWeek);
        } else {
            System.out.println("Invalid task index. Please try again.");
        }
    }

    public void changeFlexibleTaskTiming(String dayOfWeek, int index, LocalTime newStartTime, LocalTime newEndTime) {
        assert dayOfWeek != null : "Day of week cannot be null";
        assert newStartTime != null : "New start time cannot be null";
        assert newEndTime != null : "New end time cannot be null";
        Task task = readDay(dayOfWeek, index);
        if (!task.getType().equals("f")) {
            throw new IllegalArgumentException("Task on " + dayOfWeek + " at index " +
                    (index + 1) + " is not flexible, timings cannot be changed.");
        }
        task.setStartTime(newStartTime);
        task.setEndTime(newEndTime);
    }

    public void changeTaskType(String dayOfWeek, int index, String newType) {
        Task task = readDay(dayOfWeek, index);
        task.setType(newType);
    }

    private Task readDay(String dayOfWeek, int index) {
        String capitalizedDay = dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1);
        ArrayList<Task> tasks = weeklyTasks.get(capitalizedDay);
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return tasks.get(index);
    }

    /**
     * Compares and prints overlapping free time between two Timetables.
     *
     * @param table1 first Timetable.
     * @param table2 second Timetable
     * @return Returns a merged timetable comprising both input timetables.
     */
    public static Timetable compareTimetable(Timetable table1, Timetable table2) {
        Timetable mergedTimetable = new Timetable();
        if (table1.equals(table2)) {
            return table1;
        }
        try {
            InputValidator.validateTableExistence(table1);
            InputValidator.validateTableExistence(table2);
            for (String day : Timetable.DAYS) {
                mergedTimetable.weeklyTasks.replace(day, mergeAndSortTasks(table1.getWeeklyTasks().get(day),
                        table2.getWeeklyTasks().get(day)));
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return mergedTimetable;
    }

    public static Timetable compareAllTimetables(UserList userList) {
        Timetable mergedTimetable = new Timetable();
        for (User user : userList.getUsers()) {
            mergedTimetable = compareTimetable(mergedTimetable, user.getTimetable());
        }
        return mergedTimetable;
    }

    private static ArrayList<Task> mergeAndSortTasks(ArrayList<Task> taskList1, ArrayList<Task> taskList2) {
        ArrayList<Task> mergedTasks = new ArrayList<>(taskList1);
        mergedTasks.addAll(taskList2);
        mergedTasks.sort(Comparator.comparing(Task::getStartTime));
        return mergedTasks;
    }

    public static void findOverlappingFreeTime(ArrayList<Task> tasks, String day) {
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
                System.out.println(previousEndTime + " - " + LocalTime.MAX.format(formatter) +
                        ": Overlapping Free Time");
            }
        } else {
            System.out.println("** Whole day is free on " + day);
        }
    }


}
