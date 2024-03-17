package seedu.duke;
import cantvasui.UI;

public class CantVasMain {
    private static UI ui;

    public CantVasMain() {
        ui = new UI();
    }

    public void run() {
        UI.printLogo();
        ExpenditureList expenseList = new ExpenditureList();
        ProcessCommand processCommand = new ProcessCommand(expenseList);
        processCommand.userCommand();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
       new CantVasMain().run();
    }
}
