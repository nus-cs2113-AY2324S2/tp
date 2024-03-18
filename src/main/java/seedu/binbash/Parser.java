package seedu.binbash;

import java.util.regex.Matcher;

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
    private final ItemList itemList;

    public Parser(ItemList itemList) {
        this.itemList = itemList;
    }

    public Command parseCommand(String userInput) throws InvalidCommandException {
        String[] tokens = userInput.trim().split("\\s+", 2);
        String commandString = tokens[0].toLowerCase();
        String arguments = tokens.length > 1 ? tokens[1] : "";

        try {
            switch (commandString) {
            case "bye":
                return new ByeCommand(itemList);
            case "add":
                return parseAddCommand(userInput);
            case "delete":
                return parseDeleteCommand(userInput);
            case "list":
                return parseListCommand(userInput);
            case "search":
                return parseSearchCommand(userInput);
            default:
                throw new InvalidCommandException("Invalid command!");
            }
        } catch (InvalidCommandException e) {
            throw e;
        }
    }

    private Command parseDeleteCommand(String userInput) throws InvalidArgumentException {
        Matcher matcher = DeleteCommand.COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new InvalidArgumentException("Delete command is not properly formatted!");
        }
        int index = Integer.parseInt(matcher.group("index"));
        return new DeleteCommand(itemList, index);
    }

    private Command parseAddCommand(String userInput) throws InvalidFormatException {
        Matcher matcher = AddCommand.COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new InvalidFormatException("Add command is not properly formatted!");
        }
        String itemName = matcher.group("itemName");
        String itemDescription = matcher.group("itemDescription");
        int itemQuantity = Integer.parseInt(matcher.group("itemQuantity"));
        String itemExpirationDate = matcher.group("itemExpirationDate");
        double itemSalePrice = Double.parseDouble(matcher.group("itemSalePrice"));
        double itemCostPrice = Double.parseDouble(matcher.group("itemCostPrice"));

        return new AddCommand(itemList, itemName, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice,
                    itemCostPrice);
    }

    private Command parseSearchCommand(String userInput) throws InvalidFormatException {
        Matcher matcher = SearchCommand.COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new InvalidFormatException("Search command is not properly formatted!");
        }
        String keyword = matcher.group("keyword");
        return new SearchCommand(itemList, keyword);
    }

    private Command parseListCommand(String arguments) {
        return new ListCommand(itemList);
    }
}

