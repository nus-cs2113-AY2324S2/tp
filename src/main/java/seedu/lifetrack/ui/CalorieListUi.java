package seedu.lifetrack.ui;

import seedu.lifetrack.calories.calorielist.Entry;

public class CalorieListUi {

    public static void successfulDeletedMessage(Entry toDelete) {
        System.out.println("\t The following calorie record has been successfully deleted!");
        System.out.println("\t " + Entry.toString(toDelete));
    }

    public static void emptyListMessage() {
        System.out.println("\t Your caloric list is empty. Add new entries to populate your list :)");
    }

    public static void calorieListHeader() {
        System.out.println("\t Your Caloric List:");
    }

    public static void printNewCalorieEntry(Entry newEntry) {
        System.out.println("\t The following entry has been added to your caloric list!");
        System.out.println("\t " + Entry.toString(newEntry));

    }
}
