package seedu.duke;

import seedu.duke.exception.ProcessInputException;
import seedu.duke.ui.Ui;

public class Duke {
    public Duke() {
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Formatter.printWelcomeMsg();

        while (Ui.getIsRunning()) {
            Formatter.printGoalBeforeShot(Ui.roundCount);
            try {
                Ui.beginListening();
                Ui.processInput();
                Ui.executeCommand();
            } catch (ProcessInputException e) {
                Formatter.printErrorExecutionFail();
            }
        }
    }
}

