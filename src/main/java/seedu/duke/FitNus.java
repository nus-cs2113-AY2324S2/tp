package seedu.duke;

import seedu.duke.user.User;

public class FitNus {

    private static Ui ui;

    public FitNus() {
        ui = new Ui();
    }

    /** Runs the program until termination.  */
    public void run() {
        ui.printWelcomeMessage();
        while (!ui.isExit) {
            ui.readCommand();
        }
    }

    public static void main(String[] args) {
        new FitNus().run();
    }


}