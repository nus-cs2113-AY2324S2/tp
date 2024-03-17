package seedu.duke;
import cantvasui.UI;

public class CantVasMain {
    private static UI ui;
    private static ProcessCommand processCommand;

    public CantVasMain() {
        ui = new UI();
        ExpenditureList expenseList = new ExpenditureList();
        processCommand = new ProcessCommand(expenseList);
    }

    public void run() {
        UI.printLogo();
        boolean exit;
        do {
            String command = ui.getUserCommand();
            exit = processCommand.userCommand(command);
        } while (!exit);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
       new CantVasMain().run();
    }
}
