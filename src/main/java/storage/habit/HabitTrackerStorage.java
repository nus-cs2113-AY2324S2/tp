package storage.habit;

import exceptions.HabitException;
import habit.Habit;
import habit.Priority;
import storage.Storage;

import java.util.ArrayList;

public class HabitTrackerStorage {
    private static final String HABIT_FILE_PATH = "data/habits.txt";
    private static final String COMMA_SEPARATION = ", ";
    private static final int DATA_SIZE = 3;

    public static void saveHabitListToFile(ArrayList<Habit> habitList) {
        ArrayList<String> data = new ArrayList<>();

        for (Habit habit : habitList) {
            data.add(habit.getDescription() + COMMA_SEPARATION + habit.getHabitCount()
                    + COMMA_SEPARATION + habit.getPriority());
        }

        Storage.saveTasksToFile(HABIT_FILE_PATH, data);
    }

    public static ArrayList<Habit> loadHabitListFromFile() throws HabitException {
        ArrayList<Habit> habitList = new ArrayList<>();
        ArrayList<String> data = Storage.loadDataFromFile(HABIT_FILE_PATH);

        for (String line : data) {
            String[] parts = line.split(", ");

            if (parts.length != DATA_SIZE) {
                throw new HabitException("Error in loading habit tracker data from local storage");
            }

            String description = parts[0];
            int habitCount = Integer.parseInt(parts[1]);
            Priority priority = Priority.valueOf(parts[2]);
            Habit habit = new Habit(description, habitCount, priority);
            habitList.add(habit);
        }

        return habitList;
    }

}
