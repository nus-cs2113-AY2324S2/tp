package seedu.stockpal.commands;

import seedu.stockpal.common.Messages;
import seedu.stockpal.ui.Ui;

import static seedu.stockpal.ui.Ui.printGoodbyeMessage;

public class ExitCommand extends Command {
    public static final String COMMAND_KEYWORD = "exit";
    public static final String COMMAND_USAGE = Ui.indentTextIfRequired(COMMAND_KEYWORD
            + ": Exits the program."
            + Messages.LINE_SEPARATOR
            + "Format: exit");

    @Override
    public void execute() {
        printGoodbyeMessage();
    }
}
