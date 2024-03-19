package seedu.stockpal.parser;

import seedu.stockpal.commands.HelpCommand;
import seedu.stockpal.commands.ListCommand;
import seedu.stockpal.commands.ExitCommand;
import seedu.stockpal.commands.NewCommand;
import seedu.stockpal.commands.EditCommand;
import seedu.stockpal.commands.DeleteCommand;
import seedu.stockpal.commands.InflowCommand;
import seedu.stockpal.commands.OutflowCommand;
import seedu.stockpal.commands.Command;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.exceptions.InvalidCommandException;
import seedu.stockpal.exceptions.InvalidFormatException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import seedu.stockpal.storage.Storage;

public class Parser {
    public static final String DIVIDER = " ";
    public static final Pattern NEW_COMMAND_PATTERN =
            Pattern.compile("new n/([a-zA-Z0-9 ]+) q/(\\d+)(?: p/(\\d+\\.\\d{2}))?(?: d/([a-zA-Z0-9 ]{1,100}))?");
    public static final Pattern EDIT_COMMAND_PATTERN =
            Pattern.compile("edit (\\d+)(?: n/([a-zA-Z0-9 ]+))?(?: q/(\\d+))?" +
                    "(?: p/(\\d+\\.\\d{2}))?(?: d/([a-zA-Z0-9 ]{1,100}))?");
    public static final Pattern DELETE_COMMAND_PATTERN = Pattern.compile("delete (\\d+)");
    public static final Pattern INFLOW_COMMAND_PATTERN = Pattern.compile("inflow (\\d+) a/(\\d+)");
    public static final Pattern OUTFLOW_COMMAND_PATTERN = Pattern.compile("outflow (\\d+) a/(\\d+)");
    public static final int NUM_OF_NEW_COMMAND_ARGUMENTS = 4;
    public static final int NUM_OF_EDIT_COMMAND_ARGUMENTS = 5;
    public static final int NUM_OF_DELETE_COMMAND_ARGUMENTS = 1;
    public static final int NUM_OF_INFLOW_COMMAND_ARGUMENTS = 2;
    public static final int NUM_OF_OUTFLOW_COMMAND_ARGUMENTS = 2;
    public static final int START_INDEX = 0;
    private static final Double EMPTY_PRICE = -0.1;

    public final ProductList productList;
    private final Storage storage;

    public Parser(ProductList productList, Storage storage) {
        this.productList = productList;
        this.storage = storage;
    }

    public Command parseCommand(String input) throws InvalidFormatException, InvalidCommandException {
        ArrayList<String> parsed;
        input = input.strip();
        String command = getCommandFromInput(input);

        switch (command) {
        case HelpCommand.COMMAND_KEYWORD:
            return createHelpCommand();

        case ListCommand.COMMAND_KEYWORD:
            return createListCommand();

        case ExitCommand.COMMAND_KEYWORD:
            return createExitCommand();

        case NewCommand.COMMAND_KEYWORD:
            parsed = validateAndParseCommand(input, NEW_COMMAND_PATTERN, NUM_OF_NEW_COMMAND_ARGUMENTS);
            return createNewCommand(parsed);

        case EditCommand.COMMAND_KEYWORD:
            parsed = validateAndParseCommand(input, EDIT_COMMAND_PATTERN, NUM_OF_EDIT_COMMAND_ARGUMENTS);
            return createEditCommand(parsed);

        case DeleteCommand.COMMAND_KEYWORD:
            parsed = validateAndParseCommand(input, DELETE_COMMAND_PATTERN, NUM_OF_DELETE_COMMAND_ARGUMENTS);
            return createDeleteCommand(parsed);

        case InflowCommand.COMMAND_KEYWORD:
            parsed = validateAndParseCommand(input, INFLOW_COMMAND_PATTERN, NUM_OF_INFLOW_COMMAND_ARGUMENTS);
            return createInflowCommand(parsed);

        case OutflowCommand.COMMAND_KEYWORD:
            parsed = validateAndParseCommand(input, OUTFLOW_COMMAND_PATTERN, NUM_OF_OUTFLOW_COMMAND_ARGUMENTS);
            return createOutflowCommand(parsed);

        default:
            //print help
            throw new InvalidCommandException("Invalid command: " + command);
        }
    }

    private ExitCommand createExitCommand() {
        return new ExitCommand();
    }

    private ListCommand createListCommand() {
        return new ListCommand(productList);
    }

    private HelpCommand createHelpCommand() {
        return new HelpCommand();
    }

    private OutflowCommand createOutflowCommand(ArrayList<String> parsed) {
        Integer pid = Integer.parseInt(parsed.get(0));
        Integer decreaseBy = Integer.parseInt(parsed.get(1));

        return new OutflowCommand(productList, pid, decreaseBy, storage);
    }

    private InflowCommand createInflowCommand(ArrayList<String> parsed) {
        Integer pid = Integer.parseInt(parsed.get(0));
        Integer increaseBy = Integer.parseInt(parsed.get(1));

        return new InflowCommand(productList, pid, increaseBy, storage);
    }

    private DeleteCommand createDeleteCommand(ArrayList<String> parsed) {
        Integer pid = Integer.parseInt(parsed.get(0));

        return new DeleteCommand(productList, pid, storage);
    }

    private EditCommand createEditCommand(ArrayList<String> parsed) {
        Integer pid = Integer.parseInt(parsed.get(0));
        String name = parsed.get(1);
        Integer quantity = (parsed.get(2) == null)
                ? null
                : Integer.parseInt(parsed.get(2));

        Double price = (parsed.get(3) == null)
                ? null
                : Double.parseDouble(parsed.get(3));
        String description = parsed.get(4);

        return new EditCommand(productList, pid, name, quantity, price, description, storage);
    }

    private NewCommand createNewCommand(ArrayList<String> parsed) {
        String name = parsed.get(0);
        Integer quantity = Integer.parseInt(parsed.get(1));
        Double price = (parsed.get(2) == null)
                ? EMPTY_PRICE
                : Double.parseDouble(parsed.get(2));
        String description = parsed.get(3);

        return new NewCommand(productList, name, quantity, price, description, storage);
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
            throw new InvalidFormatException("Invalid input format");
        }

        ArrayList<String> parsed = new ArrayList<>();
        for (int i = 1; i < numOfArgs + 1; i++) {
            parsed.add(matcher.group(i));
        }

        return parsed;
    }
}
