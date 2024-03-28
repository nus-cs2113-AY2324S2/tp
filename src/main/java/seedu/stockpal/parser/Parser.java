package seedu.stockpal.parser;

import seedu.stockpal.commands.HelpCommand;
import seedu.stockpal.commands.ListCommand;
import seedu.stockpal.commands.ExitCommand;
import seedu.stockpal.commands.NewCommand;
import seedu.stockpal.commands.EditCommand;
import seedu.stockpal.commands.DeleteCommand;
import seedu.stockpal.commands.InflowCommand;
import seedu.stockpal.commands.OutflowCommand;
import seedu.stockpal.commands.FindCommand;
import seedu.stockpal.commands.HistoryCommand;
import seedu.stockpal.commands.Command;

import seedu.stockpal.exceptions.InvalidCommandException;
import seedu.stockpal.exceptions.InvalidFormatException;
import seedu.stockpal.exceptions.UnsignedIntegerExceededException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

import static seedu.stockpal.common.Messages.MESSAGE_ERROR_EMPTY_NAME;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_EMPTY_QUANTITY;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INPUT_INTEGER_EXCEEDED;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INVALID_COMMAND;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_INVALID_FORMAT;
import static seedu.stockpal.common.Messages.MESSAGE_ERROR_NAME_ONLY_SPACES;


