package storage.habit;

import habit.Habit;
import habit.Priority;
import storage.Storage;

import java.util.ArrayList;

public class HabitTrackerStorage {
    private static final String HABIT_FILE_PATH = "data/habits.txt";

    public static void saveHabitListToFile(ArrayList<Habit> habitList) {
        ArrayList<String> data = new ArrayList<>();

        for (Habit habit : habitList) {
            data.add(habit.getDescription() + ", " + habit.getHabitCount()
                    + ", " + habit.getPriority());
        }

        Storage.saveTasksToFile(HABIT_FILE_PATH, data);
    }

    public static ArrayList<Habit> loadHabitListFromFile() {
        ArrayList<Habit> habitList = new ArrayList<>();
        ArrayList<String> data = Storage.loadDataFromFile(HABIT_FILE_PATH);

        for (String line : data) {
            String[] parts = line.split(", ");

            if (parts.length == 3) {
                String description = parts[0];
                int habitCount = Integer.parseInt(parts[1]);
                Priority priority = Priority.valueOf(parts[2]);
                Habit habit = new Habit(description, habitCount, priority);
                habitList.add(habit);
            }
        }

        return habitList;
    }

}
