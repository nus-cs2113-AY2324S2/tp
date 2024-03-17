package seedu.duke;
import cantvasui.UI;

public class CantVasMain {
    private static UI ui;
    private static ProcessCommand processCommand;

    public CantVasMain() {
        ui = new UI();
        processCommand = new ProcessCommand();
    }

    public void run() {
        UI.printLogo();
        int exit;
        do {
            String command = ui.getUserCommand();
            exit = processCommand.userCommand(command);
        } while (exit != 1);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
       new CantVasMain().run();
    }
}
