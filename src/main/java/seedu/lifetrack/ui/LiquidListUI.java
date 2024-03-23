package seedu.lifetrack.ui;

public class LiquidListUI {

    public static final String WHITESPACE = "     ";

    public static void deleteMessage() {
        System.out.println(WHITESPACE + "Successfully delete the liquid record.");
    }

    public static String deleteLogIndexMessage() {
        return "Sorry, this index is invalid. Please enter a positive integer " +
                "within the size of the list.";
    }

    public static String deleteLogNumberMessage() {
        return "Please enter a valid index!";
    }

    public static void addEntryMessage() {
        System.out.println(WHITESPACE + "Beverage has been successfully added");
    }

    public static void emptyListMessage() {
        System.out.println(WHITESPACE + "Your liquid list is empty.");
        System.out.println(WHITESPACE + "Populate your list with more entries :)");
    }

    public static void listHeader() {
        System.out.println(WHITESPACE + "Your liquid List:");
    }
}
