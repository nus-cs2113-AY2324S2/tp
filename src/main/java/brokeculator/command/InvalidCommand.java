package brokeculator.command;

import brokeculator.frontend.UI;

public class InvalidCommand extends Command {
    private String errorMessage;
    public InvalidCommand() {
        //TODO
    }
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    @Override
    public void execute() {
        // TODO
        UI.print(errorMessage);
    }
}
