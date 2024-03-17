package habit;

import exceptions.HabitException;
import ui.Ui;


import java.util.ArrayList;


public class HabitTracker {
    private static ArrayList<Habit> habitList = new ArrayList<>();

    public HabitTracker() {
    }

    public void addHabit (Habit newHabit) {
        habitList.add(newHabit);
        String addHabitMessage = "Great! You have added a new habit:\n";
        addHabitMessage += "  '" + newHabit.description + "' was successfully added!";
        Ui.printMessageWithSepNewLine(addHabitMessage);
    }

    public void listHabits() {

    }




    public void updateHabitCount(String habitIDString, String updatedCount) throws HabitException {

    }
}
