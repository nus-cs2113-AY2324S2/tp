package seedu.duke.command;

import static seedu.duke.ui.Ui.printExit;

public class ByeCommand extends Command {
    @Override
    public void execute(String userInput) {
        printExit();
        System.exit(0);
    }
}
