package habit;

import exceptions.HabitException;
import ui.Ui;


import java.util.ArrayList;

import static ui.Ui.printMessageWithoutSepNewLine;

/**
 * Represents the habit tracker which stores all habits.
 */
public class HabitTracker {
    private static ArrayList<Habit> habitList = new ArrayList<>();

    public HabitTracker() {
    }

    /**
     * Adds a new habit into habitList.
     *
     * @param newHabit The habit to be added.
     */
    public void addHabit (Habit newHabit) {
        habitList.add(newHabit);
        String addHabitMessage = "Great! You have added a new habit:\n";
        addHabitMessage += "  '" + newHabit.getDescription() + "' was successfully added!";
        Ui.printMessageWithSepNewLine(addHabitMessage);
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
            listHabitsMessage += "  " + (i + 1) + ". " + habit + "\n";
        }
        printMessageWithoutSepNewLine(listHabitsMessage);
    }

    public static boolean isValidHabitID(int habitID) {
        return habitID > 0 && habitID <= habitList.size();
    }

    /**
     * Update the habit count for a habit.
     *
     * @param habitIDString The string of a habitID to be updated.
     * @param updatedCount The count to be added to the existing habit count.
     * @throws HabitException If an invalid habit ID is provided.
     */
    public void updateHabitCount(String habitIDString, String updatedCount) throws HabitException {
        int habitID;
        try {
            habitID = Integer.parseInt(habitIDString);
            if (!isValidHabitID(habitID)) {
                throw new HabitException("Please provide a valid habit ID.");
            }
        } catch (NumberFormatException e) {
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
    }

    public static int getNumberOfHabits() {
        return habitList.size();
    }
}
