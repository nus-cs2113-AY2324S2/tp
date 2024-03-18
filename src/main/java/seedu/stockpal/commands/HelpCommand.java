package seedu.stockpal.commands;

import seedu.stockpal.exceptions.StockPalException;

public class HelpCommand extends Command {
    public static final String COMMAND_KEYWORD = "help";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD + ": ";

    @Override
    public void execute() throws StockPalException {
        super.execute();
    }
}
