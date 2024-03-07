package seedu.duke;

import seedu.duke.ui.Ui;

public class MediTracker {

    private Ui ui;

    public MediTracker() {
        ui = new Ui();
    }

    /**
     * Runs the MediTracker.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String command = ui.readCommand();
            if (command.equals("exit")) {
                isExit = true;
                continue;
            }

            ui.showLine(); // show the divider line ("_________")
            System.out.println(command + "\n");
        }
    }

    public static void main(String[] args) {
        new MediTracker().run();
    }
}
