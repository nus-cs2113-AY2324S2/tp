package bookmarked.command;

import bookmarked.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {

    }

    @Override
    public void handleCommand() {
        Ui.exitProgramme();
    }
}
