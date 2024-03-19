package seedu.duke;

import static storage.Storage.readExpenditureFile;

public class CantVasMain {
    public static UI ui;
    private static ExpenditureList expenseList;
    private static ProcessCommand processCommand;

    public CantVasMain() {
        ui = new UI();
        expenseList = readExpenditureFile();
        processCommand = new ProcessCommand();
    }

    public void run() {
        UI.printLogo();
        UI.printHelpMessage();
        boolean exit;
        do {
            String command = ui.getUserCommand();
            exit = processCommand.userCommand(command, expenseList);
        } while (!exit);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new CantVasMain().run();
    }
}
