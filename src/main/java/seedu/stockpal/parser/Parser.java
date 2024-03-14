package seedu.stockpal.parser;

import seedu.stockpal.commands.NewCommand;
import seedu.stockpal.commands.EditCommand;
import seedu.stockpal.commands.DeleteCommand;
import seedu.stockpal.commands.InflowCommand;
import seedu.stockpal.commands.OutflowCommand;
import seedu.stockpal.commands.ListCommand;
import seedu.stockpal.commands.ExitCommand;
import seedu.stockpal.commands.HelpCommand;

import seedu.stockpal.exceptions.InvalidCommandException;
import seedu.stockpal.exceptions.InvalidFormatException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Parser {
    public static final String DIVIDER = " ";
    public static final Pattern NEW_COMMAND_PATTERN =
            Pattern.compile("(new) n/(\\S+) q/(\\d+)(?: p/(\\d+\\.\\d{2}))?(?: d/(.{1,100}))?");
    public static final Pattern EDIT_COMMAND_PATTERN =
            Pattern.compile("(edit) (\\d+)(?: n/(\\S+))?(?: q/(\\d+))?(?: p/(\\d+\\.\\d{2}))?(?: d/(.{1,100}))?");
    public static final Pattern DELETE_COMMAND_PATTERN = Pattern.compile("(delete) (\\d+)");
    public static final Pattern INFLOW_COMMAND_PATTERN = Pattern.compile("(inflow) (\\d+) a/(\\d+)");
    public static final Pattern OUTFLOW_COMMAND_PATTERN = Pattern.compile("(outflow) (\\d+) a/(\\d+)");
    public static final int NUM_OF_NEW_COMMAND_ARGUMENTS = 5;
    public static final int NUM_OF_EDIT_COMMAND_ARGUMENTS = 6;
    public static final int NUM_OF_DELETE_COMMAND_ARGUMENTS = 2;
    public static final int NUM_OF_INFLOW_COMMAND_ARGUMENTS = 3;
    public static final int NUM_OF_OUTFLOW_COMMAND_ARGUMENTS = 3;
    public static final int START_INDEX = 0;

    private Parser() {
    }

    public static ArrayList<String> parseCommand(String input) throws InvalidFormatException, InvalidCommandException {
        ArrayList<String> parsed;
        input = input.strip();
        String command = getCommandFromInput(input);

        switch (command) {
        case HelpCommand.COMMAND_KEYWORD:
            // Fallthrough
        case ListCommand.COMMAND_KEYWORD:
            // Fallthrough
        case ExitCommand.COMMAND_KEYWORD:
            // Case where command is single word
            parsed = new ArrayList<>();
            parsed.add(command);
            break;

        case NewCommand.COMMAND_KEYWORD:
            parsed = validateAndParseCommand(input, NEW_COMMAND_PATTERN, NUM_OF_NEW_COMMAND_ARGUMENTS);
            break;

        case EditCommand.COMMAND_KEYWORD:
            parsed = validateAndParseCommand(input, EDIT_COMMAND_PATTERN, NUM_OF_EDIT_COMMAND_ARGUMENTS);
            break;

        case DeleteCommand.COMMAND_KEYWORD:
            parsed = validateAndParseCommand(input, DELETE_COMMAND_PATTERN, NUM_OF_DELETE_COMMAND_ARGUMENTS);
            break;

        case InflowCommand.COMMAND_KEYWORD:
            parsed = validateAndParseCommand(input, INFLOW_COMMAND_PATTERN, NUM_OF_INFLOW_COMMAND_ARGUMENTS);
            break;

        case OutflowCommand.COMMAND_KEYWORD:
            parsed = validateAndParseCommand(input, OUTFLOW_COMMAND_PATTERN, NUM_OF_OUTFLOW_COMMAND_ARGUMENTS);
            break;

        default:
            throw new InvalidCommandException();
        }

        return parsed;
    }

    private static String getCommandFromInput(String input) {
        if (!input.contains(DIVIDER)) {
            return input;
        }
        return input.substring(START_INDEX, input.indexOf(DIVIDER));
    }

    private static ArrayList<String> validateAndParseCommand(String input, Pattern pattern, int numOfArgs)
            throws InvalidFormatException {
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new InvalidFormatException();
        }

        ArrayList<String> parsed = new ArrayList<>();
        for (int i = 1; i < numOfArgs + 1; i++) {
            parsed.add(matcher.group(i));
        }
        return parsed;
    }


}
