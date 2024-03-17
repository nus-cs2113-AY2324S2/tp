package seedu.duke;
import cantvasui.UI;

public class CantVasMain {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        UI ui = new UI();
        ui.printLogo();
        ExpenditureList expenseList = new ExpenditureList();
        ProcessCommand processCommand = new ProcessCommand(expenseList);
        processCommand.userCommand();
    }
}
