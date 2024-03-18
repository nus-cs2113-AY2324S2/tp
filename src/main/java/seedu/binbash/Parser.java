package seedu.binbash;

import java.util.regex.Matcher;

import seedu.binbash.command.Command;
import seedu.binbash.command.AddCommand;
import seedu.binbash.command.DeleteIndexCommand;
import seedu.binbash.command.DeleteNameCommand;
import seedu.binbash.command.SearchCommand;
import seedu.binbash.command.ListCommand;
import seedu.binbash.command.ByeCommand;

public class Parser {
    private final ItemList itemList;

    public Parser(ItemList itemList) {
        this.itemList = itemList;
    }

    public Command parseCommand(String userInput) {
        String[] tokens = userInput.trim().split("\\s+", 2);
        String commandString = tokens[0].toLowerCase();
        String arguments = tokens.length > 1 ? tokens[1] : "";

        switch (commandString) {
        case "add":
            return parseAddCommand(userInput);
        case "deleteindex":
            return parseDeleteIndexCommand(userInput);
        case "deletename":
            return parseDeleteNameCommand(userInput);
        case "list":
            return parseListCommand(userInput);
        case "search":
            return parseSearchCommand(userInput);
        default:
            return new ByeCommand(itemList);
        }
    }

    private Command parseDeleteIndexCommand(String userInput) {
        Matcher matcher = DeleteIndexCommand.COMMAND_FORMAT.matcher(userInput);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group("index"));
            return new DeleteIndexCommand(itemList, index);
        } else {
            return null;
        }
    }

    private Command parseDeleteNameCommand(String userInput) {
        Matcher matcher = DeleteNameCommand.COMMAND_FORMAT.matcher(userInput);
        if (matcher.matches()) {
            String itemName = matcher.group("itemName");
            return new DeleteNameCommand(itemList, itemName);
        } else {
            return null;
        }
    }

    private Command parseAddCommand(String userInput) {
        Matcher matcher = AddCommand.COMMAND_FORMAT.matcher(userInput);
        if (matcher.matches()) {
            String itemName = matcher.group("itemName");
            String itemDescription = matcher.group("itemDescription");
            int itemQuantity = Integer.parseInt(matcher.group("itemQuantity"));
            String itemExpirationDate = matcher.group("itemExpirationDate");
            double itemSalePrice = Double.parseDouble(matcher.group("itemSalePrice"));
            double itemCostPrice = Double.parseDouble(matcher.group("itemCostPrice"));

            return new AddCommand(itemList, itemName, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice,
                    itemCostPrice);
        } else {
            return null;
        }
    }

    private Command parseSearchCommand(String userInput) {
        Matcher matcher = SearchCommand.COMMAND_FORMAT.matcher(userInput);
        if (matcher.matches()) {
            String keyword = matcher.group("keyword");
            return new SearchCommand(itemList, keyword);
        } else {
            return null;
        }
    }

    private Command parseListCommand(String arguments) {
        return new ListCommand(itemList);
    }
}

