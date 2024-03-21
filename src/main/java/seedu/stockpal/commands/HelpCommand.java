package seedu.stockpal.commands;

import seedu.stockpal.common.Messages;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.ui.Ui;

import static seedu.stockpal.common.Messages.HORIZONTAL_LINE;
import static seedu.stockpal.common.Messages.LINE_SEPARATOR;
import static seedu.stockpal.ui.Ui.printToScreen;

//@@author EdmundTangg

public class HelpCommand extends Command {
    public static final String COMMAND_KEYWORD = "help";
    public static final String COMMAND_USAGE = Ui.indentTextIfRequired(COMMAND_KEYWORD
            + ": List all available commands supported by Stockpal."
            + Messages.LINE_SEPARATOR
            + "Format: help"
    );

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
                + LINE_SEPARATOR + LINE_SEPARATOR + FindCommand.COMMAND_USAGE
                + LINE_SEPARATOR + LINE_SEPARATOR + HelpCommand.COMMAND_USAGE
                + LINE_SEPARATOR + HORIZONTAL_LINE;

        printToScreen(formattedText);
    }
}
