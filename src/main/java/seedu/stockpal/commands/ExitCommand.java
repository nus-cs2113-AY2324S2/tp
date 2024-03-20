package seedu.stockpal.commands;

import seedu.stockpal.ui.Ui;

import static seedu.stockpal.common.Messages.LINE_SEPARATOR;
import static seedu.stockpal.StockPal.exit;

public class ExitCommand extends Command {
    public static final String COMMAND_KEYWORD = "exit";
    public static final String COMMAND_USAGE = Ui.indentTextIfRequired(COMMAND_KEYWORD
            + ": Exits the program."
            + LINE_SEPARATOR
            + "Format: exit");

    @Override
    public void execute() {
        exit();
    }
}
