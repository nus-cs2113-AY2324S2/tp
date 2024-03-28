package seedu.duke.command;

import seedu.duke.ui.Ui;

public class InvalidCommand extends Command {

    private final String errorMessage;

    public InvalidCommand() {
        this.errorMessage = "Invalid Command";
    }

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage != null ? errorMessage : "Invalid Command";
    }

    @Override
    public void execute(String userInput) {
        if (!errorMessage.isEmpty()) {
            Ui.printMessage(errorMessage);
        }
    }
}
