package seedu.lifetrack.ui;

public class SleepListUi {
    public static final String WHITESPACE = "     ";
    public static void addEntryMessage() {
        System.out.println(WHITESPACE + "New sleep record has been successfully added.");
    }
    public static void deleteMessage() {
        System.out.println(WHITESPACE + "Successfully delete the sleep record.");
    }
    public static String deleteLogNumberMessage() {
        return "Please enter a valid index!";
    }
    public static String deleteLogIndexMessage() {
        return "Sorry, this index is invalid. Please enter a positive integer " +
                "within the size of the list.";
    }
    public static void sleepListHeader() {
        System.out.println(WHITESPACE +"Your Sleep List:");
    }
    public static void emptyListMessage() {
        System.out.println(WHITESPACE + "Your sleep list is empty.");
        System.out.println(WHITESPACE + "Populate your list with more entries :)");
    }
}
