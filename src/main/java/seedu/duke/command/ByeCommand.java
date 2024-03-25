package seedu.duke.command;

import static seedu.duke.ui.Ui.printExit;

public class ByeCommand extends Command {
    @Override
    public void execute(String userInput) {
        try {
            printExit();
        } catch (Exception e) {
            // In case there is an unexpected issue within printExit().
            System.err.println("An error occurred while attempting to exit: " + e.getMessage());
        } finally {
            // Ensures that the application will close even if there is an exception.
            System.exit(0);
        }
    }
}
