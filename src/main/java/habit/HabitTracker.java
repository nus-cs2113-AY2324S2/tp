package habit;

import exceptions.HabitException;
import ui.Ui;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static storage.habit.HabitTrackerStorage.loadHabitListFromFile;
import static storage.habit.HabitTrackerStorage.saveHabitListToFile;
import static ui.Ui.printMessageWithoutSepNewLine;

/**
 * Represents the habit tracker which stores all habits.
 */
public class HabitTracker {
    private static ArrayList<Habit> habitList = new ArrayList<>();

    public HabitTracker() {
        try {
            habitList = loadHabitListFromFile();
        } catch (HabitException e) {
            Ui.printMessageWithSepNewLine(e.getMessage());
        }
    }

    /**
     * Adds a new habit into habitList.
     *
     * @param newHabit The habit to be added.
     */
    public void addHabit (Habit newHabit) {
        assert habitList != null : "habitList should not be null";
        habitList.add(newHabit);
        String addHabitMessage = "Great! You have added a new habit:\n";
        addHabitMessage += "  '" + newHabit.getDescription() + "' was successfully added!";
        Ui.printMessageWithSepNewLine(addHabitMessage);

        saveHabitListToFile(habitList);
    }

    /**
     * List out all habits in habitList.
     */
    public void listHabits() {
        String listHabitsMessage = "Here is the list of all your habits!\n";
        if (habitList.isEmpty()) {
            listHabitsMessage += "  <you currently have no habits, add one now!>\n";
        }
        for (int i = 0; i < habitList.size(); i++) {
            Habit habit = habitList.get(i);
            listHabitsMessage += "  " + (i + 1) + "." + habit + "\n";
        }
        printMessageWithoutSepNewLine(listHabitsMessage);
    }

    public static boolean isValidHabitID(int habitID) {
        return habitID > 0 && habitID <= habitList.size();
    }

    /**
     * Update the habit count for a habit.
     *
     * @param habitID The habitID to be updated.
     * @param updatedCount The count to be added to the existing habit count.
     * @throws HabitException If an invalid habit ID is provided.
     */
    public void updateHabitCount(int habitID, String updatedCount) throws HabitException {
        if (!isValidHabitID(habitID)) {
            throw new HabitException("Please provide a valid habit ID.");
        }

        Habit habit = habitList.get(habitID - 1);
        int changeInCount = habit.updateCount(updatedCount);

        String updateHabitCountMessage = "";

        if (changeInCount > 0) {
            updateHabitCountMessage += "Good Job! You have completed your habit!\n";
        }

        updateHabitCountMessage += "The count for your habit has been updated:\n";
        updateHabitCountMessage += "  " + habitID + ". " + habit;
        Ui.printMessageWithSepNewLine(updateHabitCountMessage);

        saveHabitListToFile(habitList);
    }

    public static int getNumberOfHabits() {
        return habitList.size();
    }

    public void deleteHabit(int habitID) throws HabitException {
        if (!isValidHabitID(habitID)) {
            throw new HabitException("Please provide a valid habit ID.");
        }

        String deleteHabitMessage = "Got it! I've removed this habit:\n";
        deleteHabitMessage += "  " + habitList.get(habitID - 1) + "\n";
        habitList.remove(habitID - 1);
        deleteHabitMessage += "Now you have " + habitList.size() + " habits left in the list.";
        Ui.printMessageWithSepNewLine(deleteHabitMessage);

        saveHabitListToFile(habitList);
    }

    public void setPriorityLevel(int habitID, String priority) throws HabitException {
        if (!isValidHabitID(habitID)) {
            throw new HabitException("Please provide a valid habit ID.");
        }

        Habit habit = habitList.get(habitID - 1);
        habit.setPriority(priority);

        String setPriorityLevelMessage = "";
        setPriorityLevelMessage += "The priority for your habit has been updated:\n";
        setPriorityLevelMessage += "  " + habitID + ". " + habit;
        Ui.printMessageWithSepNewLine(setPriorityLevelMessage);

        saveHabitListToFile(habitList);
    }

    public void sortHabits() throws HabitException {
        try {
            // Define a custom comparator to sort habits based on their priority
            Comparator<Habit> habitComparator = Comparator.comparing(habit -> {
                switch (habit.getPriority()) {
                case HIGH:
                    return 0;
                case MED:
                    return 1;
                case LOW:
                    return 2;
                default:
                    return 3; // Handles any unexpected case
                }
            });

            // Sort the habitList using the custom comparator
            Collections.sort(habitList, habitComparator);
        } catch (Exception e) {
            throw new HabitException(e.getMessage());
        }

        Ui.printMessageWithSepNewLine("Habits have been sorted according to priority.");
        saveHabitListToFile(habitList);
    }

    public void clearHabits() {
        habitList.clear();
        saveHabitListToFile(habitList);
    }
}
