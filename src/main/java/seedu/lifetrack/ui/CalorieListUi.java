package seedu.lifetrack.ui;

import seedu.lifetrack.Entry;

public class CalorieListUi {

    public static void successfulDeletedMessage(Entry toDelete) {
        System.out.println("\t The following calorie record has been successfully deleted!");
        System.out.println("\t " + toDelete.toString());
    }

    public static void emptyListMessage() {
        System.out.println("\t Your caloric list is empty. Add new entries to populate your list :)");
    }

    public static String deleteLogIndexMessage() {
        return "\t Sorry, this index is invalid. Please enter a positive integer within the size of the list.";
    }
    
    public static String deleteLogNumberMessage() {
        return "\t Please enter a valid index!";
    }

    public static void calorieListHeader() {
        System.out.println("\t Your Caloric List:");
    }

    public static void printNewCalorieEntry(Entry newEntry) {
        System.out.println("\t The following entry has been added to your caloric list!");
        System.out.println("\t " + newEntry.toString());
    }
}
