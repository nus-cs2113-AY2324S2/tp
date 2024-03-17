package habit;

import exceptions.HabitException;
import ui.Ui;


import java.util.ArrayList;

import static ui.Ui.printMessageWithoutSepNewLine;


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




    public void updateHabitCount(String habitIDString, String updatedCount) throws HabitException {

    }
}
