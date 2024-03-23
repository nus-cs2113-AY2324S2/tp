package seedu.stockpal.commands;

import seedu.stockpal.common.Messages;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.stockpal.common.Messages.HORIZONTAL_LINE;
import static seedu.stockpal.common.Messages.LINE_SEPARATOR;
import static seedu.stockpal.common.Messages.SINGLE_SPACE;
import static seedu.stockpal.ui.Ui.printToScreenWithoutNewlineAtEnd;

public class HelpCommand extends Command {
    public static final String COMMAND_KEYWORD = "help";
    public static final String COMMAND_DESCRIPTION = "List all available commands supported by Stockpal.";
    public static final String COMMAND_USAGE = "help";
    public static final String[] COMMAND_FLAGS = {};
    public static final String[] COMMAND_FLAG_DESCRIPTIONS = {};

    protected static Logger logger = Logger.getLogger(HelpCommand.class.getName());

    private static final String COMMAND_KEYWORD_PREFIX = "Command: ";
    private static final String DESCRIPTION_PREFIX = "Description: ";
    private static final String USAGE_PREFIX = "Usage: ";
    private static final String OPTIONS_PREFIX = "Options:" + LINE_SEPARATOR;
    private static final int FLAG_COL_WIDTH = 22;

    @Override
    public void execute() throws StockPalException {
        String formattedText = HORIZONTAL_LINE + LINE_SEPARATOR
                + newCommandUsage() + editCommandUsage() + deleteCommandUsage()
                + inflowCommandUsage() + outflowCommandUsage() + findCommandUsage()
                + listCommandUsage() + exitCommandUsage() + helpCommandUsage();
        printToScreenWithoutNewlineAtEnd(formattedText);
        logger.log(Level.INFO, Messages.LOG_PRINT_HELP_PAGE);
    }

    /**
     * Pads text with spaces until a specified length.
     *
     * @param text The text
     * @param width The length of the text after adding padding
     * @return The text padded with spaces until a specified length
     */
    private String padUntilWidth(String text, int width) {
        String paddedText = text;
        int amountToPad = width - text.length();
        if (amountToPad > 0) {
            paddedText += SINGLE_SPACE.repeat(amountToPad);
        }
        assert paddedText.length() == width;
        return paddedText;
    }

    /**
     * Formats the flag options and flag descriptions for display.
     *
     * @param flags List of flags for the command
     * @param flagDescriptions List of description for the flags of the command
     * @return
     */
    private String formatCommandOptions(String[] flags, String[] flagDescriptions) {
        if (flags.length == 0) {
            return Messages.EMPTY_STRING;
        }

        String commandOptions = OPTIONS_PREFIX;

        for (int i = 0; i < flags.length; i++) {
            commandOptions += padUntilWidth(flags[i], FLAG_COL_WIDTH);
            commandOptions += Ui.wrapTextWithIndentation(flagDescriptions[i], FLAG_COL_WIDTH);
            commandOptions += LINE_SEPARATOR;
        }

        return commandOptions;
    }

    /**
     * Formats all details of the command.
     *
     * @param keyword Command keyword
     * @param description Description of the command
     * @param usage Format of the command
     * @param flags List of flags that the command accepts
     * @param flagDescriptions List of description for the flags of the command
     * @return
     */
    private String formatCommandDetails(String keyword, String description, String usage
            , String[] flags, String[] flagDescriptions) {
        String command = COMMAND_KEYWORD_PREFIX + keyword + LINE_SEPARATOR;
        String commandDescription = DESCRIPTION_PREFIX
                + Ui.wrapTextWithIndentation(description, DESCRIPTION_PREFIX.length()) + LINE_SEPARATOR;
        String commandUsage = USAGE_PREFIX + usage + LINE_SEPARATOR;
        String commandOptions = formatCommandOptions(flags, flagDescriptions);

        String commandDetails = command + LINE_SEPARATOR
                + commandDescription + LINE_SEPARATOR
                + commandUsage + LINE_SEPARATOR
                + commandOptions
                + HORIZONTAL_LINE + LINE_SEPARATOR;

        assert !commandDetails.isEmpty();
        return commandDetails;
    }

    private String newCommandUsage() {
        return formatCommandDetails(NewCommand.COMMAND_KEYWORD, NewCommand.COMMAND_DESCRIPTION
                , NewCommand.COMMAND_USAGE, NewCommand.COMMAND_FLAGS, NewCommand.COMMAND_FLAG_DESCRIPTIONS);
    }

    private String editCommandUsage() {
        return formatCommandDetails(EditCommand.COMMAND_KEYWORD, EditCommand.COMMAND_DESCRIPTION
                , EditCommand.COMMAND_USAGE, EditCommand.COMMAND_FLAGS, EditCommand.COMMAND_FLAG_DESCRIPTIONS);
    }

    private String deleteCommandUsage() {
        return formatCommandDetails(DeleteCommand.COMMAND_KEYWORD, DeleteCommand.COMMAND_DESCRIPTION
                , DeleteCommand.COMMAND_USAGE, DeleteCommand.COMMAND_FLAGS, DeleteCommand.COMMAND_FLAG_DESCRIPTIONS);
    }

    private String inflowCommandUsage() {
        return formatCommandDetails(InflowCommand.COMMAND_KEYWORD, InflowCommand.COMMAND_DESCRIPTION
                , InflowCommand.COMMAND_USAGE, InflowCommand.COMMAND_FLAGS, InflowCommand.COMMAND_FLAG_DESCRIPTIONS);
    }

    private String outflowCommandUsage() {
        return formatCommandDetails(OutflowCommand.COMMAND_KEYWORD, OutflowCommand.COMMAND_DESCRIPTION
                , OutflowCommand.COMMAND_USAGE, OutflowCommand.COMMAND_FLAGS, OutflowCommand.COMMAND_FLAG_DESCRIPTIONS);
    }

    private String findCommandUsage() {
        return formatCommandDetails(FindCommand.COMMAND_KEYWORD, FindCommand.COMMAND_DESCRIPTION
                , FindCommand.COMMAND_USAGE, FindCommand.COMMAND_FLAGS, FindCommand.COMMAND_FLAG_DESCRIPTIONS);
    }

    private String listCommandUsage() {
        return formatCommandDetails(ListCommand.COMMAND_KEYWORD, ListCommand.COMMAND_DESCRIPTION
                , ListCommand.COMMAND_USAGE, ListCommand.COMMAND_FLAGS, ListCommand.COMMAND_FLAG_DESCRIPTIONS);
    }

    private String exitCommandUsage() {
        return formatCommandDetails(ExitCommand.COMMAND_KEYWORD, ExitCommand.COMMAND_DESCRIPTION
                , ExitCommand.COMMAND_USAGE, ExitCommand.COMMAND_FLAGS, ExitCommand.COMMAND_FLAG_DESCRIPTIONS);
    }

    private String helpCommandUsage() {
        return formatCommandDetails(HelpCommand.COMMAND_KEYWORD, HelpCommand.COMMAND_DESCRIPTION
                , HelpCommand.COMMAND_USAGE, HelpCommand.COMMAND_FLAGS, HelpCommand.COMMAND_FLAG_DESCRIPTIONS);
    }
}