public class Parser {
    public static final String DIVIDER = " ";
    public static final Pattern NEW_COMMAND_PATTERN =
            Pattern.compile("new n/([a-zA-Z0-9 `~!@#$%^&*()\\[\\]{}<>\\-_+=,.?\"':;]{1,50})" +
                    " q/(\\d+)" +
                    "(?: p/(\\d+\\.\\d{2}))?" +
                    "(?: d/([a-zA-Z0-9 `~!@#$%^&*()\\[\\]{}<>\\-_+=,.?\"':;]+))?");
    public static final Pattern EDIT_COMMAND_PATTERN =
            Pattern.compile("edit (\\d+)" +
                    "(?: n/([a-zA-Z0-9 `~!@#$%^&*()\\[\\]{}<>\\-_+=,.?\"':;]{1,50}))?" +
                    "(?: q/(\\d+))?" +
                    "(?: p/(\\d+\\.\\d{2}))?" +
                    "(?: d/([a-zA-Z0-9 `~!@#$%^&*()\\[\\]{}<>\\-_+=,.?\"':;]+))?");
    public static final Pattern LIST_COMMAND_PATTERN = Pattern.compile("list( -sn| -sq)?");
    public static final Pattern DELETE_COMMAND_PATTERN = Pattern.compile("delete (\\d+)");
    public static final Pattern INFLOW_COMMAND_PATTERN = Pattern.compile("inflow (\\d+) a/(\\d+)");
    public static final Pattern OUTFLOW_COMMAND_PATTERN = Pattern.compile("outflow (\\d+) a/(\\d+)");
    public static final Pattern  FIND_COMMAND_PATTERN =
            Pattern.compile("find ([a-zA-Z0-9 `~!@#$%^&*()\\[\\]{}<>\\-_+=,.?\"':;]+)");
    public static final Pattern HISTORY_COMMAND_PATTERN = Pattern.compile("history (\\d+)");
    public static final int NUM_OF_LIST_COMMAND_ARGUMENTS = 1;
    public static final int NUM_OF_NEW_COMMAND_ARGUMENTS = 4;
    public static final int NUM_OF_EDIT_COMMAND_ARGUMENTS = 5;
    public static final int NUM_OF_DELETE_COMMAND_ARGUMENTS = 1;
    public static final int NUM_OF_INFLOW_COMMAND_ARGUMENTS = 2;
    public static final int NUM_OF_OUTFLOW_COMMAND_ARGUMENTS = 2;
    public static final int NUM_OF_FIND_COMMAND_ARGUMENTS = 1;
    public static final int NUM_OF_HISTORY_COMMAND_ARGUMENTS = 1;
    public static final int START_INDEX = 0;
    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());

    private static String getCommandFromInput(String input) {
        if (!input.contains(DIVIDER)) {
            return input;
        }

        return input.substring(START_INDEX, input.indexOf(DIVIDER));
    }
    private String validateStringInput(String parsedString) {
        if (isNull(parsedString)) { // conditional branch only applicable to description input
            return null;
        }

        String strippedName = parsedString.strip();
        if (strippedName.isEmpty()) {
            return null;
        }
        return strippedName;
    }

    private Integer validateIntegerInput(String parsedInt) throws UnsignedIntegerExceededException {
        try {
            return Integer.parseUnsignedInt(parsedInt);
        } catch (NumberFormatException nfe) {
            throw new UnsignedIntegerExceededException(MESSAGE_ERROR_INPUT_INTEGER_EXCEEDED);
        }
    }

    private Double validateDoubleInput(String parsedDouble) {
        if (isNull(parsedDouble)) {
            return null;
        }
        return Double.parseDouble(parsedDouble);
    }

    private ExitCommand createExitCommand() {
        return new ExitCommand();
    }

    private HelpCommand createHelpCommand() {
        return new HelpCommand();
    }

    private ListCommand validateAndCreateListCommand(ArrayList<String> parsed) {
        String flag = parsed.get(0);

        return new ListCommand(flag);
    }

    private OutflowCommand validateAndCreateOutflowCommand(ArrayList<String> parsed)
            throws UnsignedIntegerExceededException {
        Integer pid = validateIntegerInput(parsed.get(0));
        Integer decreaseBy = validateIntegerInput(parsed.get(1));

        return new OutflowCommand(pid, decreaseBy);
    }

    private InflowCommand validateAndCreateInflowCommand(ArrayList<String> parsed)
            throws UnsignedIntegerExceededException {
        Integer pid = validateIntegerInput(parsed.get(0));
        Integer increaseBy = validateIntegerInput(parsed.get(1));

        return new InflowCommand(pid, increaseBy);
    }

    private DeleteCommand validateAndCreateDeleteCommand(ArrayList<String> parsed)
            throws UnsignedIntegerExceededException {
        Integer pid = validateIntegerInput(parsed.get(0));

        return new DeleteCommand(pid);
    }

    private EditCommand validateAndCreateEditCommand(ArrayList<String> parsed) throws UnsignedIntegerExceededException {
        Integer pid = validateIntegerInput(parsed.get(0));
        String name = validateStringInput(parsed.get(1));
        Integer quantity = validateIntegerInput(parsed.get(2));
        Double price = validateDoubleInput(parsed.get(3));
        String description = validateStringInput(parsed.get(4));

        return new EditCommand(pid, name, quantity, price, description);
    }

    private NewCommand validateAndCreateNewCommand(ArrayList<String> parsed)
            throws UnsignedIntegerExceededException, InvalidFormatException {

        if (isNull(parsed.get(0))) { // name group not matched
            throw new InvalidFormatException(MESSAGE_ERROR_EMPTY_NAME);
        }

        if (isNull(parsed.get(1))) { // quantity group not matched
            throw new InvalidFormatException(MESSAGE_ERROR_EMPTY_QUANTITY);
        }

        String name = validateStringInput(parsed.get(0));
        if (isNull(name)) {
            throw new InvalidFormatException(MESSAGE_ERROR_NAME_ONLY_SPACES);
        }

        Integer quantity = validateIntegerInput(parsed.get(1));
        Double price = validateDoubleInput(parsed.get(2));
        String description = validateStringInput(parsed.get(3));

        return new NewCommand(name, quantity, price, description);
    }

    private FindCommand validateAndCreateFindCommand(ArrayList<String> parsed) throws InvalidFormatException {
        String name = validateStringInput(parsed.get(0));
        if (isNull(name)) {
            throw new InvalidFormatException(MESSAGE_ERROR_NAME_ONLY_SPACES);
        }

        return new FindCommand(name);
    }

    private HistoryCommand validateAndCreateHistoryCommand(ArrayList<String> parsed)
            throws UnsignedIntegerExceededException {
        Integer pid = validateIntegerInput(parsed.get(0));

        return new HistoryCommand(pid);
    }

    private static ArrayList<String> matchAndParseInput(String input, Pattern pattern, int numOfArgs)
            throws InvalidFormatException {
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            LOGGER.log(Level.WARNING, MESSAGE_ERROR_INVALID_FORMAT);
            throw new InvalidFormatException(MESSAGE_ERROR_INVALID_FORMAT);
        }

        ArrayList<String> parsed = new ArrayList<>();
        for (int i = 1; i < numOfArgs + 1; i++) {
            String argument = matcher.group(i);
            parsed.add(argument);
        }

        return parsed;
    }

    public Command parseInput(String input)
            throws InvalidFormatException, InvalidCommandException, UnsignedIntegerExceededException {
        ArrayList<String> parsed;
        input = input.stripLeading();
        String command = getCommandFromInput(input);

        switch (command) {
        case HelpCommand.COMMAND_KEYWORD:
            return createHelpCommand();

        case ListCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, LIST_COMMAND_PATTERN, NUM_OF_LIST_COMMAND_ARGUMENTS);

            return validateAndCreateListCommand(parsed);

        case ExitCommand.COMMAND_KEYWORD:
            return createExitCommand();

        case NewCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, NEW_COMMAND_PATTERN, NUM_OF_NEW_COMMAND_ARGUMENTS);
            assert(parsed.get(0) != null); // name group is captured but may be made up only of spaces
            assert(parsed.get(1) != null);
            return validateAndCreateNewCommand(parsed);

        case EditCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, EDIT_COMMAND_PATTERN, NUM_OF_EDIT_COMMAND_ARGUMENTS);
            assert(parsed.get(0) != null); // pid is captured but may not be found in inventory yet
            return validateAndCreateEditCommand(parsed);

        case DeleteCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, DELETE_COMMAND_PATTERN, NUM_OF_DELETE_COMMAND_ARGUMENTS);
            assert(parsed.get(0) != null); // pid is captured but may not be found in inventory yet
            return validateAndCreateDeleteCommand(parsed);

        case InflowCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, INFLOW_COMMAND_PATTERN, NUM_OF_INFLOW_COMMAND_ARGUMENTS);
            assert(parsed.get(0) != null); // pid is captured but may not be found in inventory yet
            assert(parsed.get(1) != null);
            return validateAndCreateInflowCommand(parsed);

        case OutflowCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, OUTFLOW_COMMAND_PATTERN, NUM_OF_OUTFLOW_COMMAND_ARGUMENTS);
            assert(parsed.get(0) != null); // pid is captured but may not be found in inventory yet
            assert(parsed.get(1) != null);
            return validateAndCreateOutflowCommand(parsed);

        case FindCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, FIND_COMMAND_PATTERN, NUM_OF_FIND_COMMAND_ARGUMENTS);
            assert(parsed.get(0) != null); // keyword group is captured but may be made up only of spaces
            return validateAndCreateFindCommand(parsed);

        case HistoryCommand.COMMAND_KEYWORD:
            parsed = matchAndParseInput(input, HISTORY_COMMAND_PATTERN, NUM_OF_HISTORY_COMMAND_ARGUMENTS);
            assert(parsed.get(0) != null); // pid is captured but may not be found in inventory yet
            return validateAndCreateHistoryCommand(parsed);

        default:
            LOGGER.log(Level.WARNING, MESSAGE_ERROR_INVALID_COMMAND);
            throw new InvalidCommandException(MESSAGE_ERROR_INVALID_COMMAND);
        }
    }
}
