package seedu.binbash;

import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.binbash.command.Command;
import seedu.binbash.command.AddCommand;
import seedu.binbash.command.DeleteCommand;
import seedu.binbash.command.SearchCommand;
import seedu.binbash.command.ListCommand;
import seedu.binbash.command.ByeCommand;
import seedu.binbash.exceptions.InvalidCommandException;
import seedu.binbash.exceptions.InvalidArgumentException;
import seedu.binbash.exceptions.InvalidFormatException;

public class Parser {
    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());
    private final ItemList itemList;

    public Parser(ItemList itemList) {
        this.itemList = itemList;
    }

    public Command parseCommand(String userInput) throws InvalidCommandException {
        assert userInput != null : "User input should not be null";

        String[] tokens = userInput.trim().split("\\s+", 2);
        String commandString = tokens[0].toLowerCase();
        String arguments = tokens.length > 1 ? tokens[1] : "";

        try {
            switch (commandString) {
            case "bye":
                LOGGER.info("Parsing Bye command");
                return new ByeCommand(itemList);
            case "add":
                LOGGER.info("Parsing Add command");
                return parseAddCommand(userInput);
            case "delete":
                LOGGER.info("Parsing Delete command");
                return parseDeleteCommand(userInput);
            case "list":
                LOGGER.info("Parsing List command");
                return parseListCommand(userInput);
            case "search":
                LOGGER.info("Parsing Search command");
                return parseSearchCommand(userInput);
            default:
                throw new InvalidCommandException("Invalid command!");
            }
        } catch (InvalidCommandException e) {
            LOGGER.warning("Invalid command: " + e.getMessage());
            throw e;
        }
    }

    private Command parseDeleteCommand(String userInput) throws InvalidFormatException,
            InvalidArgumentException {
        assert userInput != null : "User input should not be null";

        Matcher argumentMatcher = DeleteCommand.COMMAND_FORMAT.matcher(userInput);
        if (!argumentMatcher.matches()) {
            LOGGER.warning("Delete command is not properly formatted!");
            throw new InvalidFormatException("Delete command is not properly formatted!");
        }

        Pattern indexIdentifier = Pattern.compile("^-?[0-9]+$");
        Matcher indexMatcher = indexIdentifier.matcher(argumentMatcher.group("identifier"));

        if (indexMatcher.matches()) {
            int index = Integer.parseInt(argumentMatcher.group("identifier"));
            if (index <= 0 || index > itemList.getItemCount()) {
                throw new InvalidArgumentException("Index is out of bounds!");
            }
            assert index <= 0 || index > itemList.getItemCount();
            return new DeleteCommand(itemList, index);
        } else {
            String keyword = argumentMatcher.group("identifier");
            assert !keyword.isEmpty();
            return new DeleteCommand(itemList, keyword);
        }
    }

    private Command parseAddCommand(String userInput) throws InvalidFormatException {
        assert userInput != null : "User input should not be null";

        Matcher matcher = AddCommand.COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            LOGGER.warning("Add command is not properly formatted!");
            throw new InvalidFormatException("Add command is not properly formatted!");
        }
        String itemName = matcher.group("itemName");
        String itemDescription = matcher.group("itemDescription");
        int itemQuantity = Integer.parseInt(
                Objects.requireNonNullElse(matcher.group("itemQuantity"), "0").strip()
        );
        String itemExpirationDate = Objects.requireNonNullElse( // If no expiration date provided, set as N.A.
                matcher.group("itemExpirationDate"),
                "N.A."
        ).strip();
        double itemSalePrice = Double.parseDouble(matcher.group("itemSalePrice"));
        double itemCostPrice = Double.parseDouble(matcher.group("itemCostPrice"));

        return new AddCommand(itemList, itemName, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice,
                itemCostPrice);
    }

    private Command parseSearchCommand(String userInput) throws InvalidFormatException {
        assert userInput != null : "User input should not be null";

        Matcher matcher = SearchCommand.COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            LOGGER.warning("Search command is not properly formatted!");
            throw new InvalidFormatException("Search command is not properly formatted!");
        }
        String keyword = matcher.group("keyword");
        return new SearchCommand(itemList, keyword);
    }

    private Command parseListCommand(String arguments) {
        assert arguments != null : "Arguments should not be null";
        return new ListCommand(itemList);
    }
}