package supertracker.command;

import supertracker.ui.Ui;

public class QuitCommand implements Command {
    @Override
    public void execute() {
        Ui.sayGoodbye();
    }

    @Override
    public boolean isQuit() {
        return true;
    }
}
