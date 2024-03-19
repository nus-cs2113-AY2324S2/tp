package supertracker.parser;

import supertracker.TrackerException;
import supertracker.command.AddCommand;
import supertracker.command.InvalidCommand;
import supertracker.command.ListCommand;
import supertracker.command.NewCommand;
import supertracker.command.QuitCommand;
import supertracker.command.RemoveCommand;
import supertracker.command.UpdateCommand;
import supertracker.command.DeleteCommand;
import supertracker.command.Command;
import supertracker.item.Inventory;
import supertracker.ui.ErrorMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String QUIT_COMMAND = "quit";
    private static final String NEW_COMMAND = "new";
    private static final String LIST_COMMAND = "list";
    private static final String UPDATE_COMMAND = "update";
    private static final String DELETE_COMMAND = "delete";
    private static final String ADD_COMMAND = "add";
    private static final String REMOVE_COMMAND = "remove";
    private static final double ROUNDING_FACTOR = 100.0;
    private static final String BASE_FLAG = "/";
    private static final String NAME_FLAG = "n";
    private static final String QUANTITY_FLAG = "q";
    private static final String PRICE_FLAG = "p";
    private static final String NAME_GROUP = "name";
    private static final String QUANTITY_GROUP = "quantity";
    private static final String PRICE_GROUP = "price";
    private static final String NEW_COMMAND_REGEX = NAME_FLAG + BASE_FLAG + "(?<" + NAME_GROUP + ">.*) "
            + QUANTITY_FLAG + BASE_FLAG + "(?<" + QUANTITY_GROUP + ">.*) "
            + PRICE_FLAG + BASE_FLAG + "(?<" + PRICE_GROUP + ">.*) ";
    private static final String UPDATE_COMMAND_REGEX = NAME_FLAG + BASE_FLAG + "(?<" + NAME_GROUP + ">.*) "
            + "(?<" + QUANTITY_GROUP + ">(?:" + QUANTITY_FLAG + BASE_FLAG + ".*)?) "
            + "(?<" + PRICE_GROUP + ">(?:" + PRICE_FLAG + BASE_FLAG + ".*)?) ";
    private static final String LIST_COMMAND_REGEX = "(?<" + QUANTITY_GROUP + ">(?:" + QUANTITY_FLAG + BASE_FLAG
            + ".*)?) (?<" + PRICE_GROUP + ">(?:" + PRICE_FLAG + BASE_FLAG + ".*)?) ";
    private static final String DELETE_COMMAND_REGEX = NAME_FLAG + BASE_FLAG + "(?<" + NAME_GROUP + ">.*) ";
    private static final String ADD_COMMAND_REGEX = NAME_FLAG + BASE_FLAG + "(?<" + NAME_GROUP + ">.*) "
            + QUANTITY_FLAG + BASE_FLAG + "(?<" + QUANTITY_GROUP + ">.*) ";
    private static final String REMOVE_COMMAND_REGEX = NAME_FLAG + BASE_FLAG + "(?<" + NAME_GROUP + ">.*) "
            + QUANTITY_FLAG + BASE_FLAG + "(?<" + QUANTITY_GROUP + ">.*) ";


    /**
     * Returns the command word specified in the user input string
     *
     * @param input a String of the user's input
     * @return a String of the first word in the user input
     */
    private static String getCommandWord(String input) {
        if (!input.contains(" ")) {
            return input;
        }
        return input.substring(0, input.indexOf(" "));
    }

    /**
     * Parses a Command accordingly from the user input string
     *
     * @param input a String of the user's input
     * @return a Command to execute
     */
    public static Command parseCommand(String input) throws TrackerException {
        String commandWord = getCommandWord(input);
        String params = input.replace(commandWord, "").trim();

        Command command;
        switch (commandWord) {
        case QUIT_COMMAND:
            command = new QuitCommand();
            break;
        case NEW_COMMAND:
            command = parseNewCommand(params);
            break;
        case LIST_COMMAND:
            command = parseListCommand(params);
            break;
        case UPDATE_COMMAND:
            command = parseUpdateCommand(params);
            break;
        case DELETE_COMMAND:
            command = parseDeleteCommand(params);
            break;
        case ADD_COMMAND:
            command = parseAddCommand(params);
            break;
        case REMOVE_COMMAND:
            command = parseRemoveCommand(params);
            break;
        default:
            command = new InvalidCommand();
            break;
        }
        return command;
    }

    /**
     * Returns a String in the format of a regex expression pattern for parsing of command inputs
     *
     * @param inputParams a String of the input parameters
     * @param paramFlags a String array with the specified flags to split the input parameters
     * @return a String of the input parameters in the format of a regex expression specified by the input flags
     */
    private static String makeStringPattern(String inputParams, String[] paramFlags) {
        StringBuilder flagBuilder = new StringBuilder();
        for (String flag : paramFlags) {
            flagBuilder.append(flag);
        }
        String flags = flagBuilder.toString();

        String[] params = inputParams.split("(?=[" + flags + "]" + BASE_FLAG + ")");
        StringBuilder stringPattern = new StringBuilder();

        for (String paramFlag : paramFlags) {
            for (String p : params) {
                if (p.startsWith(paramFlag + BASE_FLAG)) {
                    stringPattern.append(p.trim());
                    break;
                }
            }
            stringPattern.append(" ");
        }

        return stringPattern.toString();
    }

    private static Matcher getPatternMatcher(String regex, String input, String[] paramFlags) {
        Pattern p = Pattern.compile(regex);
        String commandPattern = makeStringPattern(input, paramFlags);
        assert commandPattern.length() >= paramFlags.length;
        return p.matcher(commandPattern);
    }

    private static double roundTo2Dp(double unroundedValue) {
        return Math.round(unroundedValue * ROUNDING_FACTOR) / ROUNDING_FACTOR;
    }

    private static Command parseUpdateCommand(String input) throws TrackerException {
        String[] flags = {NAME_FLAG, QUANTITY_FLAG, PRICE_FLAG};
        Matcher matcher = getPatternMatcher(UPDATE_COMMAND_REGEX, input, flags);

        if (!matcher.matches()) {
            throw new TrackerException(ErrorMessage.INVALID_UPDATE_FORMAT);
        }

        String name = matcher.group(NAME_GROUP).trim();
        String quantityString = matcher.group(QUANTITY_GROUP).replace(QUANTITY_FLAG + BASE_FLAG, "").trim();
        String priceString = matcher.group(PRICE_GROUP).replace(PRICE_FLAG + BASE_FLAG, "").trim();

        if (name.isEmpty() || (quantityString.isEmpty() && priceString.isEmpty())) {
            throw new TrackerException(ErrorMessage.EMPTY_PARAM_INPUT);
        }

        if (!Inventory.contains(name)) {
            throw new TrackerException(name + ErrorMessage.ITEM_NOT_IN_LIST_UPDATE);
        }

        int quantity = -1;
        double price = -1;

        try {
            if (!quantityString.isEmpty()) {
                quantity = Integer.parseInt(quantityString);
            }
            if (!priceString.isEmpty()) {
                price = roundTo2Dp(Double.parseDouble(priceString));
            }
        } catch (NumberFormatException e) {
            throw new TrackerException(ErrorMessage.INVALID_NUMBER_FORMAT);
        }

        if (!quantityString.isEmpty() && quantity < 0) {
            throw new TrackerException(ErrorMessage.QUANTITY_TOO_SMALL);
        }

        if (!priceString.isEmpty() && price < 0) {
            throw new TrackerException(ErrorMessage.PRICE_TOO_SMALL);
        }

        return new UpdateCommand(name, quantity, price);
    }

    private static Command parseNewCommand(String input) throws TrackerException {
        String[] flags = {NAME_FLAG, QUANTITY_FLAG, PRICE_FLAG};
        Matcher matcher = getPatternMatcher(NEW_COMMAND_REGEX, input, flags);

        if (!matcher.matches()) {
            throw new TrackerException(ErrorMessage.INVALID_NEW_ITEM_FORMAT);
        }

        String name = matcher.group(NAME_GROUP).trim();
        String quantityString = matcher.group(QUANTITY_GROUP).trim();
        String priceString = matcher.group(PRICE_GROUP).trim();

        if (name.isEmpty() || quantityString.isEmpty() || priceString.isEmpty()) {
            throw new TrackerException(ErrorMessage.EMPTY_PARAM_INPUT);
        }

        if (Inventory.contains(name)) {
            throw new TrackerException(name + ErrorMessage.ITEM_IN_LIST_NEW);
        }

        int quantity;
        double price;

        // throws NumberFormatException if strings cannot be parsed
        try {
            quantity = Integer.parseInt(quantityString);
            price = roundTo2Dp(Double.parseDouble(priceString));
        } catch (NumberFormatException e) {
            throw new TrackerException(ErrorMessage.INVALID_NUMBER_FORMAT);
        }

        if (quantity < 0) {
            throw new TrackerException(ErrorMessage.QUANTITY_TOO_SMALL);
        }

        if (price < 0) {
            throw new TrackerException(ErrorMessage.PRICE_TOO_SMALL);
        }

        return new NewCommand(name, quantity, price);
    }

    private static Command parseListCommand(String input) throws TrackerException {
        String[] flags = {QUANTITY_FLAG, PRICE_FLAG};
        Matcher matcher = getPatternMatcher(LIST_COMMAND_REGEX, input, flags);

        if (!matcher.matches()) {
            throw new TrackerException(ErrorMessage.INVALID_LIST_FORMAT);
        }

        boolean hasQuantity = !matcher.group(QUANTITY_GROUP).isEmpty();
        boolean hasPrice = !matcher.group(PRICE_GROUP).isEmpty();

        // to check if q comes before p or vice versa
        String firstParam = "";
        if (hasQuantity && hasPrice) {
            int quantityPosition = input.indexOf(QUANTITY_FLAG + BASE_FLAG);
            int pricePosition = input.indexOf(PRICE_FLAG + BASE_FLAG);
            firstParam = quantityPosition < pricePosition ? QUANTITY_FLAG : PRICE_FLAG;
        }

        return new ListCommand(hasQuantity, hasPrice, firstParam);
    }

    private static Command parseDeleteCommand(String input) throws TrackerException {
        String[] flags = {NAME_FLAG};
        Matcher matcher = getPatternMatcher(DELETE_COMMAND_REGEX, input, flags);

        if (!matcher.matches()) {
            throw new TrackerException(ErrorMessage.INVALID_DELETE_FORMAT);
        }

        String name = matcher.group(NAME_GROUP).trim();

        if (name.isEmpty()) {
            throw new TrackerException(ErrorMessage.EMPTY_PARAM_INPUT);
        }

        if (!Inventory.contains(name)) {
            throw new TrackerException(name + ErrorMessage.ITEM_NOT_IN_LIST_DELETE);
        }

        return new DeleteCommand(name);
    }

    private static Command parseAddCommand(String input) throws TrackerException {
        String[] flags = {NAME_FLAG, QUANTITY_FLAG};
        Matcher matcher = getPatternMatcher(ADD_COMMAND_REGEX, input, flags);

        if (!matcher.matches()) {
            throw new TrackerException(ErrorMessage.INVALID_ADD_FORMAT);
        }

        String name = matcher.group(NAME_GROUP).trim();
        String quantityString = matcher.group(QUANTITY_GROUP).replace(QUANTITY_FLAG + BASE_FLAG, "").trim();

        if (name.isEmpty() || quantityString.isEmpty()) {
            throw new TrackerException(ErrorMessage.EMPTY_PARAM_INPUT);
        }

        if (!Inventory.contains(name)) {
            throw new TrackerException(name + ErrorMessage.ITEM_NOT_IN_LIST_ADD);
        }

        int quantity;

        // throws NumberFormatException if strings cannot be parsed
        try {
            quantity = Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            throw new TrackerException(ErrorMessage.INVALID_NUMBER_FORMAT);
        }

        if (quantity < 0) {
            throw new TrackerException(ErrorMessage.QUANTITY_TOO_SMALL);
        }

        return new AddCommand(name,quantity);
    }

    private static Command parseRemoveCommand(String input) throws TrackerException {
        String[] flags = {NAME_FLAG, QUANTITY_FLAG};
        Matcher matcher = getPatternMatcher(REMOVE_COMMAND_REGEX, input, flags);

        if (!matcher.matches()) {
            throw new TrackerException(ErrorMessage.INVALID_REMOVE_FORMAT);
        }

        String name = matcher.group(NAME_GROUP).trim();
        String quantityString = matcher.group(QUANTITY_GROUP).replace(QUANTITY_FLAG + BASE_FLAG, "").trim();

        if (name.isEmpty() || quantityString.isEmpty()) {
            throw new TrackerException(ErrorMessage.EMPTY_PARAM_INPUT);
        }

        if (!Inventory.contains(name)) {
            throw new TrackerException(name + ErrorMessage.ITEM_NOT_IN_LIST_REMOVE);
        }

        int quantity;

        // throws NumberFormatException if strings cannot be parsed
        try {
            quantity = Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            throw new TrackerException(ErrorMessage.INVALID_NUMBER_FORMAT);
        }

        if (quantity < 0) {
            throw new TrackerException(ErrorMessage.QUANTITY_TOO_SMALL);
        }

        return new RemoveCommand(name, quantity);
    }
}
