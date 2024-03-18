package bookmarked.command;

import bookmarked.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {

    }

    @Override
    public void handleCommand() {
        Ui.printHelpMessage();
    }
}
