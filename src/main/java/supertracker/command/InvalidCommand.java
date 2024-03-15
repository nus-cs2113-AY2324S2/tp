package supertracker.command;

import supertracker.ui.Ui;

public class InvalidCommand implements Command {
    @Override
    public void execute() {
        Ui.printInvalidCommand();
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
