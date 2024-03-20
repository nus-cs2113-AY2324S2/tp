package seedu.stockpal.commands;

import seedu.stockpal.exceptions.StockPalException;

import static seedu.stockpal.common.Messages.HORIZONTAL_LINE;
import static seedu.stockpal.common.Messages.LINE_SEPARATOR;
import static seedu.stockpal.ui.Ui.printToScreen;

public class HelpCommand extends Command {
    public static final String COMMAND_KEYWORD = "help";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD + ": ";

    @Override
    public void execute() throws StockPalException {
        String formattedText = HORIZONTAL_LINE
                + LINE_SEPARATOR + NewCommand.COMMAND_USAGE
                + LINE_SEPARATOR + LINE_SEPARATOR + EditCommand.COMMAND_USAGE
                + LINE_SEPARATOR + LINE_SEPARATOR + DeleteCommand.COMMAND_USAGE
                + LINE_SEPARATOR + LINE_SEPARATOR + InflowCommand.COMMAND_USAGE
                + LINE_SEPARATOR + LINE_SEPARATOR + OutflowCommand.COMMAND_USAGE
                + LINE_SEPARATOR + LINE_SEPARATOR + ListCommand.COMMAND_USAGE
                + LINE_SEPARATOR + LINE_SEPARATOR + ExitCommand.COMMAND_USAGE
                + LINE_SEPARATOR + HORIZONTAL_LINE;

        printToScreen(formattedText);
    }
}
